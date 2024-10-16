package sangcci.springloggingtest.querycounter;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.ApplicationContext;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public abstract class BaseJpaTest {

    @Autowired
    protected ApplicationContext applicationContext;

    @Autowired
    protected DataSource dataSource;

}
