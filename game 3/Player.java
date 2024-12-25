public class Player {
    private String name;
    private int score;
    private String level;
    private GameMode gameMode;
    private boolean online;
    private PlayerStats stats;

    public Player(String name, int score, String level, GameMode gameMode) {
        this.name = name;
        this.score = score;
        this.level = level;
        this.gameMode = gameMode;
        this.online = true;
        this.stats = new PlayerStats();
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public String getLevel() {
        return level;
    }

    public GameMode getGameMode() {
        return gameMode;
    }

    public boolean isOnline() {
        return online;
    }

    public void setOnline(boolean online) {
        this.online = online;
    }

    public PlayerStats getStats() {
        return stats;
    }

    @Override
    public String toString() {
        return "Player{name='" + name + "', score=" + score + ", level='" + level + "', mode=" + gameMode.getModeName() + "}";
    }
}