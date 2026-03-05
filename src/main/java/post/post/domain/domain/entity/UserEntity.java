package post.post.domain.domain.entity;

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

    @Column(name = "cost", nullable = false)
    private long cost;

    @Builder(builderMethodName = "saveUserBuilder")
    public UserEntity(String name, String email, Long cost){
        this.name = name;
        this.email = email;
        this.cost = cost;
    }
}
