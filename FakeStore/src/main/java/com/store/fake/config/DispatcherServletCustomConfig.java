package com.store.fake.config;

import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import java.lang.annotation.*;

public class DispatcherServletCustomConfig {

    @Target({ElementType.TYPE})
    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    @Component
    @RequestMapping("api/v1/FakeStore")
    public @interface V1APIController{
        @AliasFor(annotation = Component.class)
        String value() default "dev";
    }
}
