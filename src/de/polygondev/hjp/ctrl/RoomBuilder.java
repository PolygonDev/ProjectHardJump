package de.polygondev.hjp.ctrl;

import de.cg.cgge.files.FileContents;
import de.cg.cgge.files.GameFile;
import de.cg.cgge.game.GameInstance;
import de.cg.cgge.game.Room;
import de.polygondev.hjp.enums.RoomType;
import de.polygondev.hjp.objects.ingame.Ground;
import de.polygondev.hjp.objects.ingame.Player;
import de.polygondev.hjp.objects.ui.MainMenu;
import de.polygondev.hjp.objects.ui.UIButton;
import de.polygondev.hjp.objects.ui.UILabel;

import java.awt.*;
import java.io.IOException;

public class RoomBuilder {

    private Room room;

    public RoomBuilder(GameInstance game) {
        room = new Room(game);
    }

    public RoomBuilder initFromFile(RoomType roomType) {
        
        //Check if it's a menu
        if (roomType.name().equalsIgnoreCase("title")) {
            
            new MainMenu(room);
            return this;
        }
        
        
        
        try {
            GameFile gf = new GameFile("res//game//rooms//" + roomType.name().toLowerCase() + ".data");
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
                else if (sections[0].equalsIgnoreCase("uilabel")) {
                    
                    int x = Integer.parseInt(sections[1]);
                    int y = Integer.parseInt(sections[2]);
                    String         text      = Resources.getFcStrings().getFromKeyword(sections[3]);
                    UILabel.LAYOUT flow      = UILabel.LAYOUT.valueOf(sections[4]);
                    Color          textcolor = Color.decode(Resources.getFcColors().getFromKeyword(sections[5]));
                    
                    new UILabel(room, x, y, text, flow, textcolor);
                }
                else if (sections[0].equalsIgnoreCase(("uibutton"))) {
                    
                    int x = Integer.parseInt(sections[1]);
                    int y = Integer.parseInt(sections[2]);
                    String text = Resources.getFcStrings().getFromKeyword(sections[3]);
                    UIButton.LAYOUT flow      = UIButton.LAYOUT.valueOf(sections[4]);
                    Color          textcolor = Color.decode(Resources.getFcColors().getFromKeyword(sections[5]));
                    Color          tchighi = Color.decode(Resources.getFcColors().getFromKeyword(sections[6]));
                    
                    new UIButton(room, x, y, text, flow, textcolor, tchighi);
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
