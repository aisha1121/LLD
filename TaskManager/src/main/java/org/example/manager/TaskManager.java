package org.example.manager;

import org.example.constants.TaskStatus;
import org.example.entities.Task;
import org.example.services.FilterCriteria;

import java.util.List;

public interface TaskManager {
    List<Task> getTaskHistory(String userId);
    void createTask(Task task);
    void updateStatus(Task task, TaskStatus taskStatus);
    void deleteTask(Task task);
    List<Task> getTaskByFilter(FilterCriteria filterCriteria);
}
