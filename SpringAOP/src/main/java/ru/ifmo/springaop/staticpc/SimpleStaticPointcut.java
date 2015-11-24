package ru.ifmo.springaop.staticpc;

import java.lang.reflect.Method;

import org.springframework.aop.ClassFilter;
import org.springframework.aop.support.StaticMethodMatcherPointcut;

/**
 * Пример создания собственного статического среза. 
 * Проверяются имя метода и класс, из которого этот метод вызывался.
 */
public class SimpleStaticPointcut extends StaticMethodMatcherPointcut {

    @Override
    public boolean matches(Method method, Class<?> cls) {
        return ("foo".equals(method.getName()));
    }

    @Override
    public ClassFilter getClassFilter() {
        return new ClassFilter() {
            @Override
            public boolean matches(Class<?> cls) {
                return (cls == BeanOne.class);
            }
        };

    }
}