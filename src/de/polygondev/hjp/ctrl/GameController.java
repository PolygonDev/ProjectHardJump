package de.polygondev.hjp.ctrl;

import de.cg.cgge.game.GameInstance;
import de.cg.cgge.game.Room;

import java.awt.*;

public class GameController {

    private GameInstance game;

    public GameController() {

        game = new GameInstance();
        
        Dimension toolkit = Toolkit.getDefaultToolkit().getScreenSize();
        game.getDrawer().getWindow().setBounds(
                (int) toolkit.getWidth()/2 -game.getWidth()/2,
                (int) toolkit.getHeight()/2 -game.getHeight()/2,
                (int) game.getWidth(),
                (int) game.getHeight());
        
        Room testRoom = new RoomBuilder(game).initFromFile(0).build();
        game.getDrawer().changeRoomSafely(testRoom);
    }

}
