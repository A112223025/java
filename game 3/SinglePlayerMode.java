public class SinglePlayerMode implements GameMode{
    @Override
    public String getModeName() {
        // 單人模式的名稱
        return "Single Player";
    }

    @Override
    public int getMaxPlayers() {
        // 單人模式最多允許 1 名玩家
        return 1;
    }
}
