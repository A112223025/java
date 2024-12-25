public class MultiPlayerMode implements GameMode{
    @Override
    public String getModeName() {
        // 多人模式的名稱
        return "Multiplayer";
    }

    @Override
    public int getMaxPlayers() {
        // 多人模式最多允許 4 名玩家
        return 4;
    }
}

