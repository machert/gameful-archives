package felipe.machert.gamefularchives.service;

import felipe.machert.gamefularchives.dto.GameDto;
import felipe.machert.gamefularchives.exception.BadRequestException;
import felipe.machert.gamefularchives.model.GameModel;
import felipe.machert.gamefularchives.repository.GameRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameService {
    final GameRepository gameRepository;

    public GameService(GameRepository gameRepository){
        this.gameRepository = gameRepository;
    }

    public List<GameModel> findAll(){

        List<GameModel> gameList = gameRepository.findAll();
//        if(!gameList.isEmpty()){
//            for(GameModel gameModel : gameList){
//                Long id = gameModel.getIdGame();
//                gameModel.add(linkTo(methodOn(GameController.class).getById(id)).withSelfRel());
//            }
//        }
        return gameList;
    }

    public Page<GameModel> findAll(Pageable pageable){

        Page<GameModel> gameList = gameRepository.findAll(pageable);
//        if(!gameList.isEmpty()){
//            for(GameModel gameModel : gameList){
//                Long id = gameModel.getIdGame();
//                gameModel.add(linkTo(methodOn(GameController.class).getById(id)).withSelfRel());
//            }
//        }
        return gameList;
    }

    public GameModel findByIdOrThrowBadRequestException(Long id){
        return gameRepository.findById(id).orElseThrow(() -> new BadRequestException("Game not found"));
    }
    @Transactional
    public GameModel save(GameDto gameDto){
        GameModel gameModel = new GameModel();
        BeanUtils.copyProperties(gameDto, gameModel);//convert the record to dto
        return gameRepository.save(gameModel);
    }

    @Transactional
    public void update(Long id, GameDto gameDto){
        GameModel oldGame = findByIdOrThrowBadRequestException(id);

        GameModel newGameModel = new GameModel();
        BeanUtils.copyProperties(oldGame, newGameModel);
        BeanUtils.copyProperties(gameDto, newGameModel);//Updates to new attributes of a game

        gameRepository.save(newGameModel);

    }

    @Transactional
    public void delete(Long id){
        GameModel gameModel = findByIdOrThrowBadRequestException(id);
        gameRepository.delete(gameModel);
    }
}
