package sangcci.springloggingtest.board.application;

import java.util.List;
import java.util.NoSuchElementException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sangcci.springloggingtest.board.application.port.BoardRepository;
import sangcci.springloggingtest.board.domain.Board;
import sangcci.springloggingtest.board.dto.BoardRequest;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    @Transactional(readOnly = true)
    public Board getBoardById(Long id) {
        return boardRepository.findById(id)
                .orElseThrow(NoSuchElementException::new);
    }

    @Transactional
    public void registBoards(List<BoardRequest> boardRequests) {
        List<Board> boards = boardRequests.stream()
                .map(BoardRequest::toEntity)
                .toList();

        boards.forEach(Board::validateContent);

        boardRepository.saveAll(boards);
    }
}
