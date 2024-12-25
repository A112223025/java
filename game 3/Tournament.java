
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

public class Tournament {
    private String name;
    private GameMode gameMode;
    private int maxParticipants;
    private List<Player> participants;
    private Map<Player, Integer> scores;
    private TournamentState state;

    public enum TournamentState {
        REGISTRATION,
        IN_PROGRESS,
        FINISHED
    }

    public Tournament(String name, GameMode gameMode, int maxParticipants) {
        this.name = name;
        this.gameMode = gameMode;
        this.maxParticipants = maxParticipants;
        this.participants = new ArrayList<>();
        this.scores = new HashMap<>();
        this.state = TournamentState.REGISTRATION;
    }

    public boolean registerPlayer(Player player) {
        if (state != TournamentState.REGISTRATION ||
                participants.size() >= maxParticipants ||
                !player.getGameMode().getModeName().equals(gameMode.getModeName())) {
            return false;
        }
        participants.add(player);
        scores.put(player, 0);
        return true;
    }

    public void startTournament() {
        if (state == TournamentState.REGISTRATION && participants.size() >= 2) {
            state = TournamentState.IN_PROGRESS;
        }
    }

    public void updateScore(Player player, int newScore) {
        if (state == TournamentState.IN_PROGRESS && participants.contains(player)) {
            scores.put(player, newScore);
        }
    }

    public void endTournament() {
        if (state == TournamentState.IN_PROGRESS) {
            state = TournamentState.FINISHED;
        }
    }

    public Player getWinner() {
        if (state != TournamentState.FINISHED) return null;
        return scores.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse(null);
    }

    // Getters
    public String getName() {
        return name;
    }

    public GameMode getGameMode() {
        return gameMode;
    }

    public TournamentState getState() {
        return state;
    }

    public List<Player> getParticipants() {
        return new ArrayList<>(participants);
    }

    public Map<Player, Integer> getScores() {
        return new HashMap<>(scores);
    }

}