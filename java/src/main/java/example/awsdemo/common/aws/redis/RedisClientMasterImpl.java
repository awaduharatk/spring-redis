//package example.awsdemo.common.aws.redis;
//
//import org.springframework.data.redis.connection.RedisClusterConfiguration;
//import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
//import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.data.redis.serializer.StringRedisSerializer;
//
//import io.lettuce.core.ClientOptions;
//import io.lettuce.core.SocketOptions;
//import lombok.AllArgsConstructor;
//import lombok.Builder;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//import lombok.NonNull;
//import lombok.extern.slf4j.Slf4j;
//
//import java.time.Duration;
//
//@Data
//@NoArgsConstructor
//@AllArgsConstructor
//@Builder
//@Slf4j
//public class RedisClientMasterImpl<T> implements RedisClientMaster {
//
//    private RedisTemplate<String, Object> redisTemplateAvro;
//
//
//    /**
//     * Configurationで生成したconnectionFactory
//     */
//    @NonNull
//    private LettuceConnectionFactory connectionFactory;
//
//    /** 共通プロパティ */
////	@Autowired
////	SpiBatchProperties prop;
//
//    /**
//     * 更新面番号
//     */
//    @NonNull
//    private Integer surfaceNo;
//
//    /**
//     * Avroのクラス
//     */
//    @NonNull
//    private Class<T> clazz;
//
//    /**
//     * Avroのスキーマー名
//     */
//    @NonNull
//    private String schemaName;
//
//    /**
//     * RedisTemplate取得.
//     *
//     * @param clazz valueのクラス
//     * @return valueがAvroのRedisTemplate
//     */
//    @SuppressWarnings({"rawtypes", "unchecked"})
//    synchronized <T> RedisTemplate<String, Object> getRedisTemplateAvro(Class<T> clazz, String schemaName) {
//        if (this.redisTemplateAvro == null) {
//
//            RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
//            redisTemplate.setConnectionFactory(getConnectionFactory());
//
//            redisTemplate.setKeySerializer(new StringRedisSerializer());
//            redisTemplate.setValueSerializer(new AvroRedisSerializer(clazz, schemaName));
//            this.redisTemplateAvro = redisTemplate;
//        }
//        return this.redisTemplateAvro;
//    }
//
//    /**
//     * REDISにavro形式で登録.
//     *
//     * @param <T>
//     * @param key
//     * @return ファイルパス
//     */
//    @Override
//    public <Y> void setAvro(String key, Y value) {
//        getRedisTemplateAvro().opsForValue().set(key, value);
//    }
//
//}
