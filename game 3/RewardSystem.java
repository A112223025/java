import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
public class RewardSystem {

    private Map<String, List<Reward>> availableRewards;
    private PlayerDatabase playerDatabase;

    public RewardSystem(PlayerDatabase playerDatabase) {
        this.playerDatabase = playerDatabase;
        this.availableRewards = new HashMap<>();
        initializeRewards();
    }

    private void initializeRewards() {
        // Initialize different types of rewards
        List<Reward> achievementRewards = new ArrayList<>();
        achievementRewards.add(new Reward("First Win", "Win your first game", 100));
        achievementRewards.add(new Reward("Veterans Badge", "Play 100 games", 500));
        availableRewards.put("achievements", achievementRewards);
    }

    public void checkAndAwardAchievements(Player player) {
        PlayerStats stats = player.getStats();
        for (Reward reward : availableRewards.get("achievements")) {
            if (!reward.isAwarded() && reward.checkEligibility(stats)) {
                awardReward(player, reward);
            }
        }
    }

    private void awardReward(Player player, Reward reward) {
        reward.setAwarded(true);
        // Add reward to player's achievements
        player.getStats().getAchievements().add(reward.getName());
    }
}