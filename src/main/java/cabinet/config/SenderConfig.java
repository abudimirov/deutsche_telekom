package cabinet.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import javax.jms.ConnectionFactory;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Properties;
import org.springframework.jms.core.JmsTemplate;

/**
 * This class is used for configuring connection to ActiveMQ
 *
 */
@Configuration
@ComponentScan(basePackages = "cabinet")
public class SenderConfig {

    // Set up all the default values
    private static final String DEFAULT_CONNECTION_FACTORY = "jms/RemoteConnectionFactory";

    private static final String DEFAULT_USERNAME = "quickstartUser";

    private static final String DEFAULT_PASSWORD = "quickstartPwd1!";

    private static final String INITIAL_CONTEXT_FACTORY
            = "org.jboss.naming.remote.client.InitialContextFactory";

    private static final String DEFAULT_DESTINATION = "testQueue";

    private static final String PROVIDER_URL = "http-remoting://127.0.0.1:8180";

    Context namingContext = null;

    @Bean
    public ConnectionFactory connectionFactory() throws NamingException {
        final Properties env = new Properties();
        env.put(Context.INITIAL_CONTEXT_FACTORY, INITIAL_CONTEXT_FACTORY);
        env.put(Context.PROVIDER_URL, System.getProperty(Context.PROVIDER_URL, PROVIDER_URL));
        env.put(Context.SECURITY_PRINCIPAL, DEFAULT_USERNAME);
        env.put(Context.SECURITY_CREDENTIALS, DEFAULT_PASSWORD);

        namingContext = new InitialContext(env);

        ConnectionFactory connectionFactory = (ConnectionFactory) namingContext.lookup(DEFAULT_CONNECTION_FACTORY);

        return connectionFactory;
    }

    @Bean
    public JmsTemplate jmsTemplate() throws NamingException {
        JmsTemplate template = new JmsTemplate();
        template.setConnectionFactory(connectionFactory());
        template.setDefaultDestinationName(DEFAULT_DESTINATION);
        return template;
    }
}
