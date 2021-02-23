package example.awsdemo;

import example.awsdemo.common.aws.redis.RedisClientTx;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.Duration;


@Component
public class SpringRunner implements CommandLineRunner {

    @Autowired
    RedisClientTx redisClientTx;

    @Override
    public void run(String... args) {
        System.out.println("hello");

        System.out.println(redisClientTx.getFilePath("test"));

        System.exit(0);
    }


}