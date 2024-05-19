import Factory.CommentFactory;
import Factory.PostFactory;
import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.options.RequestOptions;

public interface ApiCommand {
    APIResponse execute(APIRequestContext context);
}

class GetPostsCommand implements ApiCommand {
    @Override
    public APIResponse execute(APIRequestContext context) {
        return context.get("https://jsonplaceholder.typicode.com/posts");
    }
}

class CreatePostCommand implements ApiCommand {
    private final String title;
    private final String body;
    private final int userId;

    public CreatePostCommand(String title, String body, int userId) {
        this.title = title;
        this.body = body;
        this.userId = userId;
    }

    @Override
    public APIResponse execute(APIRequestContext context) {
        String postData = PostFactory.createPostData(title, body, userId);
        return context.post("https://jsonplaceholder.typicode.com/posts",
                RequestOptions.create().setHeader("Content-Type", "application/json").setData(postData));
    }
}

class UpdatePostCommand implements ApiCommand {
    private final int id;
    private final String title;
    private final String body;
    private final int userId;

    public UpdatePostCommand(int id, String title, String body, int userId) {
        this.id = id;
        this.title = title;
        this.body = body;
        this.userId = userId;
    }

    @Override
    public APIResponse execute(APIRequestContext context) {
        String putData = PostFactory.createUpdatePostData(id, title, body, userId);
        return context.put("https://jsonplaceholder.typicode.com/posts/" + id,
                RequestOptions.create().setHeader("Content-Type", "application/json").setData(putData));
    }
}

class PatchPostCommand implements ApiCommand {
    private final int id;
    private final String title;

    public PatchPostCommand(int id, String title) {
        this.id = id;
        this.title = title;
    }

    @Override
    public APIResponse execute(APIRequestContext context) {
        String patchData = PostFactory.createPatchPostData(title);
        return context.patch("https://jsonplaceholder.typicode.com/posts/" + id,
                RequestOptions.create().setHeader("Content-Type", "application/json").setData(patchData));
    }
}

class DeletePostCommand implements ApiCommand {
    private final int id;

    public DeletePostCommand(int id) {
        this.id = id;
    }

    @Override
    public APIResponse execute(APIRequestContext context) {
        return context.delete("https://jsonplaceholder.typicode.com/posts/" + id);
    }
}

class GetCommentsCommand implements ApiCommand {
    @Override
    public APIResponse execute(APIRequestContext context) {
        return context.get("https://jsonplaceholder.typicode.com/comments");
    }
}

class CreateCommentCommand implements ApiCommand {
    private final int postId;
    private final String name;
    private final String email;
    private final String body;

    public CreateCommentCommand(int postId, String name, String email, String body) {
        this.postId = postId;
        this.name = name;
        this.email = email;
        this.body = body;
    }

    @Override
    public APIResponse execute(APIRequestContext context) {
        String commentData = CommentFactory.createCommentData(postId, name, email, body);
        return context.post("https://jsonplaceholder.typicode.com/comments",
                RequestOptions.create().setHeader("Content-Type", "application/json").setData(commentData));
    }
}

class UpdateCommentCommand implements ApiCommand {
    private final int id;
    private final int postId;
    private final String name;
    private final String email;
    private final String body;

    public UpdateCommentCommand(int id, int postId, String name, String email, String body) {
        this.id = id;
        this.postId = postId;
        this.name = name;
        this.email = email;
        this.body = body;
    }

    @Override
    public APIResponse execute(APIRequestContext context) {
        String updateData = CommentFactory.createUpdateCommentData(id, postId, name, email, body);
        return context.put("https://jsonplaceholder.typicode.com/comments/" + id,
                RequestOptions.create().setHeader("Content-Type", "application/json").setData(updateData));
    }
}

class PatchCommentCommand implements ApiCommand {
    private final int id;
    private final String body;

    public PatchCommentCommand(int id, String body) {
        this.id = id;
        this.body = body;
    }

    @Override
    public APIResponse execute(APIRequestContext context) {
        String patchData = String.format("{\"body\": \"%s\"}", body);
        return context.patch("https://jsonplaceholder.typicode.com/comments/" + id,
                RequestOptions.create().setHeader("Content-Type", "application/json").setData(patchData));
    }
}

class DeleteCommentCommand implements ApiCommand {
    private final int id;

    public DeleteCommentCommand(int id) {
        this.id = id;
    }

    @Override
    public APIResponse execute(APIRequestContext context) {
        return context.delete("https://jsonplaceholder.typicode.com/comments/" + id);
    }
}
