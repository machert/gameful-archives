package felipe.machert.gamefularchives.util;

import felipe.machert.gamefularchives.model.GameModel;

public class GameCreator {


    public static GameModel createGameToBeSaved(){
        return GameModel.builder().
                name("Dark souls 1").
                platform("PC").
                year_finished(2012).
                build();
    }


    public static GameModel createValidGame(){
        return GameModel.builder().
                name("Dark souls 1").
                platform("PC").
                year_finished(2012).
                id(1L).
                build();
    }


    public static GameModel createValidUpdatedGame(){
        return GameModel.builder().
                name("Dark souls 2").
                platform("PC").
                year_finished(2012).
                id(1L).
                build();
    }
}
