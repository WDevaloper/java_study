package com.github.java.study.jvm;

public class RefObject {
    private static final int _1MB = 1024 * 1024;
    byte[] memory;

    public RefObject(int size) {
        memory = new byte[size * _1MB];
    }
}
