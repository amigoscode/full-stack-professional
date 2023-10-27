package com.amigoscode.auth.common.log;

public class LogContextHolder {
    private static final ThreadLocal<LogContextData> threadLocal = new ThreadLocal<>();

    public static void set(LogContextData logContextData) {
        threadLocal.set(logContextData);
    }

    public static LogContextData get() {
        LogContextData logContextData = threadLocal.get();
        if(logContextData == null) {
            return new LogContextData();
        }

        return new LogContextData(logContextData.getContextsAsString(), logContextData.getParamsAsString());
    }

    public static void clear() {
        threadLocal.remove();
    }
}
