package projekt.game;

import projekt.game.components.GameInstance;
import projekt.game.components.ships.players.PlayerType;
import projekt.game.levels.Level;
import projekt.game.levels.LevelEasy;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class GamePanel extends JPanel {
    int noPlayers;
    List<GameInstance> gameInstances = new ArrayList<>();
    public GamePanel(int noPlayers) {
        this.noPlayers = noPlayers;
    }

    public void initInstance(PlayerType pType, Level level, String playerName){
        GameInstance newGameInstance = new GameInstance(level, pType, playerName, gameInstances.size() == 0);
        add(newGameInstance);
        gameInstances.add(newGameInstance);
        this.addKeyListener(newGameInstance);
    }

    public void startGame(){
        gameInstances.forEach(GameInstance::start);
    }


}
