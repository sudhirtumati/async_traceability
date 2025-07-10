package org.example.config;

import io.micrometer.context.ContextExecutorService;
import io.micrometer.context.ContextRegistry;
import io.micrometer.context.ContextSnapshotFactory;
import org.example.util.MDCTaskDecorator;
import org.slf4j.MDC;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.support.ContextPropagatingTaskDecorator;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.Map;
import java.util.concurrent.Executor;

@Configuration
@EnableAsync
public class AsyncConfig {

    @Bean(name = "contextPropagatingTaskDecoratorExecutor")
    Executor contextPropagatingTaskDecoratorExecutor() {
        var executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(5);
        executor.setMaxPoolSize(10);
        executor.setWaitForTasksToCompleteOnShutdown(true);
        executor.setTaskDecorator(new ContextPropagatingTaskDecorator(
                ContextSnapshotFactory.builder().contextRegistry(
                        ContextRegistry.getInstance()).build()));
        executor.initialize();
        return executor;
    }

    @Bean(name = "customDecoratorTaskExecutor")
    Executor customDecoratorTaskExecutor() {
        var executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(5);
        executor.setMaxPoolSize(10);
        executor.setWaitForTasksToCompleteOnShutdown(true);
        executor.setTaskDecorator(new MDCTaskDecorator());
        return executor;
    }

    @Bean(name = "contextExecutorService")
    Executor contextExecutorService() {
        var executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(5);
        executor.setMaxPoolSize(10);
        executor.setWaitForTasksToCompleteOnShutdown(true);
        executor.initialize();
        return ContextExecutorService.wrap(executor.getThreadPoolExecutor(),
                ContextSnapshotFactory.builder().contextRegistry(
                        ContextRegistry.getInstance()).build()::captureAll);
    }

}
