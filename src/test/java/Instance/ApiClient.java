package Instance;

import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.Playwright;


public class ApiClient {
    public APIRequestContext apiRequestContext;

    public ApiClient(Playwright playwright) {
        this.apiRequestContext = playwright.request().newContext();
    }

    public APIRequestContext getContext() {
        return apiRequestContext;
    }
}
