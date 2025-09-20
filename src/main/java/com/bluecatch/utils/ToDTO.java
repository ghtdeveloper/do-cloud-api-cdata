package com.bluecatch.utils;

@FunctionalInterface
public interface ToDTO<T> {
    T toDto();
}
