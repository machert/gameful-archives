package felipe.machert.gamefularchives.controllers;
import felipe.machert.gamefularchives.Dtos.GameRecordDto;
import felipe.machert.gamefularchives.models.GameModel;
import felipe.machert.gamefularchives.repositories.GameRepository;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class GameController {

    @Autowired
    GameRepository gameRepository;


    @GetMapping("/games")
    public ResponseEntity<List<GameModel>> getAllGames(){
        List<GameModel> gameList = gameRepository.findAll();
        if(!gameList.isEmpty()){
            for(GameModel gameModel : gameList){
                Long id = gameModel.getIdGame();
                gameModel.add(linkTo(methodOn(GameController.class).getById(id)).withSelfRel());
            }
        }
        return ResponseEntity.status(HttpStatus.OK).body(gameList);
    }

    @GetMapping("/games/pages")
    public ResponseEntity<Page<GameModel>> getAllGamesPages(Pageable pageable){
        Page<GameModel> gamePages = gameRepository.findAll(pageable);
        return ResponseEntity.status(HttpStatus.OK).body(gamePages);
    }

    @GetMapping("/games/{id}")
    public ResponseEntity<Object> getById(@PathVariable Long id){
        Optional<GameModel> game = gameRepository.findById(id);
        if(game.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Game not found");
        }
        return ResponseEntity.status(HttpStatus.OK).body(game.get());
    }

    @PostMapping("/games")
    public ResponseEntity<GameModel> saveGame(@RequestBody @Valid GameRecordDto gameRecordDto ){
        GameModel gameModel = new GameModel();
        BeanUtils.copyProperties(gameRecordDto, gameModel);//convert the record to dto
        return ResponseEntity.status(HttpStatus.CREATED).body(gameRepository.save(gameModel));
    }

    @PutMapping("/games/{id}")
    public ResponseEntity<Object> updateGame(@PathVariable Long id, @RequestBody @Valid GameRecordDto gameRecordDto ){

        Optional<GameModel> oldGame = gameRepository.findById(id);
        if(oldGame.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Game not found");
        }

        GameModel newGameModel = oldGame.get();
        BeanUtils.copyProperties(gameRecordDto, newGameModel);//Updates to new attributes of a game

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/games/{id}")
    public ResponseEntity<Object> updateGame(@PathVariable Long id){

        Optional<GameModel> oldGame = gameRepository.findById(id);
        if(oldGame.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Game not found");
        }

        gameRepository.delete(oldGame.get());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
