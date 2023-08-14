package felipe.machert.gamefularchives.repository;

import felipe.machert.gamefularchives.model.GameModel;
import felipe.machert.gamefularchives.util.GameCreator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

@DataJpaTest
@DisplayName("Unit Tests for game repository")
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

    @Test
    @DisplayName("Delete a game and save it when successful ")
    void deletePersistGameWhenSuccessful(){
        GameModel gameToBeSaved = GameCreator.createGameToBeSaved();
        GameModel gameSaved = this.gameRepository.save(gameToBeSaved);

        this.gameRepository.deleteById(gameSaved.getId());

        Optional<GameModel> gameDeleted = this.gameRepository.findById(gameSaved.getId());

        Assertions.assertThat(gameDeleted.isEmpty()).isTrue();

        System.out.println("deletePersistGameWhenSuccessful done");
    }


    @Test
    @DisplayName("Return a list of games when successful ")
    void getAllGamesWhenSuccessful(){
        GameModel gameToBeSaved = GameCreator.createGameToBeSaved();
        this.gameRepository.save(gameToBeSaved);

        List<GameModel> games = this.gameRepository.findAll();

        Assertions.assertThat(games).isNotEmpty();

        System.out.println("getAllGamesWhenSuccessful done");
    }

    @Test
    @DisplayName("Get by id a game and  return a list of games when successful ")
    void getByIdReturnGameWhenSuccessful(){
        GameModel gameToBeSaved = GameCreator.createGameToBeSaved();
        this.gameRepository.save(gameToBeSaved);

        Optional<GameModel> game = this.gameRepository.findById(gameToBeSaved.getId());

        Assertions.assertThat(game).isNotEmpty();
        Assertions.assertThat(game.get().getId()).isNotNull();
        Assertions.assertThat(game.get().getName()).isEqualTo(gameToBeSaved.getName());

        System.out.println("getAllGamesWhenSuccessful done");
    }

    @Test
    @DisplayName("Find by id and return empty when not found ")
    void getByIdReturnEmptyWhenGameNotFound(){
        GameModel gameToBeSaved = GameCreator.createGameToBeSaved();
        this.gameRepository.save(gameToBeSaved);

        Optional<GameModel> game = this.gameRepository.findById(2L);

        Assertions.assertThat(game).isEmpty();

        System.out.println("getByIdReturnEmptyWhenGameNotFound done");
    }
}
