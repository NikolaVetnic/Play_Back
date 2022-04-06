package service;

import form.PostForm;
import model.Post;
import model.PostRepository;

import javax.inject.Inject;
import java.util.List;
import java.util.concurrent.CompletionStage;

public class PostService {

    private final PostRepository postRepository;

    @Inject
    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public CompletionStage<List<Post>> getPosts() {
        return postRepository.getAll();
    }

    public Post getPost(int postId) {
        return postRepository.getById(postId);
    }

    public void addPost(PostForm postForm) {
        postRepository.save(new Post(postForm.getTitle(), postForm.getContent()));
    }
}
