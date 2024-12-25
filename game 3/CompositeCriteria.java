import java.util.List;

public class CompositeCriteria implements MatchCriteria {
    private List<MatchCriteria> criteriaList;

    public CompositeCriteria(List<MatchCriteria> criteriaList) {
        this.criteriaList = criteriaList;
    }

    @Override
    public boolean match(Player p1, Player p2) {
        return criteriaList.stream().allMatch(c -> c.match(p1, p2));
    }
}
