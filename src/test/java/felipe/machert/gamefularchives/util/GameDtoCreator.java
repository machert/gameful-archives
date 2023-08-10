package felipe.machert.gamefularchives.util;

import felipe.machert.gamefularchives.dto.GameDto;

public class GameDtoCreator {
    public static GameDto createGamePostRequestBody(){
        GameDto gameDto = new GameDto();
        gameDto.setName(GameCreator.createGameToBeSaved().getName());
        gameDto.setPlatform(GameCreator.createGameToBeSaved().getPlatform());
//        gameDto.setYear_finished(GameCreator.createGameToBeSaved().getYear_finished());

        return gameDto;
    }

}
