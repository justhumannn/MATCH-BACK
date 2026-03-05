package post.post.domain.domain.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "bettings")

public class BettingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name="blue", nullable = false)
    private String blue;

    @Column(name = "red", nullable = false)
    private String red;

    @Column(name = "result", nullable = false)
    private String result;

    @Column(name = "status", nullable = false)
    private String status;

    @Builder(builderMethodName = "saveBettingBuilder")
    public BettingEntity(String title, String blue, String red, String result, String status){
        this.title = title;
        this.blue = blue;
        this.red = red;
        this.result = result;
        this.status = status;
    }
}
