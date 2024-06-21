package io.github.yilengyao.jwtauth.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document(collection = "employees")
@Data
public class Employee {
    @Id
    private String id;
    private String firstname;
    private String lastname;
    private String email;
}
