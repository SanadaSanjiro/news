package com.springbox.news.utils;

import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;

import java.lang.reflect.Field;
import java.util.Objects;

@UtilityClass
public class BeanUtils {

    /**
     * Копирует ненулевые поля из одного объекта в другой
     * @param source Object - источник данных
     * @param dest Object, в который копируются данные
     */
    @SneakyThrows
    public void copyNonNullProperties(Object source, Object dest) {
        Class<?> clazz = source.getClass();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            Object value = field.get(source);

            if (Objects.nonNull(value)) {
                field.set(dest, value);
            }
        }
    }
}
