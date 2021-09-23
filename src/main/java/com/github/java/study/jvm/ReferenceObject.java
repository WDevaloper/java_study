package com.github.java.study.jvm;

import java.util.Arrays;

public class ReferenceObject {
    private static final int _1MB = 1024 * 1024;
    byte[] memory;

    public ReferenceObject(int size) {
        memory = new byte[size * _1MB];
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReferenceObject that = (ReferenceObject) o;
        return Arrays.equals(memory, that.memory);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(memory);
    }
}
