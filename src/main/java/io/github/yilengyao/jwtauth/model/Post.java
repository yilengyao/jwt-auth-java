package io.github.yilengyao.jwtauth.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "posts")
@Data
public class Post {
    private int id;
    private String content;
    private User user;
    private List<Comment> comments;
}
