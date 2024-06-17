package com.dmdev.spring.bpp;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

@Component
public class TransactionBeanPostProcessor implements BeanPostProcessor {

    private Map<String, Class<?>> transactionBeans = new HashMap<>();

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (bean.getClass().isAnnotationPresent(Transaction.class)) {
            transactionBeans.put(beanName, bean.getClass());
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        Class<?> beanClazz = transactionBeans.put(beanName, bean.getClass());
        if (beanClazz != null) {
            return Proxy.newProxyInstance(beanClazz.getClassLoader(), beanClazz.getInterfaces(),
                    (proxy, method, args) -> {
                        System.out.println("open transaction");
                        try {
                            return method.invoke(bean, args);
                        } finally {
                            System.out.println("close transaction");
                        }
                    });
        }
        return bean;
    }
}
