package com.notes.study.spring.conditionalDemo;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

public class MacCondition implements Condition {
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        System.out.println(context.getEnvironment().getProperty("os.name"));
        return context.getEnvironment().getProperty("os.name").contains("Mac");
    }
}
