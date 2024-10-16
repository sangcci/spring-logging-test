package sangcci.springloggingtest.querycounter;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.sql.Connection;
import javax.sql.DataSource;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeansException;

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
}
