package com.notes.study.annontions;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class EnumValidtor implements ConstraintValidator<EnumValidAnnotation, String> {

    /**Validator
     * 枚举类
     */
    Class<?>[] cls;

    @Override
    public void initialize(EnumValidAnnotation enumValidAnnotation) {
        cls = enumValidAnnotation.target();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (cls.length > 0) {
            for (Class<?> cl : cls) {
                try {
                    if (cl.isEnum()) {
                        /**
                         * 	枚举类验证
                         */
                        Object[] objs = cl.getEnumConstants();
                        Method method = cl.getMethod("getType") == null ? cl.getMethod("getDesc") : cl.getMethod("getType");
                        for (Object obj : objs) {
                            String code = obj.toString();
                          //  Object val = method.invoke(obj, null);
                            if (value.equalsIgnoreCase(code)) {

                                return true;
                            }
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } else {
            return true;
        }

        return false;
    }

}
