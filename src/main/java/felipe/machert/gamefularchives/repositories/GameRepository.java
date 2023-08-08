package felipe.machert.gamefularchives.repositories;

import felipe.machert.gamefularchives.models.GameModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameRepository extends JpaRepository<GameModel, Long> {

}
