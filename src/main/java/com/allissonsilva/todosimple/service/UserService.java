package com.allissonsilva.todosimple.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.allissonsilva.todosimple.repository.TaskRepository;
import com.allissonsilva.todosimple.repository.UserRepository;
import com.allissonsilva.todosimple.model.User;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User findUserById(Long id) {
        Optional<User> user = this.userRepository.findById(id);
        return user.orElseThrow(() -> new RuntimeException(
                "Usuario não encontrado! id: " + id + " tipo: " + User.class.getName()));
    }

    @Transactional
    public User createUser(User obj) {
        obj.setId(null);
        this.userRepository.save(obj);
        return obj;
    }

    @Transactional
    public User updateUser(User obj) {
        User newObj = findUserById(obj.getId());
        newObj.setPassword(obj.getPassword());
        return this.userRepository.save(newObj);
    }

    public void deleteUser(Long id) {
        User user = findUserById(id);
        try {
            this.userRepository.deleteById(user.getId());
        } catch (Exception e) {
            throw new RuntimeException("Não é possivel excluir pois há entidades relacionada!");
        }

    }

}
