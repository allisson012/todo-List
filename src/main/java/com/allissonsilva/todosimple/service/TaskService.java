package com.allissonsilva.todosimple.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.allissonsilva.todosimple.repository.TaskRepository;
import com.allissonsilva.todosimple.model.Task;
import com.allissonsilva.todosimple.model.User;

@Service
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserService userService;

    public Task findTaskById(Long id) {
        Optional<Task> task = this.taskRepository.findById(id);
        return task.orElseThrow(
                () -> new RuntimeException("Tarefa não encontrada! Id: " + id + "Tipo: " + Task.class.getName()));
    }

    @Transactional
    public Task createTask(Task obj) {
        User user = this.userService.findUserById(obj.getUser().getId());
        obj.setId(null);
        obj.setUser(user);
        obj = this.taskRepository.save(obj);
        return obj;
    }

    @Transactional
    public Task updateTask(Task obj) {
        Task newObj = findTaskById(obj.getId());
        newObj.setDescription(obj.getDescription());
        return this.taskRepository.save(newObj);
    }

    public void deleteTask(Long id) {
        findTaskById(id);
        try {
            this.taskRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Não é possivel excluir pois há entidades relacionada!");
        }
    }
}
