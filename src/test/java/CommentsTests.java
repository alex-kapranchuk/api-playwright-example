import Instance.ApiClient;
import Instance.PlaywrightSingleton;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.Playwright;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class CommentsTests {

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
    public void testGetComments() {
        APIResponse response = new GetCommentsCommand().execute(apiClient.getContext());
        ApiAssertions.assertStatusCode(response, 200);
        ApiAssertions.assertResponseContains(response, "postId", "id", "name", "email", "body");
    }

    @Test
    public void testCreateComment() {
        int postId = 1;
        String name = "Test Name";
        String email = "test@example.com";
        String body = "This is a test comment.";

        APIResponse response = new CreateCommentCommand(postId, name, email, body).execute(apiClient.getContext());
        ApiAssertions.assertStatusCode(response, 201);
        ApiAssertions.assertCommentData(response, postId, name, email, body);
    }

    @Test
    public void testUpdateComment() {
        int id = 1;
        int postId = 1;
        String name = "Updated Name";
        String email = "updated@example.com";
        String body = "Updated Body";

        APIResponse response = new UpdateCommentCommand(id, postId, name, email, body).execute(apiClient.getContext());
        ApiAssertions.assertStatusCode(response, 200);
        ApiAssertions.assertCommentData(response, postId, name, email, body);
    }

    @Test
    public void testPatchComment() {
        int id = 1;
        String updatedBody = "Partially Updated Body";

        APIResponse response = new PatchCommentCommand(id, updatedBody).execute(apiClient.getContext());
        ApiAssertions.assertStatusCode(response, 200);

        String responseBody = response.text();
        Assertions.assertTrue(responseBody.contains("\"body\": \"" + updatedBody + "\""));
    }

    @Test
    public void testDeleteComment() {
        int id = 1;

        APIResponse response = new DeleteCommentCommand(id).execute(apiClient.getContext());
        ApiAssertions.assertStatusCode(response, 200);
    }
}
