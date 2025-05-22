package org.example.manager.impl;

import org.example.constants.TaskStatus;
import org.example.entities.Task;
import org.example.manager.TaskManager;
import org.example.services.FilterCriteria;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TaskManagerImpl implements TaskManager {
    private volatile static TaskManagerImpl instance;
    private Map<String, Task> tasks;
    private FilterCriteria filterCriteria;
    private final Lock lock = new ReentrantLock();

    private TaskManagerImpl() {
        tasks = new ConcurrentHashMap<>();
        filterCriteria = new FilterCriteria();
    }

    public static TaskManagerImpl getInstance() {
        if (instance == null) {
            synchronized (TaskManagerImpl.class) {
                if (instance == null) {
                    instance = new TaskManagerImpl();
                }
            }
        }
        return instance;
    }

    public List<Task> getTaskHistory(String userId) {
        List<Task> taskHistory = new ArrayList<>();
        for (Task task : tasks.values()) {
            if (task.getAssignee().getId().equals(userId)) {
                taskHistory.add(task);
            }
        }
        return taskHistory;
    }

    public void createTask(Task task) {
        tasks.putIfAbsent(task.getId(), task);
    }


    public void updateStatus(Task task, TaskStatus taskStatus) {
        lock.lock();
        try {
            Task reqdTask = tasks.get(task.getId());
            if (reqdTask != null) {
                reqdTask.setStatus(taskStatus);
            }
        } finally {
            lock.unlock();
        }
    }

    public void deleteTask(Task task) {
        tasks.remove(task.getId());
    }

    @Override
    public List<Task> getTaskByFilter(FilterCriteria criteria) {
        return tasks.values().stream()
                .filter(task -> criteria.getAssignee() == null || criteria.getAssignee().equals(task.getAssignee()))
                .filter(task -> criteria.getDescription() == null || criteria.getAssignee().equals(task.getAssignee()))
                .filter(task -> criteria.getTitle() == null || criteria.getTitle().equals(task.getTitle()))
                .filter(task -> criteria.getStatus() == null || criteria.getStatus().equals(task.getStatus()))
                .filter(task -> criteria.getPriority() == null || criteria.getPriority().equals(task.getPriority()))
                .filter(task -> criteria.getDueDate() == null || criteria.getDueDate().equals(task.getDueDate()))
                .toList();
    }
}
