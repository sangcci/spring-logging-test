package sangcci.springloggingtest.board.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import sangcci.springloggingtest.board.exception.BoardErrorCode;
import sangcci.springloggingtest.board.exception.BoardException;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String content;

    public Board(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public void validateContent() {
        if (content.contains("불건전한 말")) {
            throw new BoardException(BoardErrorCode.NOT_ALLOW_CONTENT);
        }
    }
}
