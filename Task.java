public class Task {
    private final String title;
    private String description;
    private boolean isComplete;
    private final String category;

    public Task(String title, String description, String category) {
        this.title = title;
        this.description = description;
        this.category = category;
        this.isComplete = false;
    }

    // Getters and setters
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public boolean isComplete() { return isComplete; }
    public String getCategory() { return category; }

    public void markAsComplete() { this.isComplete = true; }
    public void setDescription(String description) { this.description = description; }
}
