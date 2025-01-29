package com.example.application.security;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.server.ErrorEvent;
import com.vaadin.flow.server.ErrorHandler;
import com.vaadin.flow.server.VaadinSession;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Component;

@Component
public class SecurityUtils implements ErrorHandler {

    @Override
    public void error(ErrorEvent event) {
        if (event.getThrowable() instanceof AccessDeniedException) {
            UI.getCurrent().navigate("access-denied");
            return;
        }

        event.getThrowable().printStackTrace();
    }

    public static void setErrorHandler() {
        VaadinSession.getCurrent().setErrorHandler(new SecurityUtils());
    }
} 