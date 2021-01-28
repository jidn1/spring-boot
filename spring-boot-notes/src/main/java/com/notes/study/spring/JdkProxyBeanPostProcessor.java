package com.notes.study.spring;

import com.notes.study.spring.model.InstanceA;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.SmartInstantiationAwareBeanPostProcessor;

public class JdkProxyBeanPostProcessor implements SmartInstantiationAwareBeanPostProcessor {

    /**
     * 假设A被切点命中 需要创建代理  @PointCut("execution(* *..InstanceA.*(..))")
     * @param bean the raw bean instance
     * @param beanName the name of the bean
     * @return
     * @throws BeansException
     */
    @Override
    public Object getEarlyBeanReference(Object bean, String beanName) throws BeansException {

        // 假设A被切点命中 需要创建代理  @PointCut("execution(* *..InstanceA.*(..))")

        /**
         * 这里, 我们简单直接判断bean是不是InstanceA实例, 如果是, 就创建动态代理.
         * 这里没有去解析切点, 解析切点是AspectJ做的事.
         */
        if (bean instanceof InstanceA) {
//            JdkDynamicAopProxy jdkDynimcProxy = new JdkDynamicAopProxy(bean);
//            return jdkDynimcProxy.getProxy();
        }
        return bean;
    }
}
