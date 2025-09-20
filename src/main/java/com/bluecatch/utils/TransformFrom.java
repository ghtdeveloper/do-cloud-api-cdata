package com.bluecatch.utils;

/*
    Esta FunctionalInterface se utilizara para mapeoar el objeto DTO al entity
 */
@FunctionalInterface
public interface TransformFrom <F,T>{
    T from(F f);
}

