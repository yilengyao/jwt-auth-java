package io.github.yilengyao.jwtauth.model;

import lombok.Data;

@Data
public class Comment {
    int id;
    String user;
    String content;
}
