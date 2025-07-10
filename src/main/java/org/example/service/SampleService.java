package org.example.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class SampleService {

    private final HttpBinClient client;

    public SampleService(HttpBinClient client) {
        this.client = client;
    }

    public void doSomethingWithContextPropagatingTaskDecorator() {
        client.doSomethingWithContextPropagatingTaskDecorator();
    }

    public void doSomethingWithCustomDecoratorTaskExecutor() {
        client.doSomethingWithCustomDecoratorTaskExecutor();
    }

    public void doSomethingWithContextExecutorService() {
        client.doSomethingWithContextExecutorService();
    }

}
