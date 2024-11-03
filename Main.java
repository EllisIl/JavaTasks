
import java.util.Scanner;
import java.util.Set;

public class Main {

    public static void main(String[] args) {
        TaskManager taskManager = new TaskManager();

        // // Adding preset data 
        // taskManager.addTask(new Task("Complete Java assignment", "Finish the Java project for class", "School"));
        // taskManager.addTask(new Task("Grocery shopping", "Buy vegetables and fruits", "Personal"));
        // taskManager.addTask(new Task("Gym workout", "Complete the cardio and weight training", "Health"));
        // taskManager.addTask(new Task("Read a book", "Read 'The Great Gatsby'", "Leisure"));
        try (Scanner scanner = new Scanner(System.in)) {
            String command;

            do {
                System.out.println("Enter command (add, edit, delete, list, filter, done, exit): ");
                command = scanner.nextLine().trim();

                switch (command) {
                    case "add" -> {
                        taskManager.listTasks();
                        System.out.println("Enter task title: ");
                        String title = scanner.nextLine();
                        System.out.println("Enter task description: ");
                        String description = scanner.nextLine();
                        System.out.println("Enter task category: ");
                        String category = scanner.nextLine();
                        taskManager.addTask(new Task(title, description, category));
                    }
                    case "edit" -> {
                        taskManager.listTasks();
                        System.out.println("Enter task index to edit: ");
                        int editIndex = scanner.nextInt();
                        scanner.nextLine(); // consume newline
                        System.out.println("Enter new description: ");
                        String newDescription = scanner.nextLine();
                        taskManager.editTask(editIndex, newDescription);
                    }
                    case "delete" -> {
                        taskManager.listTasks();
                        System.out.println("Enter task index to delete: ");
                        int deleteIndex = scanner.nextInt();
                        scanner.nextLine(); // consume newline
                        taskManager.deleteTask(deleteIndex);
                    }
                    case "list" -> {
                        taskManager.listTasks();
                    }
                    case "filter" -> {
                        Set<String> uniqueCategories = taskManager.getUniqueCategories();
                        System.out.println("Available categories:");
                        for (String cat : uniqueCategories) {
                            System.out.println("- " + cat);
                        }
                        System.out.println("Enter category to filter: ");
                        String filterCategory = scanner.nextLine();
                        for (Task task : taskManager.filterTasksByCategory(filterCategory)) {
                            System.out.println(task.getTitle() + " - " + task.getDescription());
                        }
                    }
                    case "done" -> {
                        System.out.println("Enter task index to mark as done: ");
                        int doneIndex = scanner.nextInt();
                        scanner.nextLine(); // consume newline
                        taskManager.markTaskAsComplete(doneIndex);
                    }
                    case "exit" ->
                        System.out.println("Exiting...");
                    default ->
                        System.out.println("Invalid command. Try again.");
                }
            } while (!command.equals("exit"));
        }
    }
}
