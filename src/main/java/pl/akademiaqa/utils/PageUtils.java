package pl.akademiaqa.utils;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.LoadState;

public class PageUtils {
    // prywatny konstruktor

    public static void waitForPageToLoad(Page page){
        page.waitForLoadState(LoadState.NETWORKIDLE);
    }
}
