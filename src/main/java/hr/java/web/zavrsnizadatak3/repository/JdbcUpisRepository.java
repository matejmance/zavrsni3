package hr.java.web.zavrsnizadatak3.repository;

import hr.java.web.zavrsnizadatak3.domain.Polaznik;
import hr.java.web.zavrsnizadatak3.domain.ProgramObrazovanja;
import hr.java.web.zavrsnizadatak3.domain.Upis;
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
public class JdbcUpisRepository implements UpisRepository {

    private final JdbcTemplate jdbcTemplate;

    private static final String SELECT_SQL = """
            SELECT u.UpisID,
                   p.PolaznikID, p.Ime, p.Prezime,
                   po.ProgramObrazovanjaID, po.Naziv, po.CSVET
            FROM Upis u
            JOIN Polaznik p
                ON u.IDPolaznik = p.PolaznikID
            JOIN ProgramObrazovanja po
                ON u.IDProgramObrazovanja = po.ProgramObrazovanjaID
            """;

    private static final RowMapper<Upis> ROW_MAPPER =
            (resultSet, rowNum) -> {
                Polaznik polaznik = new Polaznik();
                polaznik.setId(resultSet.getInt("PolaznikID"));
                polaznik.setIme(resultSet.getString("Ime"));
                polaznik.setPrezime(resultSet.getString("Prezime"));

                ProgramObrazovanja program = new ProgramObrazovanja();
                program.setId(resultSet.getInt("ProgramObrazovanjaID"));
                program.setNaziv(resultSet.getString("Naziv"));
                program.setCsvet(resultSet.getInt("CSVET"));

                Upis upis = new Upis();
                upis.setId(resultSet.getInt("UpisID"));
                upis.setPolaznik(polaznik);
                upis.setProgramObrazovanja(program);

                return upis;
            };

    public JdbcUpisRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Upis> findAll() {
        return jdbcTemplate.query(
                SELECT_SQL + " ORDER BY u.UpisID",
                ROW_MAPPER
        );
    }

    @Override
    public Optional<Upis> findById(Integer id) {
        return jdbcTemplate.query(
                        SELECT_SQL + " WHERE u.UpisID = ?",
                        ROW_MAPPER,
                        id
                )
                .stream()
                .findFirst();
    }

    @Override
    public <S extends Upis> S save(S upis) {
        if (upis.getId() != null) {
            String sql = """
                    UPDATE Upis
                    SET IDPolaznik = ?, IDProgramObrazovanja = ?
                    WHERE UpisID = ?
                    """;

            int updatedRows = jdbcTemplate.update(
                    sql,
                    upis.getPolaznik().getId(),
                    upis.getProgramObrazovanja().getId(),
                    upis.getId()
            );

            if (updatedRows == 0) {
                throw new EmptyResultDataAccessException(1);
            }

            return upis;
        }

        String sql = """
                INSERT INTO Upis (IDPolaznik, IDProgramObrazovanja)
                VALUES (?, ?)
                """;

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement statement = connection.prepareStatement(
                    sql,
                    Statement.RETURN_GENERATED_KEYS
            );

            statement.setInt(1, upis.getPolaznik().getId());
            statement.setInt(2, upis.getProgramObrazovanja().getId());

            return statement;
        }, keyHolder);

        Number generatedId = keyHolder.getKey();

        if (generatedId == null) {
            throw new DataRetrievalFailureException(
                    "Nije moguće dohvatiti ID novog upisa."
            );
        }

        upis.setId(generatedId.intValue());

        return upis;
    }
}