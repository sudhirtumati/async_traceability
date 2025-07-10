package org.example.util;

import org.slf4j.MDC;
import org.springframework.core.task.TaskDecorator;

import java.util.Map;

public class MDCTaskDecorator implements TaskDecorator {

    @Override
    public Runnable decorate(Runnable runnable) {
        Map<String, String> callerContext = MDC.getCopyOfContextMap();
        return () -> {
            try {
                if (callerContext != null) {
                    MDC.setContextMap(callerContext);
                }
                runnable.run();
            } finally {
                MDC.clear();
            }
        };
    }

}
