package post.post.domain.domain.entity;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@Getter
@Entity
@Table(name = "transection")

public class TransectionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(cascade = CascadeType.MERGE, targetEntity = UserEntity.class)
    @JoinColumn(name="user_id", updatable = false)
    private UserEntity userEntity;

    @ManyToOne(cascade = CascadeType.MERGE, targetEntity = BettingEntity.class)
    @JoinColumn(name="betting_id", updatable = false)
    private BettingEntity bettingEntity;

    @Column(name = "betting_team",nullable = false)
    private String bettingTeam;

    @Column(name = "betting_cost", nullable = false)
    private Long bettingCost;

    @Builder(builderMethodName = "saveTransectionBuilder")
    public TransectionEntity(UserEntity userEntity, BettingEntity bettingEntity, String bettingTeam, Long bettingCost){
        this.userEntity = userEntity;
        this.bettingEntity = bettingEntity;
        this.bettingTeam = bettingTeam;
        this.bettingCost = bettingCost;
    }
}