package pl.akademiaqa.pages.sections.common;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class TopNavigationSection {

    private Page page;

    private Locator languageSelector;
    private String selectedLanguage;
    private Locator english;

    public TopNavigationSection(Page page) {
        this.page = page;
        this.languageSelector = page.locator(".language-selector");
        this.english = page.locator("a[data-iso-code=en]");
        this.selectedLanguage = page.locator("span[class=expand-more]").innerText();
    }

    public void setPageLanguageToEn() {
        if (!selectedLanguage.equals("English")) {
            languageSelector.click();
            english.click();
        }
    }
}
