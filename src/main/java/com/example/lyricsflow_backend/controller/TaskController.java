package com.example.lyricsflow_backend.controller;

import com.example.lyricsflow_backend.business.TaskBusiness;
import com.example.lyricsflow_backend.dto.TaskResponse;
import com.example.lyricsflow_backend.model.Task;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class TaskController {

    private final TaskBusiness taskBusiness;

    public TaskController(TaskBusiness taskBusiness) {
        this.taskBusiness = taskBusiness;
    }

    @GetMapping("/{userId}/tasks")
public List<TaskResponse> getUserTasks(@PathVariable Long userId) {
    return taskBusiness.getTasksByUser(userId);
}
}
