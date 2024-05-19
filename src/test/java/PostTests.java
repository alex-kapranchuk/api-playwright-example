import Instance.ApiClient;
import Instance.PlaywrightSingleton;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.Playwright;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class PostTests {
    private static ApiClient apiClient;

    @BeforeAll
    public static void setUp() {
        Playwright playwright = PlaywrightSingleton.getInstance();
        apiClient = new ApiClient(playwright);
    }

    @AfterAll
    public static void tearDown() {
        PlaywrightSingleton.close();
    }

    @Test
    public void testGetPosts() {
        APIResponse response = new GetPostsCommand().execute(apiClient.getContext());
        ApiAssertions.assertStatusCode(response, 200);
        ApiAssertions.assertResponseContains(response, "userId", "id", "title", "body");
    }

    @Test
    public void testCreatePost() {
        String title = "Test Post";
        String body = "This is a test post.";
        int userId = 1;

        APIResponse response = new CreatePostCommand(title, body, userId).execute(apiClient.getContext());
        ApiAssertions.assertStatusCode(response, 201);
        ApiAssertions.assertPostData(response, title, body, userId);
    }

    @Test
    public void testUpdatePost() {
        int id = 1;
        String title = "Updated Title";
        String body = "Updated Body";
        int userId = 1;

        APIResponse response = new UpdatePostCommand(id, title, body, userId).execute(apiClient.getContext());
        ApiAssertions.assertStatusCode(response, 200);
        ApiAssertions.assertPostData(response, title, body, userId);
    }

    @Test
    public void testPatchPost() {
        int id = 1;
        String updatedTitle = "Partially Updated Title";

        APIResponse response = new PatchPostCommand(id, updatedTitle).execute(apiClient.getContext());
        ApiAssertions.assertStatusCode(response, 200);

        String responseBody = response.text();
        Assertions.assertTrue(responseBody.contains("\"title\": \"" + updatedTitle + "\""));
    }

    @Test
    public void testDeletePost() {
        int id = 1;

        APIResponse response = new DeletePostCommand(id).execute(apiClient.getContext());
        ApiAssertions.assertStatusCode(response, 200);
    }
}
