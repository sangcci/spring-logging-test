package sangcci.springloggingtest.board.application;

import java.util.NoSuchElementException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sangcci.springloggingtest.board.domain.Board;
import sangcci.springloggingtest.board.infra.BoardJpaRepository;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardJpaRepository boardJpaRepository;

    @Transactional(readOnly = true)
    public Board getBoardById(Long id) {
        return boardJpaRepository.findById(id)
                .orElseThrow(NoSuchElementException::new);
    }
}
