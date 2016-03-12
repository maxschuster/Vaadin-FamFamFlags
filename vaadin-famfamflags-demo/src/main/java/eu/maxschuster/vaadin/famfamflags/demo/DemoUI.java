package eu.maxschuster.vaadin.famfamflags.demo;

import com.vaadin.annotations.PreserveOnRefresh;
import com.vaadin.annotations.Push;
import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.annotations.Viewport;
import com.vaadin.data.Property;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.Page;
import com.vaadin.server.Resource;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Image;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Table;
import com.vaadin.ui.UI;
import eu.maxschuster.vaadin.famfamflags.FamFamFlags;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import org.vaadin.viritin.ListContainer;

@Theme("demo")
@Title("FamFamFlags - Demo")
@PreserveOnRefresh
@Push
@Viewport("width=device-width, initial-scale=1.0, user-scalable=no")
public class DemoUI extends UI {

    @WebServlet(value = "/*", asyncSupported = true)
    @VaadinServletConfiguration(productionMode = true, ui = DemoUI.class)
    public static class Servlet extends VaadinServlet {
    }

    private final DemoUILayout layout = new DemoUILayout();

    @Override
    protected void init(VaadinRequest request) {
        setContent(layout);

        layout.panel.setIcon(FamFamFlags.FAM);

        List<LocaleRow> localeRows = getAvaliableLocaleRows();

        ListContainer<LocaleRow> localeRowContainer
                = new ListContainer<>(LocaleRow.class, localeRows);
        
        Table table = layout.availableLocales;
        table.setContainerDataSource(localeRowContainer);
        table.addGeneratedColumn("_icon", this::generateIconCell);
        table.setVisibleColumns("_icon", "displayCountry", "displayLanguage", "countryCode");
        table.setColumnHeaders("Flag", "Country", "Language", "ISO 3166");
        table.setSelectable(true);
        table.addValueChangeListener(this::localeChanged);
    }

    private Resource getLocaleIcon(Locale locale) {
        Resource icon = FamFamFlags.fromLocale(locale);
        if (icon == null) {
            icon = FontAwesome.QUESTION_CIRCLE;
        }
        return icon;
    }
    
    private Object generateIconCell(Table source, Object itemId, Object columnId) {
        LocaleRow row = (LocaleRow) itemId;
        return new Image(null, row.getIcon());
    }

    private void localeChanged(Property.ValueChangeEvent event) {
        LocaleRow row = (LocaleRow) event.getProperty().getValue();
        Locale locale = row.getLocale();
        String displayCountry = locale.getDisplayCountry(getLocale());
        String displayLanguage = locale.getDisplayLanguage(getLocale());
        String country = locale.getCountry();
        Notification notification = new Notification(
                displayCountry,
                "The country has changed to \"" + displayCountry + "\"\n"
                + "- Language: " + displayLanguage + "\n"
                + "- Code (ISO 3166-2): " + country + "\n",
                Notification.Type.TRAY_NOTIFICATION
        );
        notification.setIcon(getLocaleIcon(locale));
        notification.show(Page.getCurrent());
    }
    
    private List<LocaleRow> getAvaliableLocaleRows() {
        Locale[] locales = Locale.getAvailableLocales();
        Locale baseLocale = getLocale();
        List<LocaleRow> rows = new ArrayList<>(locales.length);
        for (Locale locale : locales) {
            String country = locale.getCountry();
            if (country != null && !country.isEmpty()) {
                rows.add(new LocaleRow(locale, baseLocale));
            }
        }
        return rows;
    }
    
    public class LocaleRow implements Serializable {
        
        private final Locale locale;
        
        private final Resource icon;
        
        private final String displayCountry;
        
        private final String displayLanguage;
        
        private final String countryCode;

        public LocaleRow(Locale locale, Locale baseLocale) {
            this.locale = locale;
            this.icon = getLocaleIcon(locale);
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

}
