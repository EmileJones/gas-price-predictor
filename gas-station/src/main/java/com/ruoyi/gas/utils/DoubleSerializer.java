package com.ruoyi.gas.utils;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * 小数序列化器永续讲Double类型的数据序列化为保留两位小数
 * @author KlenKiven
 */
public class DoubleSerializer extends JsonSerializer<Object> {
    @Override
    public void serialize(Object value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        if (value != null) {
            BigDecimal bigDecimal = new BigDecimal(value.toString());
            gen.writeNumber(bigDecimal.divide(BigDecimal.ONE, 2, RoundingMode.DOWN));
        }
    }
}
