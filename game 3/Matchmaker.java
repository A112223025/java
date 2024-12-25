import java.util.ArrayList;
import java.util.List;

public class Matchmaker {
    private List<MatchCriteria> criteriaList = new ArrayList<>();

    // 添加匹配條件
    public void addCriteria(MatchCriteria criteria) {
        criteriaList.add(criteria);
    }


    // 匹配玩家，並記錄匹配結果到 MatchHistory
    public List<Player[]> matchPlayers(List<Player> players, MatchHistory matchHistory) {
        List<Player[]> matches = new ArrayList<>();

        // 兩兩比較玩家
        for (int i = 0; i < players.size(); i++) {
            for (int j = i + 1; j < players.size(); j++) {
                Player p1 = players.get(i);
                Player p2 = players.get(j);

                // 如果玩家不在線，跳過此組合
                if (!p1.isOnline() || !p2.isOnline()) {
                    continue;
                }

                // 先檢查遊戲模式是否相同
                if (!p1.getGameMode().getModeName().equals(p2.getGameMode().getModeName())) {
                    continue; // 如果遊戲模式不同，跳過此組合
                }

                // 檢查所有匹配條件，確保所有匹配條件都通過
                boolean allMatch = criteriaList.stream().allMatch(c -> c.match(p1, p2));
                if (allMatch) {
                    // 匹配成功，加入結果清單
                    matches.add(new Player[]{p1, p2});
                    matchHistory.addRecord(p1.getName() + " 與 " + p2.getName() + " 匹配成功！");
                }
            }
        }
        return matches;
    }

    public Player[] suggestBestMatch(Player target, List<Player> players) {
        Player[] bestMatch = null;
        int maxCriteriaMatch = 0;

        for (Player p : players) {
            if (target.equals(p)) continue; // 跳過自己

            int criteriaMatchCount = 0;
            for (MatchCriteria criteria : criteriaList) {
                if (criteria.match(target, p)) {
                    criteriaMatchCount++;
                }
            }

            if (criteriaMatchCount > maxCriteriaMatch) {
                maxCriteriaMatch = criteriaMatchCount;
                bestMatch = new Player[]{target, p};
            }
        }

        return bestMatch;
    }


}
