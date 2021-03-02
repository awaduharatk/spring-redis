package example.awsdemo;

import example.awsdemo.common.aws.redis.RedisClientTx;
import example.awsdemo.common.aws.redis.RedisClientTxImpl;
import example.awsdemo.common.aws.redis.RedisConfigurationMaster;
import example.awsdemo.common.aws.redis.RedisConfigurationTx;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;

import java.time.Duration;

@Configuration
@Import({RedisConfigurationTx.class, RedisConfigurationMaster.class})
public class AwsDemoConfiguration {

    Duration connectionTimeout = Duration.ofMillis(1000);

    Duration socketTimeout = Duration.ofMillis(1000);

    String host = "127.0.0.1";

    Integer port1 = 7000;

    Integer port2 = 17000;


    @Autowired
    LettuceConnectionFactory connectionFactoryMaster1;

    @Bean("redisClientMaster1")
    RedisClientTx redisClientMaster1(LettuceConnectionFactory connectionFactoryMaster1) {
        return new RedisClientTxImpl(connectionFactoryMaster1);
    }

    @Autowired
    LettuceConnectionFactory connectionFactoryMaster2;

    @Bean("redisClientMaster2")
    RedisClientTx redisClientMaster2(LettuceConnectionFactory connectionFactoryMaster2) {
        return new RedisClientTxImpl(connectionFactoryMaster2);
    }
    // redisConfigurationで生成したconnectionFactoryをAutowired
//    @Autowired
//    @Qualifier("connectionFactoryTx")
//    private LettuceConnectionFactory connectionFactory1;


}
