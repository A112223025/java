public class ScoreBasedCriteria implements MatchCriteria {
    private int scoreRange;

    public ScoreBasedCriteria(int scoreRange) {
        this.scoreRange = scoreRange;
    }

    @Override
    public boolean match(Player p1, Player p2) {
        return Math.abs(p1.getScore() - p2.getScore()) <= scoreRange;
    }
}

