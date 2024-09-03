package io.github.yilengyao.jwtauth.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import io.github.yilengyao.jwtauth.model.Employee;

public interface EmployeeRepository extends MongoRepository<Employee, String>, CustomEmployeeRepository {

  // Custom query to find employees by firstname
  @Query("{ 'firstname' : ?0 }")
  public List<Employee> findByFirstname(String firstname);

  // Custom query to find employees by lastname
  @Query("{ 'lastname' : ?0 }")
  public List<Employee> findByLastname(String lastname);

  // Custom query to find employees by firstname and lastname
  @Query("{ 'firstname' : ?0, 'lastname' : ?1}")
  public List<Employee> findByFirstnameAndLastName(String firstname, String lastname);

  // Custom query to find employee by email
  @Query("{ 'email' : ?0 }")
  public Optional<Employee> findByEmail(String email);

  /** 
   * Custom paginated query to find all employees 
   * projecting firstname, lastname, and email
  */
  @Query(value = "{}", fields = "{ 'firstname' : 1, 'lastname' : 1, 'email' : 1 }")
  public Page<Employee> findAllProjectedBy(Pageable pageable);
}


