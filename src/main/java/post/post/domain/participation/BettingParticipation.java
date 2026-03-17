package post.post.domain.participation;

import jakarta.persistence.*;
import lombok.*;
import post.post.domain.betting.BettingEntity;
import post.post.domain.user.UserEntity;

@NoArgsConstructor
@Getter
@Entity
@Table(name = "betting_participations")
public class BettingParticipation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name="user_id", updatable = false)
    private UserEntity user;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name="betting_id", updatable = false)
    private BettingEntity betting;

    @Column(name = "betting_team", nullable = false)
    private String bettingTeam;

    @Column(name = "betting_cost", nullable = false)
    private Long bettingCost;

    @Builder(builderMethodName = "saveParticipationBuilder")
    public BettingParticipation(UserEntity user, BettingEntity betting, String bettingTeam, Long bettingCost){
        this.user = user;
        this.betting = betting;
        this.bettingTeam = bettingTeam;
        this.bettingCost = bettingCost;
    }
}
