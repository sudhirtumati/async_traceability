package org.example.service;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@Slf4j
public class HttpBinClient {

    private final RestTemplate restTemplate;

    public HttpBinClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Async("contextPropagatingTaskDecoratorExecutor")
    public void doSomethingWithContextPropagatingTaskDecorator() {
        request();
    }

    @Async("customDecoratorTaskExecutor")
    public void doSomethingWithCustomDecoratorTaskExecutor() {
        request();
        MDC.getCopyOfContextMap();
    }

    @Async("contextExecutorService")
    public void doSomethingWithContextExecutorService() {
        request();
    }

    public void request() {
        ResponseEntity<String> response = restTemplate.getForEntity("https://httpbin.dev/headers", String.class);
        log.info(response.getBody());
    }

}
