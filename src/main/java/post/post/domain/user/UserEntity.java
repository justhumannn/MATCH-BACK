package post.post.domain.user;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@Getter
@Entity
@Table(name = "users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "email", nullable = false)
    private String email;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "wallet_id")
    private Wallet wallet;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private java.util.List<post.post.domain.participation.BettingParticipation> participations = new java.util.ArrayList<>();

    @Builder(builderMethodName = "saveUserBuilder")
    public UserEntity(String name, String email, Long initialBalance){
        this.name = name;
        this.email = email;
        this.wallet = new Wallet(initialBalance);
    }

    public long getCost() {
        return this.wallet.getBalance();
    }

    public void deductBalance(long amount) {
        this.wallet.deduct(amount);
    }

    public void chargeBalance(long amount) {
        this.wallet.charge(amount);
    }
}
