import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class RankingSystem {
    private Map<String, List<Player>> rankingsByMode;
    private static final int RANKING_UPDATE_INTERVAL = 60; // minutes

    public RankingSystem() {
        this.rankingsByMode = new HashMap<>();
        scheduleRankingUpdates();
    }

    private void scheduleRankingUpdates() {
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                updateAllRankings();
            }
        }, 0, RANKING_UPDATE_INTERVAL * 60 * 1000);
    }

    public void updateAllRankings() {
        for (Map.Entry<String, List<Player>> entry : rankingsByMode.entrySet()) {
            String mode = entry.getKey();
            List<Player> players = entry.getValue();

            players.sort((p1, p2) -> {
                double score1 = calculateScore(p1);
                double score2 = calculateScore(p2);
                return Double.compare(score2, score1);
            });
        }
    }

    private double calculateScore(Player player) {
        PlayerStats stats = player.getStats();
        return (stats.getWinRate() * 1000) + (stats.getGamesPlayed() * 0.1);
    }

    // 添加玩家到特定模式的排名中
    public void addPlayerToMode(String mode, Player player) {
        rankingsByMode.computeIfAbsent(mode, k -> new ArrayList<>()).add(player);
    }

    // 獲取特定模式的排名
    public List<Player> getRankingsByMode(String mode) {
        return new ArrayList<>(rankingsByMode.getOrDefault(mode, new ArrayList<>()));
    }
}