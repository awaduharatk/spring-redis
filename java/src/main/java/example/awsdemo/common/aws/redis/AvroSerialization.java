//package example.awsdemo.common.aws.redis;
//
//import java.io.ByteArrayOutputStream;
//import java.io.File;
//import java.io.IOException;
//import java.net.URL;
//import java.util.ArrayList;
//import java.util.List;
//
//import org.apache.avro.Schema;
//import org.apache.avro.Schema.Parser;
//import org.apache.avro.Schema.Type;
//import org.apache.avro.file.DataFileReader;
//import org.apache.avro.file.DataFileWriter;
//import org.apache.avro.file.SeekableByteArrayInput;
//import org.apache.avro.io.DatumReader;
//import org.apache.avro.io.DatumWriter;
//import org.apache.avro.specific.SpecificDatumReader;
//import org.apache.avro.specific.SpecificDatumWriter;
//
//public class AvroSerialization {
//	/**
//	 *
//	 * @param <T>
//	 * @param obj
//	 * @param clazz
//	 * @param schemaName
//	 * @return バイト配列
//	 * @throws IOException
//	 */
//	public static <T> byte[] serializationToByteArray(T obj, Class<T> clazz, String schemaName) throws IOException {
//		ByteArrayOutputStream baos = new ByteArrayOutputStream();
//		DatumWriter<T> userDatumWriter = new SpecificDatumWriter<T>(clazz);
//		DataFileWriter<T> dataFileWriter = new DataFileWriter<T>(userDatumWriter);
//		Schema schema = null;
//		URL userResource = null;
//		if ("String".equals(schemaName) || "string".equals(schemaName)) {
//			// schemaNameがStringの場合、
//			schema = Schema.create(Type.STRING);
//		} else {
//			// それ以外の場合、shemaファイルを読み取る
//			userResource = AvroSerialization.class.getResource("/" + schemaName);
//			schema = new Parser().parse(new File(userResource.getPath()));
//		}
//		dataFileWriter.create(schema, baos);
//		dataFileWriter.append(obj);
//		dataFileWriter.close();
//		return (baos.toByteArray());
//	}
//
//	/**
//	 *
//	 * @param <T>
//	 * @param tList
//	 * @param clazz
//	 * @param schemaName
//	 * @returnバイト配列
//	 * @throws IOException
//	 */
//	public static <T> byte[] serializeListToByteArray(List<T> tList, Class<T> clazz, String schemaName)
//			throws IOException {
//		ByteArrayOutputStream baos = new ByteArrayOutputStream();
//		DatumWriter<T> userDatumWriter = new SpecificDatumWriter<T>(clazz);
//		DataFileWriter<T> dataFileWriter = new DataFileWriter<T>(userDatumWriter);
//		URL userResource = AvroSerialization.class.getResource("/" + schemaName);
//		Schema schema = new Parser().parse(new File(userResource.getPath()));
//		dataFileWriter.create(schema, baos);
//		for (T t : tList) {
//			dataFileWriter.append(t);
//		}
//		dataFileWriter.close();
//		return (baos.toByteArray());
//	}
//
//	/**
//	 * デシリアライズ
//	 * @param <T>
//	 * @param bytes
//	 * @param clazz
//	 * @returnオブジェクト
//	 */
//	public static <T> T deSerializationToObj(byte[] bytes, Class<T> clazz) {
//		SeekableByteArrayInput sbai = null;
//		sbai = new SeekableByteArrayInput((bytes));
//		DatumReader<T> userDatumReader = new SpecificDatumReader<T>(clazz);
//		DataFileReader<T> dataFileReader = null;
//		T readUser = null;
//		try {
//			dataFileReader = new DataFileReader<T>(sbai, userDatumReader);
//
//			while (dataFileReader.hasNext()) {
//				readUser = dataFileReader.next(readUser);
//			}
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		return readUser;
//	}
//
//	/**
//	 * デシリアライズList
//	 * @param <T>
//	 * @param bytes
//	 * @param clazz
//	 * @return List<T>
//	 */
//	public static <T> List<T> deSerializationToList(byte[] bytes, Class<T> clazz) {
//		SeekableByteArrayInput sbai = null;
//		sbai = new SeekableByteArrayInput((bytes));
//		DatumReader<T> userDatumReader = new SpecificDatumReader<T>(clazz);
//		DataFileReader<T> dataFileReader = null;
//		T readUser = null;
//		List<T> tList = new ArrayList<T>();
//		try {
//			dataFileReader = new DataFileReader<T>(sbai, userDatumReader);
//
//			while (dataFileReader.hasNext()) {
//				readUser = null;
//				readUser = dataFileReader.next(readUser);
//				tList.add(readUser);
//			}
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		return tList;
//	}
//
//}
