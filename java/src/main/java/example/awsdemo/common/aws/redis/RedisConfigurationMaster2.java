package example.awsdemo.common.aws.redis;//package example.awsdemo.common.aws.redis;

import io.lettuce.core.ClientOptions;
import io.lettuce.core.SocketOptions;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;

import java.time.Duration;

@EnableCaching
public class RedisConfigurationMaster2 {

    Duration connectionTimeout = Duration.ofMillis(1000);

    Duration socketTimeout = Duration.ofMillis(1000);

    String host = "127.0.0.1";

    Integer port1 = 7000;

    Integer port2 = 17000;

    @Primary
    @Bean(name = "connectionFactoryMaster2")
    LettuceConnectionFactory connectionFactoryMaster2() {
        final SocketOptions socketOptions = SocketOptions.builder()
                .connectTimeout(socketTimeout).build();
        final ClientOptions clientOptions = ClientOptions.builder().socketOptions(socketOptions).build();
        LettuceClientConfiguration lettuceClientConfiguration = LettuceClientConfiguration.builder()
                .commandTimeout(connectionTimeout).clientOptions(clientOptions).build();

        LettuceConnectionFactory connectionFactory = new LettuceConnectionFactory(
                new RedisClusterConfiguration().clusterNode(host, port2
                ), lettuceClientConfiguration);
        connectionFactory.setDatabase(1);
        return connectionFactory;
    }




}
