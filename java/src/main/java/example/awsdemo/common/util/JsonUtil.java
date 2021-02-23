package example.awsdemo.common.util;


import java.io.IOException;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public abstract class JsonUtil {

    private JsonUtil() {
        super();
    }

    private static final ObjectMapper objectMapper = createDefaultObjectMapper();

    /**
     * 本システムで推奨する{@link ObjectMapper}を生成します.
     *
     * @return
     */
    public static ObjectMapper createDefaultObjectMapper() {
        ObjectMapper objectMapper = new ObjectMapper()
                // Javaにバインド先がないJson項目があった場合にエラーとしないで無視する
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                // NULLの項目は項目ごと出力しない
                .setSerializationInclusion(JsonInclude.Include.NON_NULL)
                // 日付型の値をタイムスタンプ形式でシリアライズする機能を無効化
                .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
                // タイムゾーン情報を含む日付型の値をUTCに変換してデシリアライズする機能を無効化
                .disable(DeserializationFeature.ADJUST_DATES_TO_CONTEXT_TIME_ZONE);
        objectMapper.registerModule(new JavaTimeModule());
        return objectMapper;
    }

    public static String serialize(Object obj) throws JsonProcessingException {
        return objectMapper.writeValueAsString(obj);
    }

    public static <T> T deserialize(String content, Class<T> valueType) throws IOException {
        return objectMapper.readValue(content, valueType);
    }
}
