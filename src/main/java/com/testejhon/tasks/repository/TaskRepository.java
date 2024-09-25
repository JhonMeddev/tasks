package com.testejhon.tasks.repository;

import com.testejhon.tasks.model.Status;
import com.testejhon.tasks.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    public List<Task> findAllByStatus(Status status);
}
