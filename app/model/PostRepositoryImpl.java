package model;

import play.db.jpa.JPAApi;

import javax.inject.Inject;
import javax.persistence.NoResultException;
import java.util.List;
import java.util.concurrent.CompletionStage;

import static java.util.concurrent.CompletableFuture.supplyAsync;

public class PostRepositoryImpl implements PostRepository {

    private final JPAApi jpaApi;
    private final DBExecutionContext executionContext;

    @Inject
    public PostRepositoryImpl(JPAApi jpaApi, DBExecutionContext executionContext) {
        this.jpaApi = jpaApi;
        this.executionContext = executionContext;
    }

    @Override
    public CompletionStage<List<Post>> getAll() {
        return supplyAsync(() -> jpaApi.withTransaction(entityManager -> {
            return entityManager.createQuery("select p from Post p", Post.class).getResultList();
        }));
    }

    @Override
    public Post getById(Integer postId) {
        try {
            return jpaApi.withTransaction(entityManager -> {
                return entityManager.createQuery("select p from Post p where p.id = :postId", Post.class)
                        .setParameter("postId", postId)
                        .getSingleResult();
            });
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public void save(Post post) {
        jpaApi.withTransaction(entityManager -> {
            entityManager.persist(post);
        });
    }
}
