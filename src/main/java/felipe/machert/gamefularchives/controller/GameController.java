package felipe.machert.gamefularchives.controller;

import felipe.machert.gamefularchives.dto.GameDto;
import felipe.machert.gamefularchives.model.GameModel;
import felipe.machert.gamefularchives.service.GameService;
import jakarta.validation.Valid;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class GameController {

    private final GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }


    @GetMapping("/games")
    public ResponseEntity<List<GameModel>> getAllGames(){
        return ResponseEntity.status(HttpStatus.OK).body(gameService.findAll());
    }

    @GetMapping("/games/pages")
    public ResponseEntity<Page<GameModel>> getAllGamesPages(@ParameterObject Pageable pageable){
        return ResponseEntity.status(HttpStatus.OK).body(gameService.findAll(pageable));
    }

    @GetMapping("/games/{id}")
    public ResponseEntity<GameModel> getById(@PathVariable long id){
        return ResponseEntity.status(HttpStatus.OK).body(gameService.findByIdOrThrowBadRequestException(id));
    }

//    @GetMapping("/games/errorException")
//    public void errorException(){
//        throw new InternalServerErrorException("This is an example of an internal server error.");
//        throw new BadRequestException("This is an example of a bad request exception.");
//    }

    @PostMapping("/games")
    public ResponseEntity<GameModel> saveGame(@RequestBody @Valid GameDto gameDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(gameService.save(gameDto));
    }

    @PutMapping("/games/{id}")
    public ResponseEntity<Void> updateGame(@PathVariable long id, @RequestBody @Valid GameDto gameDto){
        gameService.update(id, gameDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/games/{id}")
    public ResponseEntity<Void> deleteGame(@PathVariable long id){
        gameService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }




}
