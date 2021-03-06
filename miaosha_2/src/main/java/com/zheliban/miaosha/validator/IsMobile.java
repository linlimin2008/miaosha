package com.zheliban.miaosha.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/*
 * 自定义注解
 * 但是光有注解系统是不知道如何来判断手机号码是否合法，因此还需要一个类作为校验器
 */
@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER})
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = {IsMobileValidator.class})    //指定校验器
public @interface IsMobile {

    boolean required() default true;    //参数是必须的，默认为true，

    String message() default "手机号码格式错误";    //校验不通过的默认提示

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};


}
