package eu.maxschuster.vaadin.famfamflags.demo;

import com.vaadin.annotations.PreserveOnRefresh;
import com.vaadin.annotations.Push;
import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.annotations.Viewport;
import com.vaadin.data.HasValue;
import com.vaadin.server.Page;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;
import com.vaadin.ui.renderers.ImageRenderer;
import eu.maxschuster.vaadin.famfamflags.FamFamFlags;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Theme("demo")
@Title("FamFamFlags - Demo")
@PreserveOnRefresh
@Push
@Viewport("width=device-width, initial-scale=1.0, user-scalable=no")
public class DemoUI extends UI {

    @WebServlet(value = "/*", asyncSupported = true)
    @VaadinServletConfiguration(productionMode = false, ui = DemoUI.class)
    public static class Servlet extends VaadinServlet {
    }

    private final DemoUILayout layout = new DemoUILayout();

    @Override
    protected void init(VaadinRequest request) {
        setContent(layout);

        layout.panel.setIcon(FamFamFlags.FAM);

        List<LocaleRow> localeRows = getAvaliableLocaleRows();
        
        Grid<LocaleRow> grid = layout.availableLocales;
        grid.setSelectionMode(Grid.SelectionMode.SINGLE);
        grid.asSingleSelect().addValueChangeListener(this::localeChanged);
        grid.setItems(localeRows);
        
        grid.addColumn(LocaleRow::getIcon, new ImageRenderer()).setCaption("Flag");
        grid.addColumn(LocaleRow::getDisplayCountry).setCaption("Country");
        grid.addColumn(LocaleRow::getDisplayLanguage).setCaption("Language");
        grid.addColumn(LocaleRow::getCountryCode).setCaption("ISO 3166");
    }

    private void localeChanged(HasValue.ValueChangeEvent<LocaleRow> event) {
        LocaleRow row = event.getValue();
        if (row == null) {
            return;
        }
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
        notification.setIcon(row.getIcon());
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
    
}
