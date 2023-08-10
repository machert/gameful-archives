package felipe.machert.gamefularchives.service;

import felipe.machert.gamefularchives.model.GameModel;
import felipe.machert.gamefularchives.repository.GameRepository;
import felipe.machert.gamefularchives.util.GameCreator;
import felipe.machert.gamefularchives.util.GameDtoCreator;

import java.util.List;
import java.util.Optional;

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
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
public class GameServiceTest {
    @InjectMocks
    private GameService gameService;

    @Mock
    private GameRepository gameRepositoryMock;

    @BeforeEach
    void setUp(){

        PageImpl<GameModel> gamePage = new PageImpl<>(List.of(GameCreator.createValidGame()));

        BDDMockito.when(gameRepositoryMock.findAll(ArgumentMatchers.any(PageRequest.class)))
                .thenReturn(gamePage);

        BDDMockito.when(gameRepositoryMock.findAll())
                .thenReturn(List.of(GameCreator.createValidGame()));

        BDDMockito.when(gameRepositoryMock.findById(ArgumentMatchers.anyLong()))
                .thenReturn(Optional.of(GameCreator.createValidGame()));

        BDDMockito.when(gameRepositoryMock.save(ArgumentMatchers.any(GameModel.class)))
                .thenReturn(GameCreator.createValidGame());

        BDDMockito.doNothing().when(gameRepositoryMock).delete(ArgumentMatchers.any(GameModel.class));

        System.out.println("setUp done");
    }

    @Test
    @DisplayName("List return of game inside page object when successful")
    void listReturnsListOfGamesInsidePageObjectWhenSuccessful(){
        String exceptedName = GameCreator.createValidGame().getName();
        Page<GameModel> gamePage = gameService.findAll(PageRequest.of(1, 1));

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
        List<GameModel> gameList = gameService.findAll();

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

        GameModel gameModel = gameService.findByIdOrThrowBadRequestException(1l);

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
        GameModel gameModel = gameService.save(GameDtoCreator.createGamePostRequestBody());

        Assertions.assertThat(gameModel)
                .isNotNull()
                .isEqualTo(GameCreator.createValidGame());

        System.out.println("saveReturnsGameWhenSuccessful done");
    }

    @Test
    @DisplayName("Replace update an game then return game when successful")
    void UpdateReturnsGameWhenSuccessful(){
        Assertions.assertThatCode(() -> gameService.update(1L, GameDtoCreator.createGamePostRequestBody()))
                .doesNotThrowAnyException();

        System.out.println("UpdateReturnsGameWhenSuccessful done");
    }

    @Test
    @DisplayName("Delete remove a game then return game when successful")
    void deleteRemoveReturnsGameWhenSuccessful(){
        Assertions.assertThatCode(() -> gameService.delete(1l))
                .doesNotThrowAnyException();
        System.out.println("deleteRemoveReturnsGameWhenSuccessful done");
    }

}
