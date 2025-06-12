import java.util.ArrayList;
import java.util.Scanner;

public class TodoListApp {

    private ArrayList<String> tasks;
    private Scanner scanner;

    public TodoListApp() {
        tasks = new ArrayList<>();
        scanner = new Scanner(System.in);
    }

    public void run() {
        System.out.println("=== Welcome to the To-Do List Application ===");
        boolean exit = false;
        while (!exit) {
            printMenu();
            int choice = getUserChoice();
            switch (choice) {
                case 1:
                    addTask();
                    break;
                case 2:
                    viewTasks();
                    break;
                case 3:
                    removeTask();
                    break;
                case 4:
                    exit = true;
                    System.out.println("Exiting application. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number from 1 to 4.");
            }
        }
        scanner.close();
    }

    private void printMenu() {
        System.out.println("\nPlease choose an option:");
        System.out.println("1. Add new task");
        System.out.println("2. View all tasks");
        System.out.println("3. Remove a task (by index or name)");
        System.out.println("4. Exit");
        System.out.print("Your choice: ");
    }

    private int getUserChoice() {
        while (true) {
            String input = scanner.nextLine().trim();
            try {
                int choice = Integer.parseInt(input);
                return choice;
            } catch (NumberFormatException e) {
                System.out.print("Invalid input. Please enter a number (1-4): ");
            }
        }
    }

    private void addTask() {
        System.out.print("Enter the task description: ");
        String task = scanner.nextLine().trim();
        if (task.isEmpty()) {
            System.out.println("Task description cannot be empty.");
            return;
        }
        tasks.add(task);
        System.out.println("Task added successfully.");
    }

    private void viewTasks() {
        if (tasks.isEmpty()) {
            System.out.println("No tasks found.");
        } else {
            System.out.println("\nYour Tasks:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.printf("%d. %s%n", i + 1, tasks.get(i));
            }
        }
    }

    private void removeTask() {
        if (tasks.isEmpty()) {
            System.out.println("No tasks to remove.");
            return;
        }
        System.out.println("Remove by:");
        System.out.println("1. Index");
        System.out.println("2. Name");
        System.out.print("Choose method (1 or 2): ");
        int method = getRemovalMethod();
        switch (method) {
            case 1:
                removeByIndex();
                break;
            case 2:
                removeByName();
                break;
            default:
                System.out.println("Invalid removal method.");
        }
    }

    private int getRemovalMethod() {
        while (true) {
            String input = scanner.nextLine().trim();
            if (input.equals("1") || input.equals("2")) {
                return Integer.parseInt(input);
            } else {
                System.out.print("Invalid input. Please enter 1 or 2: ");
            }
        }
    }

    private void removeByIndex() {
        viewTasks();
        System.out.print("Enter the task number to remove: ");
        while (true) {
            String input = scanner.nextLine().trim();
            try {
                int index = Integer.parseInt(input);
                if (index < 1 || index > tasks.size()) {
                    System.out.print("Invalid task number. Please enter a valid task number: ");
                } else {
                    String removed = tasks.remove(index - 1);
                    System.out.println("Removed task: " + removed);
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.print("Invalid input. Please enter a task number: ");
            }
        }
    }

    private void removeByName() {
        System.out.print("Enter the exact task name to remove: ");
        String nameToRemove = scanner.nextLine().trim();
        if (nameToRemove.isEmpty()) {
            System.out.println("Task name cannot be empty.");
            return;
        }
        boolean removed = tasks.remove(nameToRemove);
        if (removed) {
            System.out.println("Task \"" + nameToRemove + "\" removed successfully.");
        } else {
            System.out.println("Task not found: \"" + nameToRemove + "\"");
        }
    }

    public static void main(String[] args) {
        TodoListApp app = new TodoListApp();
        app.run();
    }
}

