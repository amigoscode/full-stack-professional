package com.amigoscode.auth.common.locale;

import org.springframework.context.MessageSource;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.util.StringUtils;

import java.util.Locale;

public class LocaleManager {
    private final Locale locale;
    private final MessageSource messageSource;

    public LocaleManager(String languageCode) {
        if(StringUtils.isEmpty(languageCode)) {
            this.locale = Locale.ENGLISH;
        } else {
            this.locale = new Locale(languageCode);
        }

        this.messageSource = getMessageSource();
    }

    public LocaleManager() {
        this(Locale.ENGLISH.getLanguage());
    }

    public MessageSource getMessageSource() {
        ReloadableResourceBundleMessageSource messageSource
                = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("classpath:messages");
        messageSource.setDefaultEncoding("UTF-8");

        return messageSource;
    }

    public String translate(String messageCode, Object[] args, String defaultMessage, Locale locale) {
        String translatedMessage = messageSource.getMessage(messageCode, args, defaultMessage, locale == null? this.locale: locale);
        if(translatedMessage == null) {
            if(defaultMessage == null) {
                return messageCode;
            }
            return defaultMessage;
        }

        return translatedMessage;
    }

    public String translate(String messageCode, Object[] args, String defaultMessage) {
        return translate(messageCode, args, defaultMessage, null);
    }
    public String translate(String messageCode, Object[] args) {
        return translate(messageCode, args, null, null);
    }

    public String translate(String messageCode, String defaultMessage) {
        return translate(messageCode, null, defaultMessage, null);
    }

    public String translate(String messageCode) {
        return translate(messageCode, null, null, null);
    }

}
