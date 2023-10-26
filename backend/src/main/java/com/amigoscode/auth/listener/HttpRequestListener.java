package com.amigoscode.auth.listener;

import com.amigoscode.auth.common.CustomerContextHolder;
import com.amigoscode.auth.common.locale.LocaleManagerContext;
import com.amigoscode.auth.common.log.LogContextHolder;
import jakarta.servlet.ServletRequestEvent;
import jakarta.servlet.ServletRequestListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class HttpRequestListener implements ServletRequestListener {
    @Override
    public void requestInitialized(ServletRequestEvent servletRequestEvent) {}
    @Override
    public void requestDestroyed(ServletRequestEvent servletRequestEvent) {
        clearThreadLocal();
    }

    private void clearThreadLocal() {
        LocaleManagerContext.clear();
        CustomerContextHolder.clear();
        LogContextHolder.clear();
    }
}
