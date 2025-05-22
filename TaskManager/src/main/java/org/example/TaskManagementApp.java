package org.example;

import org.example.constants.TaskPriority;
import org.example.constants.TaskStatus;
import org.example.entities.Task;
import org.example.entities.User;
import org.example.manager.TaskManager;
import org.example.manager.impl.TaskManagerImpl;
import org.example.services.FilterCriteria;

import java.time.LocalDateTime;
import java.util.List;

public class TaskManagementApp {
    public static void main(String[] args) {
        User user1 = new User("Aayusha", "aisha@gmail.com");
        User user2 = new User("Ankit", "ankit@gmail.com");

        Task task1 = new Task("Task 1", "Desc 1", LocalDateTime.now().plusDays(3), TaskPriority.LOW, user1);
        Task task2 = new Task("Task 2", "Desc 2", LocalDateTime.now().plusDays(1), TaskPriority.HIGH, user1);

        TaskManager taskManager = TaskManagerImpl.getInstance();

        taskManager.createTask(task1);
        taskManager.createTask(task2);

        List<Task> tasks = taskManager.getTaskHistory(user1.getId());
        displayTasks(tasks, user1);

        FilterCriteria filterCriteria = new FilterCriteria();

        // first criteria
        filterCriteria.setAssignee(user1);
        List<Task> filteredTasks = taskManager.getTaskByFilter(filterCriteria);
        displayTasks(filteredTasks, user1);

        taskManager.updateStatus(task1, TaskStatus.COMPLETED);
        // second criteria
        filterCriteria.setStatus(TaskStatus.TO_DO);
        List<Task> filteredTasks1 = taskManager.getTaskByFilter(filterCriteria);
        displayTasks(filteredTasks1, user1);

        taskManager.deleteTask(task2);
        List<Task> tasks2 = taskManager.getTaskHistory(user1.getId());
        displayTasks(tasks2, user1);
    }


    private static void displayTasks(List<Task> tasks, User user) {
        if (!tasks.isEmpty()) {
            System.out.println("Below tasks are assigned to: " + user.getName());
            for (Task task : tasks) {
                System.out.println(task.getTitle());
            }
        } else {
            System.out.println("No tasks present for user: " + user.getName());
        }
    }
}
