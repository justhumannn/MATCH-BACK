package post.post.domain.user;

import jakarta.persistence.*;
import lombok.*;
import post.post.global.exception.BusinessException;
import post.post.global.exception.ErrorCode;

@NoArgsConstructor
@Getter
@Entity
@Table(name = "wallets")
public class Wallet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "balance", nullable = false)
    private long balance;

    @OneToOne(mappedBy = "wallet")
    private UserEntity user;

    public Wallet(long balance) {
        this.balance = balance;
    }

    public void charge(long amount) {
        if (amount < 0) {
            throw new BusinessException(ErrorCode.INVALID_INPUT_VALUE);
        }
        this.balance += amount;
    }

    public void deduct(long amount) {
        if (amount < 0) {
            throw new BusinessException(ErrorCode.INVALID_INPUT_VALUE);
        }
        if (this.balance < amount) {
            throw new BusinessException(ErrorCode.INSUFFICIENT_BALANCE);
        }
        this.balance -= amount;
    }
}
