package com.testejhon.tasks.service;

import com.testejhon.tasks.model.UserLogin;
import com.testejhon.tasks.model.UserModel;
import com.testejhon.tasks.repository.UserRepository;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.nio.charset.Charset;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public Optional<UserModel> cadastrarUsuario(UserModel user) {
        if (userRepository.findByUsername(user.getUsername()).isPresent())
            return Optional.empty();
        user.setPassword(criptografarSenha(user.getPassword()));
        return Optional.of(userRepository.save(user));
    }

    public Optional<UserModel> atualizarUsuario(UserModel user) {

        if (userRepository.findById(user.getId()).isPresent()) {

            Optional<UserModel> buscaUsuario = userRepository.findByUsername(user.getUsername());

            if ((buscaUsuario.isPresent()) && (buscaUsuario.get().getId() != user.getId()))
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Usuário já existe!", null);

            user.setPassword(criptografarSenha(user.getPassword()));

            return Optional.ofNullable(userRepository.save(user));

        }

        return Optional.empty();

    }

    public Optional<UserLogin> autenticarUsuario(Optional<UserLogin> userLogin) {

        Optional<UserModel> username = userRepository.findByUsername(userLogin.get().getUsername());

        if (username.isPresent()) {

            if (compararSenhas(userLogin.get().getPassword(), username.get().getPassword())) {

                userLogin.get().setId(username.get().getId());
                userLogin.get().setUsername(username.get().getUsername());
                userLogin.get().setNivel(username.get().getNivel());
                userLogin.get().setToken(gerarBasicToken(username.get().getUsername(), userLogin.get().getPassword()));
                userLogin.get().setPassword(username.get().getPassword());

                return userLogin;
            }
        }

        return Optional.empty();
    }

    private String criptografarSenha(String password) {

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        return encoder.encode(password);
    }

    private boolean compararSenhas(String senhaDigitada, String senhaBanco) {

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        return encoder.matches(senhaDigitada, senhaBanco);
    }

    private String gerarBasicToken(String usuario, String senha) {

        String token = usuario + ":" + senha;
        byte[] tokenBase64 = Base64.encodeBase64(token.getBytes(Charset.forName("US-ASCII")));

        return "Basic " + new String(tokenBase64);
    }

}
