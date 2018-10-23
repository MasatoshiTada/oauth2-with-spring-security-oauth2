package com.example.resourceserver.persistence.respository;

import com.example.resourceserver.persistence.entity.Todo;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface TodoRepository extends CrudRepository<Todo, Integer> {

    @Query("UPDATE Todo t SET t.done = true WHERE t.id = :id")
    @Modifying
    public void updateDoneById(@Param("id") Integer id);
}
