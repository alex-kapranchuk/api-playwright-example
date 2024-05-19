package Factory;

public class CommentFactory {

        public static String createCommentData(int postId, String name, String email, String body) {
            return String.format("{\"postId\": %d, \"name\": \"%s\", \"email\": \"%s\", \"body\": \"%s\"}", postId, name, email, body);
        }

        public static String createUpdateCommentData(int id, int postId, String name, String email, String body) {
            return String.format("{\"id\": %d, \"postId\": %d, \"name\": \"%s\", \"email\": \"%s\", \"body\": \"%s\"}", id, postId, name, email, body);
        }
}
