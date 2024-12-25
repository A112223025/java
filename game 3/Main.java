import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // 創建玩家數據庫
        PlayerDatabase playerDatabase = new PlayerDatabase();

        // 創建遊戲模式
        GameMode singlePlayerMode = new SinglePlayerMode();
        GameMode multiPlayerMode = new MultiPlayerMode();

        // 註冊玩家
        playerDatabase.registerPlayer(new Player("Alice", 500, "Gold", singlePlayerMode));
        playerDatabase.registerPlayer(new Player("Bob", 550, "Gold", singlePlayerMode));
        playerDatabase.registerPlayer(new Player("Charlie", 450, "Silver", singlePlayerMode));
        playerDatabase.registerPlayer(new Player("Diana", 480, "Gold", multiPlayerMode));
        playerDatabase.registerPlayer(new Player("Eve", 600, "Gold", singlePlayerMode));

        // 顯示所有玩家
        System.out.println("所有玩家清單：");
        for (Player player : playerDatabase.getAllPlayers()) {
            System.out.println(player);
        }

        // 創建匹配條件
        MatchCriteria levelCriteria = new LevelBasedCriteria();
        MatchCriteria scoreCriteria = new ScoreBasedCriteria(100);

        // 創建 Matchmaker 並添加條件
        Matchmaker matchmaker = new Matchmaker();
        matchmaker.addCriteria(levelCriteria);
        matchmaker.addCriteria(scoreCriteria);

        // 創建匹配歷史管理
        MatchHistory matchHistory = new MatchHistory();

        // 進行匹配
        System.out.println("\n匹配結果：");
        List<Player> players = playerDatabase.getAllPlayers();
        List<Player[]> matches = matchmaker.matchPlayers(players, matchHistory);

        if (matches.isEmpty()) {
            System.out.println("沒有找到匹配的玩家。");
        }

        // 顯示匹配結果並保存到歷史
        System.out.println("\n匹配結果：");
        if (matches.isEmpty()) {
            System.out.println("沒有找到匹配的玩家。");
        } else {
            int matchIndex = 1;
            for (Player[] match : matches) {
                String record = "匹配 " + matchIndex + ": " + match[0].getName() + " 和 " + match[1].getName();
                System.out.println(record);
                matchHistory.addRecord(record);
                matchIndex++;
            }
        }

        // 顯示匹配歷史
        System.out.println("\n目前匹配歷史：");
        matchHistory.printHistory();

        // 清空歷史（測試功能）
        System.out.println("\n清空匹配歷史：");
        matchHistory.clearHistory();
        matchHistory.printHistory();

        // 測試最佳配對建議
        System.out.println("\n最佳配對建議（以 Alice 為目標）：");
        Player targetPlayer = playerDatabase.findPlayerByName("Alice");
        if (targetPlayer != null) {
            Player[] bestMatch = matchmaker.suggestBestMatch(targetPlayer, players);
            if (bestMatch != null) {
                System.out.println("最佳配對: " + bestMatch[0].getName() + " 和 " + bestMatch[1].getName());
            } else {
                System.out.println("沒有找到適合的最佳配對。");
            }
        }
    }
}

