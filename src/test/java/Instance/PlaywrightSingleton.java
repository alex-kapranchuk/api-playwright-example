package Instance;

import com.microsoft.playwright.Playwright;

public class PlaywrightSingleton {
    private static Playwright playwrightInstance;

    private PlaywrightSingleton() {}

    public static Playwright getInstance() {
        if (playwrightInstance == null) {
            playwrightInstance = Playwright.create();
        }
        return playwrightInstance;
    }

    public static void close() {
        if (playwrightInstance != null) {
            playwrightInstance.close();
            playwrightInstance = null;
        }
    }
}
