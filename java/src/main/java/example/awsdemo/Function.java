package example.awsdemo;

import example.awsdemo.common.aws.redis.RedisClientTx;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Function {

    @Autowired
    RedisClientTx redisClientTx;

    @Autowired
    RedisClientTx redisClientMaster1;

    @Autowired
    RedisClientTx redisClientMaster2;



    public String getTx(String key) {
        return redisClientTx.getFilePath(key);
    }

    public String getMaster(String key) {
        return redisClientMaster1.getFilePath(key);
    }

    public String getMaster2(String key) {
        return redisClientMaster1.getFilePath(key);
    }

}
