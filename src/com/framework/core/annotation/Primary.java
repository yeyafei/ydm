package com.framework.core.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 标识该字段是唯一字段
 * 可以通过该字段来查找唯一数据 
 * 可以通过该字段来删除唯一数据 
 * 可以通过该字段来更新唯一数据
 * 
 * @author yyf
 * 
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Primary {

}
