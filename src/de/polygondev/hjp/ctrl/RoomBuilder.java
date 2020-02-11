package de.polygondev.hjp.ctrl;

import de.cg.cgge.files.FileContents;
import de.cg.cgge.files.GameFile;
import de.cg.cgge.game.GameInstance;
import de.cg.cgge.game.Room;
import de.polygondev.hjp.objects.ingame.Ground;
import de.polygondev.hjp.objects.ingame.Player;

import java.io.IOException;

public class RoomBuilder {

    private Room room;

    public RoomBuilder(GameInstance game) {
        room = new Room(game);
    }

    public RoomBuilder initFromFile(int id) {

        try {
            GameFile gf = new GameFile("rsc//game//rooms//room" + id + ".data");
            gf.loadToMemory();
            FileContents fc = gf.getContents();

            String[] lines = fc.get();

            for (String line : lines) {

                String[] sections = line.split(";");

                if (sections[0].equalsIgnoreCase("player")) {
                    int x = Integer.parseInt(sections[1]);
                    int y = Integer.parseInt(sections[2]);

                    new Player(room, x, y);
                }
                else if (sections[0].equalsIgnoreCase("ground")) {
                    int x = Integer.parseInt(sections[1]);
                    int y = Integer.parseInt(sections[2]);
                    int w = Integer.parseInt(sections[3]);
                    int h = Integer.parseInt(sections[4]);

                    new Ground(room, x, y, w, h);
                }

            }



        } catch (IOException e) {
            e.printStackTrace();
        }

        return this;
    }

    public Room build() {

        return this.room;
    }
}
