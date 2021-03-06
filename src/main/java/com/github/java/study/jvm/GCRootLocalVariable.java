package com.github.java.study.jvm;


public class GCRootLocalVariable {
    private byte[] memory = new byte[100 * 1024 * 1024];

    public static void main(String[] args) {
        System.out.println("Start:");
        MemoryUtil.printMemory();
        method();
        System.gc();
        System.out.println("Second GC finish");
        MemoryUtil.printMemory();
    }

    public static void method() {
        GCRootLocalVariable g = new GCRootLocalVariable();
        System.gc();
        System.out.println("First GC finish");
        MemoryUtil.printMemory();
    }
}
