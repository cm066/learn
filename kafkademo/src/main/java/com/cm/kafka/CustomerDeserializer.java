package com.cm.kafka;

import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Deserializer;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.util.Map;

/**
 * @author Administrator
 */
public class CustomerDeserializer implements Deserializer<Customer> {

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
        //不需要任何配置
    }

    @Override
    public Customer deserialize(String s, byte[] bytes) {
        int id;
        int nameSize;
        String name;
        if (bytes == null){
            return null;
        }
        if (bytes.length < 8){
            throw new SerializationException("Size of data received by IntegerDe is ");
        }
        ByteBuffer wrap = ByteBuffer.wrap(bytes);
        id = wrap.getInt();
        nameSize = wrap.getInt();
        byte[] nameBytes = new byte[nameSize];
        wrap.get(nameBytes);
        try {
            name = new String(nameBytes,"UTF-8");
            return new Customer(id,name);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
