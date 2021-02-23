package example.awsdemo.common.aws.redis;

import com.fasterxml.jackson.core.JsonProcessingException;

public interface RedisClientTx {

	void setString(String key, String value);

	void setInteger(String key, Integer value);

	void setJson(String key, Object value, long ttl) throws JsonProcessingException;

	void setJson(String key, Object value) throws JsonProcessingException;

	Integer getNewestMasterCacheNo();

	String getFilePath(String key);

}
