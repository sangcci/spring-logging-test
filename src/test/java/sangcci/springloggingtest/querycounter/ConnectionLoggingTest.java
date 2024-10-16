package sangcci.springloggingtest.querycounter;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.sql.DataSource;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeansException;
import sangcci.springloggingtest.common.querycounter.ConnectionProxy;

public class ConnectionLoggingTest extends BaseJpaTest {

    @Test
    void DataSource_singleton_bean_test() {
        DataSource dataSourceBean = applicationContext.getBean(DataSource.class);

        assertThat(dataSourceBean).isSameAs(dataSource);
        assertThat(applicationContext.isSingleton("dataSource")).isTrue();
    }

    @Test
    void connection_singleton_bean_test() {
        assertThatThrownBy(() -> applicationContext.getBean(Connection.class))
                .isInstanceOf(BeansException.class);
    }

    @Test
    void connection_proxy_test() throws SQLException {
        // jdk dynamic proxy는 reflection
        Connection connection = dataSource.getConnection();

        System.out.println(connection.getClass());

        assertThat(connection).isInstanceOf(ConnectionProxy.class);
    }

    @Test
    void connection_call_prepareStatement_test() throws SQLException {
        Connection connection = dataSource.getConnection();
        System.out.println(connection.getClass().getName());
        //connection.setAutoCommit(false);

        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO board(title, content) VALUES (?, ?)");
        preparedStatement.setString(1, "제목1");
        preparedStatement.setString(2, "내용1");
        preparedStatement.execute();

        //connection.commit();

        connection.close();
    }
}
