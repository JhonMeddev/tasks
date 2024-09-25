package com.testejhon.tasks.controller;

import com.testejhon.tasks.model.UserModel;
import com.testejhon.tasks.service.UserService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UsuarioControllerTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private UserService userService;


    @Test
    @Order(1)
    @DisplayName("Cadastrar um usuario")
    public void deveCriarUmUsuario(){
        HttpEntity<UserModel> requisicao = new HttpEntity<UserModel>(new UserModel(0L, "João Bezerra", "2342423234", "ADMIN"));

        ResponseEntity<UserModel> resposta = testRestTemplate.exchange("/users", HttpMethod.POST, requisicao, UserModel.class);

        Assertions.assertEquals(HttpStatus.CREATED, resposta.getStatusCode());
        Assertions.assertEquals(requisicao.getBody().getUsername(), resposta.getBody().getUsername());
    }

    @Test
    @Order(2)
    @DisplayName("Não deve duplicar um usuario.")
    public void naoDeveDuplicarUmUsuario(){
        userService.cadastrarUsuario(new UserModel(0L, "Jhonatan Medeiros", "2342423234", "ADMIN"));

        HttpEntity<UserModel> requisicao = new HttpEntity<UserModel>(new UserModel(0L, "Jhonatan Medeiros", "2342423234", "ADMIN"));

        ResponseEntity<UserModel> resposta = testRestTemplate.exchange("/users", HttpMethod.POST, requisicao, UserModel.class);

        Assertions.assertEquals(HttpStatus.BAD_REQUEST, resposta.getStatusCode());
    }

    @Test
    @Order(3)
    @DisplayName("Alterar um usuario.")
    public void deveAtualizarUmUsuario(){

        Optional<UserModel> usuarioCreate = userService.cadastrarUsuario(new UserModel(0L, "Mario Kart", "2342423234", "ADMIN"));

        UserModel userUpdate = new UserModel(usuarioCreate.get().getId(), "Luigi Kart", "2342423234", "ADMIN");

        HttpEntity<UserModel> requisicao = new HttpEntity<UserModel>(userUpdate);

        ResponseEntity<UserModel> response = testRestTemplate.withBasicAuth("root", "root")
                .exchange("/users", HttpMethod.PUT, requisicao, UserModel.class);

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(userUpdate.getUsername(), response.getBody().getUsername());

    }

    @Test
    @Order(3)
    @DisplayName("Listar todos usuários.")
    public void deveMostrarTodosUsuarios(){
        userService.cadastrarUsuario(new UserModel(0L, "Gokuu ", "2342423234", "ADMIN"));
        userService.cadastrarUsuario(new UserModel(0L, "Vegeta ", "2342423234", "ADMIN"));

        ResponseEntity<String> resp = testRestTemplate.withBasicAuth("root", "root")
                .exchange("/users", HttpMethod.GET, null, String.class);

        Assertions.assertEquals(HttpStatus.OK, resp.getStatusCode());
    }


}
