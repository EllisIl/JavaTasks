import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TaskManager {
    private final List<Task> tasks;
    private static final String FILE_NAME = "tasks.txt";

    public TaskManager() {
        tasks = new ArrayList<>();
        loadTasksFromFile();
    }

    public void addTask(Task task) {
        tasks.add(task);
        saveTasksToFile(); // Save tasks every time a new task is added
    }

    public void editTask(int index, String newDescription) {
        if (index >= 0 && index < tasks.size()) {
            tasks.get(index).setDescription(newDescription);
            saveTasksToFile();
        }
    }

    public void markTaskAsComplete(int index) {
        if (index >= 0 && index < tasks.size()) {
            tasks.get(index).markAsComplete();
            saveTasksToFile();
        }
    }

    public void deleteTask(int index) {
        if (index >= 0 && index < tasks.size()) {
            tasks.remove(index);
            saveTasksToFile();
        }
    }

    public void listTasks() {
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            System.out.println(i + ": " + task.getTitle() + " - " + task.getDescription() + " [" + (task.isComplete() ? "Complete" : "Incomplete") + "] - Category: " + task.getCategory());
        }
    }

    public Set<String> getUniqueCategories() {
    Set<String> uniqueCategories = new HashSet<>();
    for (Task task : tasks) {
        uniqueCategories.add(task.getCategory());
    }
    return uniqueCategories;
}

    public List<Task> filterTasksByCategory(String category) {
        List<Task> filteredTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getCategory().equals(category)) {
                filteredTasks.add(task);
            }
        }
        return filteredTasks;
    }

    // Save tasks to a file
    private void saveTasksToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Task task : tasks) {
                writer.write(task.getTitle() + "|" + task.getDescription() + "|" + task.getCategory() + "|" + (task.isComplete() ? "true" : "false"));
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving tasks to file: " + e.getMessage());
        }
    }

    // Load tasks from a file
    private void loadTasksFromFile() {
        File file = new File(FILE_NAME);
        if (!file.exists()) {
            return; // If the file doesn't exist, just return
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                String title = parts[0];
                String description = parts[1];
                String category = parts[2];
                boolean isComplete = Boolean.parseBoolean(parts[3]);
                
                Task task = new Task(title, description, category);
                if (isComplete) {
                    task.markAsComplete(); // Mark as complete if applicable
                }
                tasks.add(task);
            }
        } catch (IOException e) {
            System.out.println("Error loading tasks from file: " + e.getMessage());
        }
    }
}
