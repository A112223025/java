public class TeamMode implements GameMode {
    @Override
    public String getModeName() {
        // 團隊模式的名稱
        return "Team Mode";
    }

    @Override
    public int getMaxPlayers() {
        // 團隊模式最多允許 6 名玩家
        return 6;
    }
}

