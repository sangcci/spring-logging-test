package sangcci.springloggingtest.board.infra;

import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import sangcci.springloggingtest.board.application.port.BoardRepository;
import sangcci.springloggingtest.board.domain.Board;

@Repository
@RequiredArgsConstructor
public class BoardRepositoryImpl implements BoardRepository {

    private final BoardJpaRepository boardJpaRepository;
    private final BoardJdbcRepository boardJdbcRepository;

    @Override
    public Optional<Board> findById(Long id) {
        return boardJpaRepository.findById(id);
    }

    @Override
    public void saveAll(List<Board> boards) {
        //boardJpaRepository.saveAll(boards);
        boardJdbcRepository.bulkInsert(boards);
    }
}
