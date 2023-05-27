import projekt.frames.*;
import projekt.game.components.ships.players.PlayerType;
import projekt.game.levels.LevelEasy;

import javax.swing.*;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
//        SwingUtilities.invokeLater(() -> new GameFrame(Arrays.asList(PlayerType.PIRATE), Arrays.asList(new LevelEasy()), Arrays.asList("Melzak")));
        SwingUtilities.invokeLater(MenuFrame::new);
    }
}