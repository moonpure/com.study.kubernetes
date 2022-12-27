package com.study.kubernete.user.copy;

import org.springframework.cglib.core.Converter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.study.kubernete.user.sensitive.ClassTool;
public class DeepCopyConverter implements Converter {

    /**
     * The Target.
     */
    private Class<?> target;

    /**
     * Instantiates a new Deep copy converter.
     *
     * @param target
     *            the target
     */
    public DeepCopyConverter(Class<?> target) {
        this.target = target;
    }

    @Override
    public Object convert(Object value, Class targetClazz, Object methodName) {
        if (value instanceof List) {
            List values = (List) value;
            List retList = new ArrayList<>(values.size());
            for (final Object source : values) {
                String tempFieldName = methodName.toString().replace("set",
                        "");
                String fieldName = tempFieldName.substring(0, 1)
                        .toLowerCase() + tempFieldName.substring(1);
                Class clazz = ClassTool.getElementType(target, fieldName);
                retList.add(BeanCopierTool.convert(source, clazz));
            }
            return retList;
        } else if (value instanceof Map) {
            // TODO 暂时用不到，后续有需要再补充
        } else if (!ClassTool.isPrimitive(targetClazz)) {
            return BeanCopierTool.convert(value, targetClazz);
        }
        return value;
    }
}
