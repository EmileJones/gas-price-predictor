package com.ruoyi.gas.utils;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
 * 限流器
 *
 * @author KlenKiven
 */
public class RateLimiter {

    /**
     * 间隔时间: 1/QPS
     */
    private static long interval;

    private final Sync sync;

    private static class Sync extends AbstractQueuedSynchronizer {

        volatile long timed;

        public void await() {
            for (; ; ) {
                try {
                    boolean acquireNanos = tryAcquireNanos(1, interval);
                    if (acquireNanos) break;
                    // 超时后重新计算时间
                    if ((timed - System.nanoTime()) < 0L)
                        timed = System.nanoTime() + interval;
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        @Override
        protected boolean tryAcquire(int acquires) {
            int c = getState();
            // 成功 Acquire 后， 重新计算超时时间
            if ((timed - System.nanoTime()) < 0L &&
                    !hasQueuedPredecessors() &&
                    compareAndSetState(c, c + acquires)) {
                timed = System.nanoTime() + interval;
                return true;
            }
            return false;
        }
    }

    public RateLimiter(double qps) {
        interval = Math.round(1000.0 * 1000.0 * 1000.0 / qps);
        sync = new Sync();
    }

    public void limit() {
        sync.await();
    }

}
