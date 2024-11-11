package io.github.yilengyao.jwtauth.repository;

import io.github.yilengyao.jwtauth.model.Post;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface PostRepository extends MongoRepository<Post, String> {

    @Query("{ 'id' : ?0 }")
    Post findByPostId(int id);

}
