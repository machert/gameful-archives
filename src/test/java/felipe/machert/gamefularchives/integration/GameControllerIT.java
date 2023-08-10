package felipe.machert.gamefularchives.integration;

import felipe.machert.gamefularchives.model.GameModel;
import felipe.machert.gamefularchives.repository.GameRepository;
import felipe.machert.gamefularchives.util.GameCreator;
import felipe.machert.gamefularchives.wrapper.PageableResponse;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;

import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class GameControllerIT {
    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private GameRepository gameRepository;


    @Test
    @DisplayName("List return of games inside page object when successful")
    void listReturnsListOfGamesInsidePageObjectWhenSuccessful(){
        GameModel gameModel = gameRepository.save(GameCreator.createGameToBeSaved());
        String exceptedName = gameModel.getName();

        PageableResponse<GameModel> gamePage = testRestTemplate.exchange("/games/pages",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<PageableResponse<GameModel>>() {
                }).getBody();

        Assertions.assertThat(gamePage).isNotNull();

        Assertions.assertThat(gamePage.toList())
                .isNotEmpty()
                .hasSize(1);

        Assertions.assertThat(gamePage.toList().get(0).getName())
                .isEqualTo(exceptedName);

        System.out.println("listReturnsListOfGamesInsidePageObjectWhenSuccessful done");
    }

    @Test
    @DisplayName("List return a game when successful")
    void listReturnsListOfGamesWhenSuccessful(){
        gameRepository.save(GameCreator.createGameToBeSaved());
        List<GameModel> gameList = gameRepository.findAll();

        Assertions.assertThat(gameList).isNotNull();

        Assertions.assertThat(gameList)
                .isNotEmpty()
                .hasSize(1);

        System.out.println("listReturnsListOfGamesWhenSuccessful done");
    }

    @Test
    @DisplayName("Find an game By Id then return game when successful")
    void findByIdReturnsGameWhenSuccessful(){
        GameModel gameModel = gameRepository.save(GameCreator.createGameToBeSaved());

        long exceptedId = gameModel.getId();

        List<GameModel> games = testRestTemplate.exchange("/games", HttpMethod.GET, null,
                new ParameterizedTypeReference<List<GameModel>>() {
                }).getBody();

        Assertions.assertThat(gameModel)
                .isNotNull();

        Assertions.assertThat(gameModel.getId())
                .isNotNull()
                .isEqualTo(exceptedId);

        System.out.println("findByIdReturnsGameWhenSuccessful done");
    }

    @Test
    @DisplayName("save an game then return game when successful")
    void saveReturnsGameWhenSuccessful(){

        ResponseEntity<GameModel> gameResponseEntity = testRestTemplate.exchange("/games",
                HttpMethod.POST,
                new HttpEntity<>(GameCreator.createGameToBeSaved()),
                GameModel.class);

        Assertions.assertThat(gameResponseEntity).isNotNull();
        Assertions.assertThat(gameResponseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        Assertions.assertThat(gameResponseEntity.getBody()).isNotNull();
        Assertions.assertThat(gameResponseEntity.getBody().getId()).isNotNull();

        System.out.println("saveReturnsGameWhenSuccessful done");
    }

    @Test
    @DisplayName("Replace update an game then return game when successful")
    void UpdateReturnsGameWhenSuccessful(){

        GameModel savedGame = gameRepository.save(GameCreator.createGameToBeSaved());

        savedGame.setName("Prince of persia 2 Warrior within");

        ResponseEntity<Void> gameResponseEntity = testRestTemplate.exchange("/games/{id}",
                HttpMethod.PUT,
                new HttpEntity<>(savedGame),
                Void.class,
                savedGame.getId());

        Assertions.assertThat(gameResponseEntity).isNotNull();
        Assertions.assertThat(gameResponseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);

        System.out.println("UpdateReturnsGameWhenSuccessful done");
    }


    @Test
    @DisplayName("Delete remove a game then return game when successful")
    void deleteRemoveReturnsGameWhenSuccessful(){

        GameModel savedGame = gameRepository.save(GameCreator.createGameToBeSaved());

        ResponseEntity<Void> gameResponseEntity = testRestTemplate.exchange("/games/{id}",
                HttpMethod.DELETE,
                null,
                Void.class,
                savedGame.getId());

        Assertions.assertThat(gameResponseEntity).isNotNull();
        Assertions.assertThat(gameResponseEntity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);

        System.out.println("deleteRemoveReturnsGameWhenSuccessful done");
    }


}
