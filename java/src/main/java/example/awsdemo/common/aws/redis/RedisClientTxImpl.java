package example.awsdemo.common.aws.redis;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.aspectj.EnableSpringConfigured;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericToStringSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import com.fasterxml.jackson.core.JsonProcessingException;

import lombok.Data;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Slf4j
@EnableSpringConfigured
public class RedisClientTxImpl implements RedisClientTx {

    private RedisTemplate<String, String> redisTemplateString;

    private RedisTemplate<String, Integer> redisTemplateInteger;

    private LettuceConnectionFactory connectionFactory1;

    public RedisClientTxImpl(LettuceConnectionFactory connectionFactory1) {
        this.connectionFactory1 = connectionFactory1;
    }

    private RedisTemplate<String, String> getRedisTemplateString() {
        if (redisTemplateString == null) {
            RedisTemplate<String, String> redisTemplate = new RedisTemplate<>();
            redisTemplate.setConnectionFactory(connectionFactory1);
            redisTemplate.setKeySerializer(new StringRedisSerializer());
            redisTemplate.setHashKeySerializer(new StringRedisSerializer());
            redisTemplate.afterPropertiesSet();
            this.redisTemplateString = redisTemplate;
        }
        return this.redisTemplateString;
    }

    private RedisTemplate<String, Integer> getRedisTemplateInteger() {
        if (redisTemplateInteger == null) {
            RedisTemplate<String, Integer> redisTemplate = new RedisTemplate<>();
            redisTemplate.setConnectionFactory(connectionFactory1);
            redisTemplate.setKeySerializer(new StringRedisSerializer());
            redisTemplate.setValueSerializer(new GenericToStringSerializer<>(Integer.class));
            redisTemplate.afterPropertiesSet();
            this.redisTemplateInteger = redisTemplate;
        }
        return this.redisTemplateInteger;
    }

    /**
     * REDISにキャッシュデータを登録.
     *
     * @param key   キー
     * @param value 値
     * @param ttl   ttl(分)
     * @throws JsonProcessingException Jsonシリアライズエラー
     */
    @Override
    public void setJson(String key, Object value, long ttl) throws JsonProcessingException {
//        getRedisTemplateString().opsForValue().set(key, JsonUtil.serialize(value));
//        getRedisTemplateString().expire(key, ttl, TimeUnit.MINUTES);
    }

    /**
     * REDISにキャッシュデータを登録.
     *
     * @param key   キー
     * @param value 値
     * @throws JsonProcessingException Jsonシリアライズエラー
     */
    @Override
    public void setJson(String key, Object value) throws JsonProcessingException {
//        getRedisTemplateString().opsForValue().set(key, JsonUtil.serialize(value));
    }

    @Override
    public void setString(String key, String value) {
        getRedisTemplateString().opsForValue().set(key, value);
    }

    @Override
    public void setInteger(String key, Integer value) {
        getRedisTemplateInteger().opsForValue().set(key, value);
    }

    /**
     * REDISから更新面番号を取得.
     *
     * @return 更新面番号
     */
    @Override
    public Integer getNewestMasterCacheNo() {
        Integer nowNo = getRedisTemplateInteger().opsForValue().get(1);
        if (nowNo == null) {
            return null;
        } else if (nowNo.equals(1)) {
            return 2;
        } else if (nowNo.equals(2)) {
            return 1;
        } else {
            // 上記以外の場合
            return null;
        }
    }

    /**
     * REDISからファイルパスを取得.
     *
     * @param key
     * @return ファイルパス
     */
    @Override
    public String getFilePath(String key) {
        return getRedisTemplateString().opsForValue().get(key);
    }

}
