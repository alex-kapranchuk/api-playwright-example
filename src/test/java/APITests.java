import com.microsoft.playwright.Page;
import com.microsoft.playwright.junit.UsePlaywright;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

@UsePlaywright
public class APITests {

    @Test
    public void testGetPosts(Page page) {
        page.navigate("https://jsonplaceholder.typicode.com/posts");
        String responseBody = page.textContent("body");
        Assertions.assertTrue(responseBody.contains("userId"));
        Assertions.assertTrue(responseBody.contains("id"));
        Assertions.assertTrue(responseBody.contains("title"));
        Assertions.assertTrue(responseBody.contains("body"));
    }
}
