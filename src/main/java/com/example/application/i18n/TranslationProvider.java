package com.example.application.i18n;

import com.vaadin.flow.i18n.I18NProvider;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class TranslationProvider implements I18NProvider {

    @Override
    public List<Locale> getProvidedLocales() {
        return Arrays.asList(new Locale("fi"), new Locale("en"));
    }

    @Override
    public String getTranslation(String key, Locale locale, Object... params) {
        if (locale.getLanguage().equals("fi")) {
            return getFinnishTranslation(key);
        }
        return getEnglishTranslation(key);
    }

    private String getFinnishTranslation(String key) {
        Map<String, String> translations = new HashMap<>();
        translations.put("app.title", "Pesäpallo Tilastot");
        translations.put("menu.players", "Pelaajat");
        translations.put("menu.statistics", "Tilastot");
        translations.put("menu.teams", "Joukkueet");
        return translations.getOrDefault(key, key);
    }

    private String getEnglishTranslation(String key) {
        Map<String, String> translations = new HashMap<>();
        translations.put("app.title", "Baseball Statistics");
        translations.put("menu.players", "Players");
        translations.put("menu.statistics", "Statistics");
        translations.put("menu.teams", "Teams");
        return translations.getOrDefault(key, key);
    }
} 