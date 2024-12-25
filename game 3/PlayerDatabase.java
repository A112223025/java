import java.util.ArrayList;
import java.util.List;

public class PlayerDatabase {
    private List<Player> players; // 儲存玩家的集合

    // 建構子，初始化玩家清單(當創建一個新的 PlayerDatabase 物件時，會自動準備好一個空的玩家清單。)
    public PlayerDatabase() {
        this.players = new ArrayList<>();
    }

    // 註冊新玩家，防止名稱重複
    public void registerPlayer(Player player) {
        // 檢查名稱是否已存在
        if (findPlayerByName(player.getName()) != null) {
            System.out.println("玩家名稱已存在：" + player.getName());
            return; // 終止方法執行，防止重複加入
        }
        // 新增玩家到清單
        players.add(player);
        System.out.println("玩家 " + player.getName() + " 已成功註冊！");
    }



    // 根據名稱查詢玩家
    public Player findPlayerByName(String name) {
        for (Player player : players) {
            if (player.getName().equals(name)) {
                return player; // 找到匹配的玩家並回傳
            }
        }
        System.out.println("玩家 " + name + " 不存在！");
        return null; // 如果沒有找到，回傳 null
    }

    // 刪除玩家
    public boolean removePlayer(String name) {
        Player player = findPlayerByName(name); // 查詢玩家
        // 從清單中移除
        if (player != null) {
            players.remove(player);
            System.out.println("玩家 " + name + " 已被移除！");
            return true;
        }
        //處理不存在的玩家
        System.out.println("玩家 " + name + " 不存在！");
        return false;
    }

    // 獲取所有玩家
    public List<Player> getAllPlayers() {
        return players; // 回傳玩家清單
    }
}

