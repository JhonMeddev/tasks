package com.testejhon.tasks.security;

import com.testejhon.tasks.model.UserModel;
import com.testejhon.tasks.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<UserModel> user = userRepository.findByUsername(username);

        user.orElseThrow(() -> new UsernameNotFoundException(username + " not found."));

        return user.map(UserDetailsImpl::new).get();
    }
}
