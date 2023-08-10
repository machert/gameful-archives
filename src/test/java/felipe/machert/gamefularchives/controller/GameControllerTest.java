package felipe.machert.gamefularchives.controller;

import felipe.machert.gamefularchives.dto.GameDto;
import felipe.machert.gamefularchives.model.GameModel;
import felipe.machert.gamefularchives.service.GameService;
import felipe.machert.gamefularchives.util.GameCreator;
import felipe.machert.gamefularchives.util.GameDtoCreator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@ExtendWith(SpringExtension.class)
public class GameControllerTest {
    @InjectMocks
    private GameController gameController;

    @Mock
    private GameService gameServiceMock;

    @BeforeEach
    void setUp(){

        PageImpl<GameModel> gamePage = new PageImpl<>(List.of(GameCreator.createValidGame()));

        BDDMockito.when(gameServiceMock.findAll(ArgumentMatchers.any()))
                .thenReturn(gamePage);

        BDDMockito.when(gameServiceMock.findAll())
                .thenReturn(List.of(GameCreator.createValidGame()));

        BDDMockito.when(gameServiceMock.findByIdOrThrowBadRequestException(ArgumentMatchers.anyLong()))
                .thenReturn(GameCreator.createValidGame());

        BDDMockito.when(gameServiceMock.save(ArgumentMatchers.any(GameDto.class)))
                .thenReturn(GameCreator.createValidGame());

        BDDMockito.doNothing().when(gameServiceMock).update(ArgumentMatchers.anyLong(), ArgumentMatchers.any(GameDto.class));


        System.out.println("setUp done");
    }

    @Test
    @DisplayName("List return of game inside page object when successful")
    void listReturnsListOfGamesInsidePageObjectWhenSuccessful(){
        String exceptedName = GameCreator.createValidGame().getName();
        Page<GameModel> gamePage = gameController.getAllGamesPages(null).getBody();

        Assertions.assertThat(gamePage).isNotNull();

        Assertions.assertThat(gamePage.toList())
                .isNotEmpty()
                .hasSize(1);

        Assertions.assertThat(gamePage.toList().get(0).getName())
                .isEqualTo(exceptedName);

        System.out.println("listReturnsListOfGamesInsidePageObjectWhenSuccessful done");
    }

    @Test
    @DisplayName("List return of game when successful")
    void listReturnsListOfGamesWhenSuccessful(){
        List<GameModel> gameList = gameController.getAllGames().getBody();

        Assertions.assertThat(gameList).isNotNull();

        Assertions.assertThat(gameList)
                .isNotEmpty()
                .hasSize(1);

        System.out.println("listReturnsListOfGamesWhenSuccessful done");
    }

    @Test
    @DisplayName("Find an game By Id then return game when successful")
    void findByIdReturnsGameWhenSuccessful(){
        Long exceptedId = GameCreator.createValidGame().getId();

        GameModel gameModel = gameController.getById(1).getBody();

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
        GameCreator.createGameToBeSaved();
        GameModel gameModel = gameController.saveGame(GameDtoCreator.createGamePostRequestBody()).getBody();

        Assertions.assertThat(gameModel)
                .isNotNull()
                .isEqualTo(GameCreator.createValidGame());

        System.out.println("saveReturnsGameWhenSuccessful done");
    }

    @Test
    @DisplayName("Replace update an game then return game when successful")
    void UpdateReturnsGameWhenSuccessful(){
        Assertions.assertThatCode(() -> gameController.updateGame(1L, GameDtoCreator.createGamePostRequestBody()))
                .doesNotThrowAnyException();

        ResponseEntity<Void> entity = gameController.updateGame(1L, GameDtoCreator.createGamePostRequestBody());

        Assertions.assertThat(entity).isNotNull();
        Assertions.assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);

        System.out.println("UpdateReturnsGameWhenSuccessful done");
    }


    @Test
    @DisplayName("Delete remove a game then return game when successful")
    void deleteRemoveReturnsGameWhenSuccessful(){
        Long exceptedId = GameCreator.createValidGame().getId();
        Assertions.assertThatCode(() -> gameController.deleteGame(1))
                .doesNotThrowAnyException();

        ResponseEntity<Void> entity = gameController.deleteGame(1);

        Assertions.assertThat(entity).isNotNull();
        Assertions.assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);

        System.out.println("deleteRemoveReturnsGameWhenSuccessful done");
    }



}
