package sangcci.springloggingtest.board.infra;

import java.sql.PreparedStatement;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import sangcci.springloggingtest.board.domain.Board;

@Component
@RequiredArgsConstructor
public class BoardJdbcRepository {

    private static final String INSERT_SQL = "INSERT INTO board (title, content) VALUES (?, ?)";

    private final JdbcTemplate jdbcTemplate;

    public void bulkInsert(List<Board> boards) {
        jdbcTemplate.batchUpdate(
                INSERT_SQL,
                boards,
                1000,
                (PreparedStatement ps, Board board) -> {
                    ps.setString(1, board.getTitle());
                    ps.setString(2, board.getContent());

                });
    }
}
