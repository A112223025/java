public class Achievement {
    private String name;
    private String description;
    private AchievementType type;
    private int requiredValue;
    private boolean unlocked;

    public enum AchievementType {
        GAMES_PLAYED,
        WINS,
        PLAY_TIME,
        WIN_STREAK
    }

    public Achievement(String name, String description, AchievementType type, int requiredValue) {
        this.name = name;
        this.description = description;
        this.type = type;
        this.requiredValue = requiredValue;
        this.unlocked = false;
    }

    public boolean checkUnlock(PlayerStats stats) {
        if (unlocked) return true;

        boolean shouldUnlock = switch (type) {
            case GAMES_PLAYED -> stats.getGamesPlayed() >= requiredValue;
            case WINS -> stats.getWins() >= requiredValue;
            case PLAY_TIME -> stats.getTotalPlayTime() >= requiredValue;
            case WIN_STREAK -> false; // Requires additional tracking
        };

        if (shouldUnlock) {
            unlocked = true;
            return true;
        }
        return false;
    }
}
