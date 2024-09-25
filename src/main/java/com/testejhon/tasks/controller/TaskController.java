package com.testejhon.tasks.controller;

import com.testejhon.tasks.model.Status;
import com.testejhon.tasks.model.Task;
import com.testejhon.tasks.repository.TaskRepository;
import com.testejhon.tasks.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TaskRepository taskRepository;

    /*GET /api/tasks: Listar todas as tarefas do usuário autenticado.*/
    @GetMapping
    public ResponseEntity<List<Task>> getAll(){
        return ResponseEntity.ok(taskRepository.findAll());
    }

    /*POST /api/tasks: Criar uma nova tarefa.*/
    @PostMapping
    public ResponseEntity<Task> postTask(@Valid @RequestBody Task post){
        return ResponseEntity.status(HttpStatus.CREATED).body(taskRepository.save(post));
    }

    /*PUT /api/tasks/{id}: Atualizar uma tarefa existente.*/
    @PutMapping
    public ResponseEntity<Task> updateTask(@Valid @RequestBody Task task) {

        return taskRepository.findById(task.getId())
                .map(resposta -> {
                    return ResponseEntity.ok().body(taskRepository.save(task));
                })
                .orElse(ResponseEntity.notFound().build());

    }

    /*DELETE /api/tasks/{id}: Deletar uma tarefa.*/
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTask(@PathVariable long id) {

        return taskRepository.findById(id)
                .map(resposta -> {
                    taskRepository.deleteById(id);
                    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
                })
                .orElse(ResponseEntity.notFound().build());
    }

    /*GET /api/tasks?status={status}: Filtrar tarefas por status.*/
    @GetMapping("/status/{status}")
    public ResponseEntity<List<Task>> tasksByStatus(@Valid @PathVariable String status){
        return ResponseEntity.ok(taskRepository.findAllByStatus(Status.valueOf(status)));
    }


    /*GET /api/tasks?sort=dueDate: Ordenar tarefas por data de vencimento.*/
    @GetMapping("?sort=dueDate")
    public ResponseEntity<List<Task>> getTasksSortedByDueDate(@RequestParam(defaultValue = "asc") String sortDirection) {
        Sort.Direction direction = Sort.Direction.fromString(sortDirection);
        List<Task> tasks = taskRepository.findAll(Sort.by(direction, "dueDate"));
        return ResponseEntity.ok(tasks);
    }

}
