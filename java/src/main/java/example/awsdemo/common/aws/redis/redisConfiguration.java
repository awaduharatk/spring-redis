package example.awsdemo.common.aws.redis;

import io.lettuce.core.ClientOptions;
import io.lettuce.core.SocketOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.session.data.redis.config.annotation.SpringSessionRedisConnectionFactory;

import java.time.Duration;

@Configuration
public class redisConfiguration {

    Duration connectionTimeout = Duration.ofMillis(1000);

    Duration socketTimeout = Duration.ofMillis(1000);

    String host = "127.0.0.1";

    Integer port1 = 7000;

    Integer port2 = 17000;

    @Bean(name = "connectionFactoryTx")
    @Primary
    LettuceConnectionFactory connectionFactoryTx() {
        final SocketOptions socketOptions = SocketOptions.builder()
                .connectTimeout(socketTimeout).build();
        final ClientOptions clientOptions = ClientOptions.builder().socketOptions(socketOptions).build();
        LettuceClientConfiguration lettuceClientConfiguration = LettuceClientConfiguration.builder()
                .commandTimeout(connectionTimeout).clientOptions(clientOptions).build();

        LettuceConnectionFactory connectionFactory = new LettuceConnectionFactory(
                new RedisClusterConfiguration().clusterNode(host, port1
                ), lettuceClientConfiguration);
        connectionFactory.setDatabase(0);
        return connectionFactory;
    }

    @Bean(name = "connectionFactoryMaster1")
    @SpringSessionRedisConnectionFactory
    LettuceConnectionFactory connectionFactoryMaster1() {
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

    @Bean(name = "connectionFactoryMaster2")
    @SpringSessionRedisConnectionFactory
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
