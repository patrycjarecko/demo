package com.example.demo.service;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
public class UserService {
    private UserRepository userRepository;

    public List<User> getUsers(){
        return userRepository.findAll();
    }

    public User getUser(Long id){
        return userRepository.findById(id).orElseThrow(()-> new RuntimeException("User with this id doesn't exist"));
    }

    public User createUser(User user){
        return userRepository.save(user);
    }

    @Transactional(rollbackFor = Exception.class)
    public User updateUser(User user){
        User existingUser = userRepository.findById(user.getId()).orElseThrow(()-> new RuntimeException("User with this id doesn't exist"));
        existingUser.setFirstName(user.getFirstName());
        existingUser.setLastName(user.getLastName());
        return userRepository.save(existingUser);
    }

    @Transactional(rollbackFor = Exception.class)
    public User changePassword(User user, String password, String oldPassword) throws Exception {
        User existingUser = userRepository.findById(user.getId()).orElseThrow(()-> new RuntimeException("User with this id doesn't exist"));
        if (!Objects.equals(existingUser.getPassword(), oldPassword)) {
            throw new Exception("Wrong old password.");
        }
        existingUser.setPassword(password);
        return userRepository.save(existingUser);
    }

    public void deleteUser(Long id){
        userRepository.deleteById(id);
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("No user with such username!"));
    }
}
