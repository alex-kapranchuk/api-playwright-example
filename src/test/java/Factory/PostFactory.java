package Factory;

public class PostFactory {

    public static String createPostData(String title, String body, int userId) {
        return String.format("{\"title\": \"%s\", \"body\": \"%s\", \"userId\": %d}", title, body, userId);
    }

    public static String createUpdatePostData(int id, String title, String body, int userId) {
        return String.format("{\"id\": %d, \"title\": \"%s\", \"body\": \"%s\", \"userId\": %d}", id, title, body, userId);
    }

    public static String createPatchPostData(String title) {
        return String.format("{\"title\": \"%s\"}", title);
    }
}


