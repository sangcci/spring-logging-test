package sangcci.springloggingtest.board.application.port;

import java.util.List;
import java.util.Optional;
import sangcci.springloggingtest.board.domain.Board;

public interface BoardRepository {

    Optional<Board> findById(Long id);

    void saveAll(List<Board> boards);
}
