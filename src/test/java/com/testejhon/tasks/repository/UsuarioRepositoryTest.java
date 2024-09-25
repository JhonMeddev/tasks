package com.testejhon.tasks.repository;

import com.testejhon.tasks.model.UserModel;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

@SpringBootTest(webEnvironment =  SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UsuarioRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @BeforeAll
    void start(){
        userRepository.save(new UserModel(0L, "João Bezerra", "2342423234", "ADMIN"));
        userRepository.save(new UserModel(0L, "Maria Silva", "1234567890", "USER"));
        userRepository.save(new UserModel(0L, "Carlos Silva", "9876543210", "USER"));
        userRepository.save(new UserModel(0L, "Ana Silva", "1122334455", "ADMIN"));
    }

    @Test
    @DisplayName("Retorna 1 usuario")
    public void deveRetornarUmUsuario(){
        Optional<UserModel> user = userRepository.findByUsername("João Bezerra");
        Assertions.assertTrue(user.get().getUsername().equals("João Bezerra"));
    }

    @Test
    @DisplayName("Retorna 3 usuarios")
    public void deveRetornarTresUsuarios(){
        List<UserModel> listaDeUsuarios = userRepository.findAllByUsernameContainingIgnoreCase("Silva");
        Assertions.assertEquals(3, listaDeUsuarios.size());
        Assertions.assertTrue(listaDeUsuarios.get(0).getUsername().equals("Maria Silva"));
        Assertions.assertTrue(listaDeUsuarios.get(1).getUsername().equals("Carlos Silva"));
        Assertions.assertTrue(listaDeUsuarios.get(2).getUsername().equals("Ana Silva"));
    }


}
