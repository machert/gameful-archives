package felipe.machert.gamefularchives.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Table(name = "games")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GameModel implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 100)
    private String name;
    @Column(nullable = true, length = 70)
    private String platform;
    @Column(nullable = true)
    private Integer year_finished;
}
