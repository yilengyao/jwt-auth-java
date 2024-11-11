package io.github.yilengyao.jwtauth.service;

import io.github.yilengyao.jwtauth.model.Post;
import io.github.yilengyao.jwtauth.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    public Post getPostById(int id) {
        return postRepository.findByPostId(id);
    }

    public List<Post> getAllPost() {
        return postRepository.findAll();
    }

    public Post createPost(Post post) {
        return postRepository.save(post);
    }

}
