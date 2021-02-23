package example.awsdemo;

import example.awsdemo.common.aws.redis.RedisClientTxImpl;
import example.awsdemo.common.aws.redis.RedisClientTx;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;

@Configuration
public class AwsDemoConfiguration {


    // redisConfigurationで生成したconnectionFactoryをAutowired
    @Autowired
    @Qualifier("connectionFactoryTx")
    private LettuceConnectionFactory connectionFactory1;

    @Bean("redisClientTx")
    RedisClientTx redisClientTx() {
        return new RedisClientTxImpl(connectionFactory1);
    }


}
