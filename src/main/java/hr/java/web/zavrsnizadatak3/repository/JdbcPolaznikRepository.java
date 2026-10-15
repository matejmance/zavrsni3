package hr.java.web.zavrsnizadatak3.repository;

import hr.java.web.zavrsnizadatak3.domain.Polaznik;
import org.springframework.context.annotation.Profile;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

@Repository
@Profile("jdbc")
public class JdbcPolaznikRepository implements PolaznikRepository {

    private final JdbcTemplate jdbcTemplate;

    private static final RowMapper<Polaznik> ROW_MAPPER =
            (resultSet, rowNum) -> {
                Polaznik polaznik = new Polaznik();

                polaznik.setId(resultSet.getInt("PolaznikID"));
                polaznik.setIme(resultSet.getString("Ime"));
                polaznik.setPrezime(resultSet.getString("Prezime"));

                return polaznik;
            };

    public JdbcPolaznikRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Polaznik> findAll() {
        String sql = """
                SELECT PolaznikID, Ime, Prezime
                FROM Polaznik
                ORDER BY PolaznikID
                """;

        return jdbcTemplate.query(sql, ROW_MAPPER);
    }

    @Override
    public Optional<Polaznik> findById(Integer id) {
        String sql = """
                SELECT PolaznikID, Ime, Prezime
                FROM Polaznik
                WHERE PolaznikID = ?
                """;

        return jdbcTemplate.query(sql, ROW_MAPPER, id)
                .stream()
                .findFirst();
    }

    @Override
    public <S extends Polaznik> S save(S polaznik) {
        if (polaznik.getId() != null) {
            String sql = """
                    UPDATE Polaznik
                    SET Ime = ?, Prezime = ?
                    WHERE PolaznikID = ?
                    """;

            int updatedRows = jdbcTemplate.update(
                    sql,
                    polaznik.getIme(),
                    polaznik.getPrezime(),
                    polaznik.getId()
            );

            if (updatedRows == 0) {
                throw new EmptyResultDataAccessException(1);
            }

            return polaznik;
        }

        String sql = """
                INSERT INTO Polaznik (Ime, Prezime)
                VALUES (?, ?)
                """;

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement statement = connection.prepareStatement(
                    sql,
                    Statement.RETURN_GENERATED_KEYS
            );

            statement.setString(1, polaznik.getIme());
            statement.setString(2, polaznik.getPrezime());

            return statement;
        }, keyHolder);

        Number generatedId = keyHolder.getKey();

        if (generatedId == null) {
            throw new DataRetrievalFailureException(
                    "Nije moguće dohvatiti ID novog polaznika."
            );
        }

        polaznik.setId(generatedId.intValue());

        return polaznik;
    }
}