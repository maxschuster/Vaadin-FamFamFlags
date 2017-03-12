/*
 * Copyright 2017 mschuster.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package eu.maxschuster.vaadin.famfamflags.demo;

import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.Resource;
import eu.maxschuster.vaadin.famfamflags.FamFamFlags;
import java.io.Serializable;
import java.util.Locale;

/**
 *
 * @author mschuster
 */
public class LocaleRow implements Serializable {
    
    private final Locale locale;
    private final Resource icon;
    private final String displayCountry;
    private final String displayLanguage;
    private final String countryCode;

    public LocaleRow(Locale locale, Locale baseLocale) {
        Resource localeIcon = FamFamFlags.fromLocale(locale);
        if (localeIcon == null) {
            localeIcon = VaadinIcons.QUESTION_CIRCLE;
        } 
        this.locale = locale;
        this.icon = localeIcon;
        this.displayCountry = locale.getDisplayCountry(baseLocale);
        this.displayLanguage = locale.getDisplayLanguage(baseLocale);
        this.countryCode = locale.getCountry();
    }

    public Locale getLocale() {
        return locale;
    }

    public Resource getIcon() {
        return icon;
    }

    public String getDisplayCountry() {
        return displayCountry;
    }

    public String getDisplayLanguage() {
        return displayLanguage;
    }

    public String getCountryCode() {
        return countryCode;
    }
    
}
