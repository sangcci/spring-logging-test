package sangcci.springloggingtest.board.infra;

import org.springframework.data.jpa.repository.JpaRepository;
import sangcci.springloggingtest.board.domain.Board;

public interface BoardJpaRepository extends JpaRepository<Board, Long> {

}
