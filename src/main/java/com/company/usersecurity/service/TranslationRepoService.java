package com.company.usersecurity.service;

import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.stereotype.Service;

import java.util.Locale;

@RequiredArgsConstructor
@Service
public class TranslationRepoService {
    private final MessageSource messageSource;

    public String findByKey(String key, String lang, Object... arguments) {
        try {
            return messageSource.getMessage(key, arguments, new Locale(lang));
        } catch (NoSuchMessageException ex) {
            return "";
        }
    }
}
