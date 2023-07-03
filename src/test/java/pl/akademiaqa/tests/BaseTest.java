package pl.akademiaqa.tests;

import com.microsoft.playwright.*;
import org.junit.jupiter.api.*;
import pl.akademiaqa.factory.BrowserFactory;
import pl.akademiaqa.utils.Properties;

import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static pl.akademiaqa.utils.StringUtils.removeRoundBrackets;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class BaseTest {

//    private static Playwright pw;
//    protected static Browser browser;

    private BrowserFactory browserFactory;
    protected Browser browser;

    protected BrowserContext browserContext;
    protected Page page;

    @BeforeAll
    void launchBrowser() {
        browserFactory = new BrowserFactory();
        browser = browserFactory.getBrowser();

//        pw = Playwright.create();
//        browser = pw.chromium().launch(new BrowserType.LaunchOptions()
//                .setHeadless(Boolean.parseBoolean(Properties.getProperty("browser.headless")))
//                .setSlowMo(Integer.parseInt(Properties.getProperty("browser.slow.mo")))
//                .setChannel("msedge"));
    }

    @BeforeEach
    void createContextAndPage() {
        browserContext = browser.newContext();

        // TRACING START
        if (isTracingEnabled()) {
            browserContext.tracing().start(new Tracing.StartOptions()
                    .setScreenshots(true)
                    .setSnapshots(true)
                    .setSources(true));
        }

        page = browserContext.newPage();
        page.setViewportSize(1920, 1080);
        page.navigate(Properties.getProperty("app.url"));
    }

    @AfterEach
    void closeContext(TestInfo testInfo) {
        // TRACING STOP
        if (isTracingEnabled()) {
            String traceName = "traces/trace_"
                    + removeRoundBrackets(testInfo.getDisplayName())
                    + "_" + LocalDateTime.now().format(DateTimeFormatter
                    .ofPattern(Properties.getProperty("tracing.date.format"))) + ".zip";
            browserContext.tracing().stop(new Tracing.StopOptions().setPath(Paths.get(traceName)));
        }

        browserContext.close();
    }

    @AfterAll
    void closeBrowser() {
        browserFactory.getPlaywright().close();
    }

    private boolean isTracingEnabled() {
        return Boolean.parseBoolean(Properties.getProperty("tracing.enabled"));
    }
}
