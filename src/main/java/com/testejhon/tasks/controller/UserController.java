package com.testejhon.tasks.controller;

import com.testejhon.tasks.model.Task;
import com.testejhon.tasks.model.UserLogin;
import com.testejhon.tasks.model.UserModel;
import com.testejhon.tasks.repository.UserRepository;
import com.testejhon.tasks.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService usuarioService;

    @Autowired
    private UserRepository userRepository;

    /*GET /api/users/{userId}/tasks: Listar todas as tarefas de um usuário específico.*/
    @GetMapping("/{userId}/tasks")
    public ResponseEntity<List<Task>> getAllTasksByUser(@PathVariable long userId){
        return userRepository.findById(userId)
                .map( resp -> ResponseEntity.ok(resp.getTask()))
                .orElse(ResponseEntity.notFound().build());

    }

    /*GET /api/users: Listar todos os usuários.*/
    @GetMapping
    public ResponseEntity<List<UserModel>> getAll() {
        return ResponseEntity.ok(userRepository.findAll());
    }

    /*POST /api/users: Criar um novo usuário.*/
    @PostMapping
    public ResponseEntity<UserModel> postUsuario(@Valid @RequestBody UserModel user) {
        return usuarioService.cadastrarUsuario(user)
                .map(resposta -> ResponseEntity.status(HttpStatus.CREATED).body(resposta))
                .orElse(ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
    }

    /*PUT /api/users/{id}: Atualizar um usuário existente.*/
    @PutMapping
    public ResponseEntity<UserModel> putUsuario(@Valid @RequestBody UserModel user) {
        return usuarioService.atualizarUsuario(user)
                .map(resposta -> ResponseEntity.status(HttpStatus.OK).body(resposta))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    /*DELETE /api/users/{id}: Deletar um usuário.*/
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTask(@PathVariable long id) {
        return userRepository.findById(id)
                .map(resposta -> {
                    userRepository.deleteById(id);
                    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/logar")
    public ResponseEntity<UserLogin> login(@RequestBody Optional<UserLogin> user) {
        return usuarioService.autenticarUsuario(user).map(resposta -> ResponseEntity.ok(resposta))
                .orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserModel> GetById(@PathVariable long id) {
        return userRepository.findById(id).map(resp -> ResponseEntity.ok(resp)).orElse(ResponseEntity.notFound().build());
    }

}
