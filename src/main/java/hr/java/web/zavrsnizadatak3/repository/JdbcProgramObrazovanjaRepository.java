package hr.java.web.zavrsnizadatak3.repository;

import hr.java.web.zavrsnizadatak3.domain.ProgramObrazovanja;
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
public class JdbcProgramObrazovanjaRepository
        implements ProgramObrazovanjaRepository {

    private final JdbcTemplate jdbcTemplate;

    private static final RowMapper<ProgramObrazovanja> ROW_MAPPER =
            (resultSet, rowNum) -> {
                ProgramObrazovanja program = new ProgramObrazovanja();

                program.setId(resultSet.getInt("ProgramObrazovanjaID"));
                program.setNaziv(resultSet.getString("Naziv"));
                program.setCsvet(resultSet.getInt("CSVET"));

                return program;
            };

    public JdbcProgramObrazovanjaRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<ProgramObrazovanja> findAll() {
        String sql = """
                SELECT ProgramObrazovanjaID, Naziv, CSVET
                FROM ProgramObrazovanja
                ORDER BY ProgramObrazovanjaID
                """;

        return jdbcTemplate.query(sql, ROW_MAPPER);
    }

    @Override
    public Optional<ProgramObrazovanja> findById(Integer id) {
        String sql = """
                SELECT ProgramObrazovanjaID, Naziv, CSVET
                FROM ProgramObrazovanja
                WHERE ProgramObrazovanjaID = ?
                """;

        return jdbcTemplate.query(sql, ROW_MAPPER, id)
                .stream()
                .findFirst();
    }

    @Override
    public <S extends ProgramObrazovanja> S save(S program) {
        if (program.getId() != null) {
            String sql = """
                    UPDATE ProgramObrazovanja
                    SET Naziv = ?, CSVET = ?
                    WHERE ProgramObrazovanjaID = ?
                    """;

            int updatedRows = jdbcTemplate.update(
                    sql,
                    program.getNaziv(),
                    program.getCsvet(),
                    program.getId()
            );

            if (updatedRows == 0) {
                throw new EmptyResultDataAccessException(1);
            }

            return program;
        }

        String sql = """
                INSERT INTO ProgramObrazovanja (Naziv, CSVET)
                VALUES (?, ?)
                """;

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement statement = connection.prepareStatement(
                    sql,
                    Statement.RETURN_GENERATED_KEYS
            );

            statement.setString(1, program.getNaziv());
            statement.setInt(2, program.getCsvet());

            return statement;
        }, keyHolder);

        Number generatedId = keyHolder.getKey();

        if (generatedId == null) {
            throw new DataRetrievalFailureException(
                    "Nije moguće dohvatiti ID novog programa."
            );
        }

        program.setId(generatedId.intValue());

        return program;
    }
}