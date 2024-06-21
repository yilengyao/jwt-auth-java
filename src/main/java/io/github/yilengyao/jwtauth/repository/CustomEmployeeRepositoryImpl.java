package io.github.yilengyao.jwtauth.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import io.github.yilengyao.jwtauth.model.Employee;

@Repository
public class CustomEmployeeRepositoryImpl implements CustomEmployeeRepository {

  @Autowired
  private MongoTemplate mongoTemplate;

  @Override
  public Employee updateEmployee(String id, Employee employee) {
    Query query = new Query(Criteria.where("id").is(id));
    Update update = new Update()
            .set("firstname", employee.getFirstname())
            .set("lastname", employee.getLastname())
            .set("email", employee.getEmail());

    return mongoTemplate.findAndModify(query, update, Employee.class);
  }

}
