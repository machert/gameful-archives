package felipe.machert.gamefularchives.repository;

import felipe.machert.gamefularchives.model.GameModel;
import felipe.machert.gamefularchives.util.GameCreator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
@DisplayName("Tests for game repository")
public class GameRepositoryTest {

    @Autowired
    private GameRepository gameRepository;


    @Test
    @DisplayName("Create a game and save it when successful ")
    void savePersistGameWhenSuccessful(){
        GameModel gameToBeSaved = GameCreator.createGameToBeSaved();
        GameModel gameSaved = this.gameRepository.save(gameToBeSaved);

        Assertions.assertThat(gameSaved).isNotNull();
        Assertions.assertThat(gameSaved.getId()).isNotNull();
        Assertions.assertThat(gameSaved.getName()).isEqualTo(gameToBeSaved.getName());
        System.out.println("savePersistGameWhenSuccessful done");
    }

    @Test
    @DisplayName("Update a game and save it when successful ")
    void updatePersistGameWhenSuccessful(){
        GameModel gameToBeSaved = GameCreator.createGameToBeSaved();
        GameModel gameSaved = this.gameRepository.save(gameToBeSaved);

        gameSaved.setName("Dark souls 11");

        GameModel gameUpdated = this.gameRepository.save(gameSaved);

        Assertions.assertThat(gameUpdated).isNotNull();
        Assertions.assertThat(gameUpdated.getId()).isNotNull();
        Assertions.assertThat(gameUpdated.getName()).isEqualTo(gameSaved.getName());

        System.out.println("updatePersistGameWhenSuccessful done");
    }
}
