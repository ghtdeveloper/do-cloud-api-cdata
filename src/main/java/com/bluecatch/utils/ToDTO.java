package com.bluecatch.utils;

/*
    Esta FunctionalInterface se utilizara para mapear el entity a DTO
 */
@FunctionalInterface
public interface ToDTO<T> {
    T toDto();
}
