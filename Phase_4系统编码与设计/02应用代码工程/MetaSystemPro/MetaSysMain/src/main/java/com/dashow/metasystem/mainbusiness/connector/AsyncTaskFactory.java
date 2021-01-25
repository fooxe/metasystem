package com.dashow.metasystem.main.connector;

import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

/**
 * MetaSystemPro
 *
 * @author: Create AsyncTaskFactory  by Fuqifeng on 2021 2021/1/23;
 * Function:
 */
@EnableAsync
@Configuration
public class AsyncTaskFactory implements AsyncConfigurer {
    @Override
    public Executor getAsyncExecutor() {
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setCorePoolSize(5);// 最小线程数
        taskExecutor.setMaxPoolSize(10);// 最大线程数
        taskExecutor.setQueueCapacity(25);// 等待队列
        taskExecutor.initialize();
        return taskExecutor;
    }
    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return null;
    }
}