import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

public class PlayerStats {
    private int gamesPlayed;
    private int wins;
    private int losses;
    private double winRate;
    private int totalPlayTime; // in minutes
    private List<String> achievements;
    private Map<String, Integer> gameModesPlayed;

    public PlayerStats() {
        this.gamesPlayed = 0;
        this.wins = 0;
        this.losses = 0;
        this.winRate = 0.0;
        this.totalPlayTime = 0;
        this.achievements = new ArrayList<>();
        this.gameModesPlayed = new HashMap<>();
    }

    public void updateStats(boolean won, int playTime, String gameMode) {
        gamesPlayed++;
        if (won) wins++;
        else losses++;
        winRate = (double) wins / gamesPlayed;
        totalPlayTime += playTime;
        gameModesPlayed.merge(gameMode, 1, Integer::sum);
    }

    // Getters
    public int getGamesPlayed() { return gamesPlayed; }
    public int getWins() { return wins; }
    public int getLosses() { return losses; }
    public double getWinRate() { return winRate; }
    public int getTotalPlayTime() { return totalPlayTime; }
    public List<String> getAchievements() { return new ArrayList<>(achievements); }
    public Map<String, Integer> getGameModesPlayed() { return new HashMap<>(gameModesPlayed); }
}