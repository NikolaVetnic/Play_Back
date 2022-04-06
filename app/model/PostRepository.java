package model;

import com.google.inject.ImplementedBy;

import java.util.List;
import java.util.concurrent.CompletionStage;

@ImplementedBy(PostRepositoryImpl.class)
public interface PostRepository {

    CompletionStage<List<Post>> getAll();
    Post getById(Integer postId);
    void save(Post post);
}
