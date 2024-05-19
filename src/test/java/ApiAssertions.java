import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.microsoft.playwright.APIResponse;
import org.junit.jupiter.api.Assertions;

public class ApiAssertions {

    public static void assertStatusCode(APIResponse response, int expectedStatusCode) {
        Assertions.assertEquals(response.status(), expectedStatusCode, "Expected status code: " + expectedStatusCode + ", but got: " + response.status());
    }

    public static void assertPostData(APIResponse response, String expectedTitle, String expectedBody, int expectedUserId) {
        String responseBody = response.text();
        JsonObject jsonObject = JsonParser.parseString(responseBody).getAsJsonObject();
        Assertions.assertEquals(jsonObject.get("title").getAsString(), expectedTitle);
        Assertions.assertEquals(jsonObject.get("body").getAsString(), expectedBody);
        Assertions.assertEquals(jsonObject.get("userId").getAsInt(), expectedUserId);
        Assertions.assertTrue(jsonObject.has("id"));
    }

    public static void assertResponseContains(APIResponse response, String... keywords) {
        String responseBody = response.text();
        for (String keyword : keywords) {
            Assertions.assertTrue(responseBody.contains(keyword), "Response does not contain expected keyword: " + keyword);
        }
    }
    public static void assertCommentData(APIResponse response, int expectedPostId, String expectedName, String expectedEmail, String expectedBody) {
        String responseBody = response.text();
        JsonObject jsonObject = JsonParser.parseString(responseBody).getAsJsonObject();
        Assertions.assertEquals(jsonObject.get("postId").getAsInt(), expectedPostId);
        Assertions.assertEquals(jsonObject.get("name").getAsString(), expectedName);
        Assertions.assertEquals(jsonObject.get("email").getAsString(), expectedEmail);
        Assertions.assertEquals(jsonObject.get("body").getAsString(), expectedBody);
        Assertions.assertTrue(jsonObject.has("id"));
    }
}
