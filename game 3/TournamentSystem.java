// TournamentSystem.java
import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

public class TournamentSystem {
    private List<Tournament> activeTournaments;
    private PlayerDatabase playerDatabase;
    private MatchHistory matchHistory;

    public TournamentSystem(PlayerDatabase playerDatabase, MatchHistory matchHistory) {
        this.activeTournaments = new ArrayList<>();
        this.playerDatabase = playerDatabase;
        this.matchHistory = matchHistory;
    }

    public Tournament createTournament(String name, GameMode gameMode, int maxParticipants) {
        Tournament tournament = new Tournament(name, gameMode, maxParticipants);
        activeTournaments.add(tournament);
        return tournament;
    }

    public Optional<Tournament> findTournament(String name) {
        return activeTournaments.stream()
                .filter(t -> t.getName().equals(name))
                .findFirst();
    }

    public void removeTournament(Tournament tournament) {
        activeTournaments.remove(tournament);
    }

    public List<Tournament> getActiveTournaments() {
        return new ArrayList<>(activeTournaments);
    }
}

//