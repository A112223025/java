import java.time.LocalDateTime;
import java.time.Duration;

public class Game {
    private Player player1;
    private Player player2;
    private GameState state;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Player winner;

    public enum GameState {
        WAITING,
        IN_PROGRESS,
        FINISHED,
        CANCELLED
    }

    public Game(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
        this.state = GameState.WAITING;
    }

    public void start() {
        state = GameState.IN_PROGRESS;
        startTime = LocalDateTime.now();
        // Simulate game logic
    }

    public void end(Player winner) {
        this.winner = winner;
        this.state = GameState.FINISHED;
        this.endTime = LocalDateTime.now();
        updatePlayerStats();
    }

    private void updatePlayerStats() {
        Duration gameDuration = Duration.between(startTime, endTime);
        int playTimeMinutes = (int) gameDuration.toMinutes();

        if (winner.equals(player1)) {
            player1.getStats().updateStats(true, playTimeMinutes, player1.getGameMode().getModeName());
            player2.getStats().updateStats(false, playTimeMinutes, player2.getGameMode().getModeName());
        } else {
            player1.getStats().updateStats(false, playTimeMinutes, player1.getGameMode().getModeName());
            player2.getStats().updateStats(true, playTimeMinutes, player2.getGameMode().getModeName());
        }
    }
}
