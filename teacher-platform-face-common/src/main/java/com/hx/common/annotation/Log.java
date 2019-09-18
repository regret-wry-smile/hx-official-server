package com.hx.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *
 *
 * @ClassName: Log
 *
 * @Description: 自定义注解@Log
 *
 * @author: yk
 *
 * @date: 2017年8月26日 下午11:19:12
 */
// 声明可以使用该注解的目标类型
@Target(ElementType.METHOD)
// 声明注解的保留期限
@Retention(RetentionPolicy.RUNTIME)
public @interface Log {
	String value() default "";
	Module module() default Module.sys;
}
