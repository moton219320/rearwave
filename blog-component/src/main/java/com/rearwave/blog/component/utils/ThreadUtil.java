package com.rearwave.blog.component.utils;

import io.netty.util.concurrent.DefaultThreadFactory;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.concurrent.*;

/**
 * @author sunyi
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ThreadUtil {

    private static final Executor EXECUTOR;

    static{
        BlockingQueue<Runnable> queue = new ArrayBlockingQueue<>(100);
        ThreadFactory factory = new DefaultThreadFactory("blog-pool",true,1);
        EXECUTOR = new ThreadPoolExecutor(10,
                100,
                6,
                TimeUnit.MINUTES,
                queue,
                factory);
    }

    /**
     * 执行线程
     * @param runnable 被执行线程
     */
    public static void exec(Runnable runnable){
        EXECUTOR.execute(runnable);
    }
}
