public class Reward {
    private String name;
    private String description;
    private int points;
    private boolean awarded;

    public Reward(String name, String description, int points) {
        this.name = name;
        this.description = description;
        this.points = points;
        this.awarded = false;
    }

    public boolean checkEligibility(PlayerStats stats) {
        // Implement eligibility logic based on stats
        return false;
    }

    // Getters and setters
    public String getName() { return name; }
    public String getDescription() { return description; }
    public int getPoints() { return points; }
    public boolean isAwarded() { return awarded; }
    public void setAwarded(boolean awarded) { this.awarded = awarded; }
}
