public class LevelBasedCriteria implements MatchCriteria {
    @Override
    public boolean match(Player p1, Player p2) {
        return p1.getLevel().equals(p2.getLevel());
    }
}
