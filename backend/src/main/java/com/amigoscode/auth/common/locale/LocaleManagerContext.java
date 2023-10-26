package com.amigoscode.auth.common.locale;

public class LocaleManagerContext {
    private static final ThreadLocal<LocaleManager> localeManagerContext = new ThreadLocal<>();

    public static void set(LocaleManager localeManager) {
        localeManagerContext.set(localeManager);
    }

    public static LocaleManager get() {
        LocaleManager localeManager = localeManagerContext.get();
        if(localeManager == null) {
            return new LocaleManager();
        }

        return localeManager;
    }

    public static void clear() {
        localeManagerContext.remove();
    }
}
