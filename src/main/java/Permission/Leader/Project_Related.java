package Permission.Leader;

public interface Project_Related {
    public void UpdateProjectStatus(Status status);
}

enum Status {
    IN_PROGRESS, PAUSED, COMPLETED
}
