package com.audibene.provider;

import javax.ws.rs.ext.ParamConverter;
import javax.ws.rs.ext.ParamConverterProvider;
import javax.ws.rs.ext.Provider;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.time.OffsetDateTime;

@Provider
public class OffsetDateTimeProvider implements ParamConverterProvider {

    @Override
    public <T> ParamConverter<T> getConverter(Class<T> clazz, Type type, Annotation[] annotations) {
        if (clazz.getName().equals(OffsetDateTime.class.getName())) {

            return new ParamConverter<T>() {

                @SuppressWarnings("unchecked")
                @Override
                public T fromString(String value) {
                    OffsetDateTime time = OffsetDateTime.parse(value);
                    return (T) time;
                }

                @Override
                public String toString(T time) {
                    return time.toString();
                }
            };
        }
        return null;
    }
}
