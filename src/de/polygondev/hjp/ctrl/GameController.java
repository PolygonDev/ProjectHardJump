package de.polygondev.hjp.ctrl;

import de.cg.cgge.game.GameInstance;
import de.cg.cgge.game.Room;

public class GameController {

    private GameInstance game;

    public GameController() {

        game = new GameInstance();

        Room testRoom = new RoomBuilder(game).initFromFile(0).build();
        game.getDrawer().changeRoomSafely(testRoom);
    }

}
