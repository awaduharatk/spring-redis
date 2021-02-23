//package example.awsdemo.common.aws.redis;
//
//import java.nio.charset.Charset;
//import java.nio.charset.StandardCharsets;
//import java.util.List;
//
//import org.springframework.data.redis.serializer.RedisSerializer;
//import org.springframework.data.redis.serializer.SerializationException;
//import org.springframework.lang.Nullable;
//
//public class AvroRedisSerializer<T> implements RedisSerializer<T> {
//
//	public static final Charset DEFAULT_CHARSET = StandardCharsets.UTF_8;
//
//	private Class<T> clazz;
//
//	private String schemaName;
//
//	public AvroRedisSerializer(Class<T> clazz, String schemaName) {
//		this.clazz = clazz;
//		this.schemaName = schemaName;
//	}
//
//	public T deserialize(@Nullable byte[] bytes) throws SerializationException {
//
//		if (isEmpty(bytes)) {
//			return null;
//		}
//		try {
//			return (T) AvroSerialization.deSerializationToObj(bytes, clazz);
//		} catch (Exception ex) {
//			throw new SerializationException("Could not read byte[]: " + ex.getMessage(), ex);
//		}
//	}
//
//	@SuppressWarnings("unchecked")
//	@Override
//	public byte[] serialize(@Nullable Object t) throws SerializationException {
//
//		if (t == null) {
//			return new byte[0];
//		}
//		try {
//			return AvroSerialization.serializationToByteArray((T) t, clazz, schemaName);
//		} catch (Exception ex) {
//			throw new SerializationException("Could not write byte[]: " + ex.getMessage(), ex);
//		}
//	}
//
//	public byte[] serialize(@Nullable List<T> tList) throws SerializationException {
//
//		if (tList == null) {
//			return new byte[0];
//		}
//		try {
//			return AvroSerialization.serializeListToByteArray(tList, clazz, schemaName);
//		} catch (Exception ex) {
//			throw new SerializationException("Could not write byte[]: " + ex.getMessage(), ex);
//		}
//	}
//
//	private boolean isEmpty(@Nullable byte[] data) {
//		return (data == null || data.length == 0);
//	}
//}
