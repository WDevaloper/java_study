package com.github.java.study.jvm;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;

public class ReferenceQueueTest {
    private static volatile boolean isShutdown = false;
    private static Map<Object, ReferenceObjectWeakReference> cache = new HashMap<>();
    private static ReferenceQueue<ReferenceObject> referenceQueue = new ReferenceQueue<>();

    public static void main(String[] args) throws InterruptedException {
        MemoryUtil.printMemory("Main method run Start");

        ReferenceObject referenceObject10M;
        ReferenceObjectWeakReference weakReference;

        for (int i = 0; i < 3; i++) {
            referenceObject10M =
                    new ReferenceObject(i + 2);//2 + 3 + 4

            int hashCode = referenceObject10M.hashCode();
            weakReference =
                    new ReferenceObjectWeakReference(hashCode, referenceObject10M, referenceQueue);

            cache.put(hashCode, weakReference);
        }

        referenceObject10M = null;
        weakReference = null;


        // 开启后台线程监控资源回收
        Thread thread = new Thread(() -> {
            while (!isShutdown) {
                try {
                    ReferenceObjectWeakReference reference = (ReferenceObjectWeakReference) referenceQueue.remove();
                    cache.remove(reference.key);
                    MemoryUtil.printMemory("Object is recycled and Join RefQueue");
                } catch (InterruptedException e) {
                    e.printStackTrace(); 
                }
            }
            MemoryUtil.printLog("Monitor thread finish");
        });

        thread.setDaemon(true);
        thread.setName("GC Monitor");
        thread.start();

        MemoryUtil.printMemory("GC before");
        Thread.sleep(1000);
        System.gc();
        Thread.sleep(5000);
        isShutdown = true;
        MemoryUtil.printMemory("GC after");
        MemoryUtil.printMemory("Main method run finish : cache size:  " + cache.size());
    }


    static class ReferenceObjectWeakReference extends WeakReference<ReferenceObject> {
        public int key;

        public ReferenceObjectWeakReference(
                int key,
                ReferenceObject referent,
                ReferenceQueue<? super ReferenceObject> q) {
            super(referent, q);
            this.key = key;
        }
    }
}
