package com.allissonsilva.todosimple.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.allissonsilva.todosimple.model.Task;
import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByUser_id(Long id);

    // JPa Query
    // @Query(value = "SELECT t FROM Task t WHERE t.user.id = :id")
    // List<Task> findByUserId(@Param ("id") Long id);

    // SQl puro
    // @Query(value = "SELECT * FROM task t WHERE t.user_id = :id",nativeQuery =
    // true)
    // List<Task> findByUserId(@Param ("id") Long id);
}
