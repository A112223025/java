import java.util.Map;
import java.util.Queue;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.List;

public class MatchmakingQueue {
    private Map<String, Queue<Player>> queuesByMode;
    private Map<String, List<MatchCriteria>> criteriaByMode;
    private MatchHistory matchHistory;

    public MatchmakingQueue(MatchHistory matchHistory) {
        this.queuesByMode = new HashMap<>();
        this.criteriaByMode = new HashMap<>();
        this.matchHistory = matchHistory;
    }

    public void addToQueue(Player player) {
        String mode = player.getGameMode().getModeName();
        queuesByMode.computeIfAbsent(mode, k -> new LinkedList<>()).offer(player);
        tryMatch(mode);
    }

    // 添加匹配準則到特定遊戲模式
    public void addCriteriaForMode(String mode, MatchCriteria criteria) {
        criteriaByMode.computeIfAbsent(mode, k -> new ArrayList<>()).add(criteria);
    }

    private void tryMatch(String mode) {
        Queue<Player> queue = queuesByMode.get(mode);
        if (queue == null || queue.size() < 2) return;

        List<Player> potentialMatches = new ArrayList<>(queue);
        Matchmaker matchmaker = new Matchmaker();

        // 添加該模式的所有匹配準則
        criteriaByMode.getOrDefault(mode, new ArrayList<>())
                .forEach(matchmaker::addCriteria);

        List<Player[]> matches = matchmaker.matchPlayers(potentialMatches, matchHistory);
        for (Player[] match : matches) {
            queue.remove(match[0]);
            queue.remove(match[1]);
            startGame(match[0], match[1]);
        }
    }

    private void startGame(Player p1, Player p2) {
        Game game = new Game(p1, p2);
        game.start();
    }
}