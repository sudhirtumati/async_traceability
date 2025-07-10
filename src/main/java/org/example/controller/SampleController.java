package org.example.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.service.SampleService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class SampleController {

    private final SampleService service;

    public SampleController(SampleService service) {
        this.service = service;
    }

    @GetMapping("/contextPropagatingTaskDecorator")
    @ResponseStatus(HttpStatus.OK)
    public void contextPropagatingTaskDecorator() {
        log.info("Before invoking the service with cpdt");
        service.doSomethingWithContextPropagatingTaskDecorator();
        log.info("After invoking the service with cpdt");
    }

    @GetMapping("/customDecoratorTaskExecutor")
    @ResponseStatus(HttpStatus.OK)
    public void customDecoratorTaskExecutor() {
        log.info("Before invoking the service with cdt");
        service.doSomethingWithCustomDecoratorTaskExecutor();
        log.info("After invoking the service with cdt");
    }

    @GetMapping("/contextExecutorService")
    @ResponseStatus(HttpStatus.OK)
    public void contextExecutorService() {
        log.info("Before invoking the service with ces");
        service.doSomethingWithContextExecutorService();
        log.info("After invoking the service with ces");
    }

}
