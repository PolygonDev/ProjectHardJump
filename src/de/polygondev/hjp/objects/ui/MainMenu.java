package de.polygondev.hjp.objects.ui;

import de.cg.cgge.game.GameObject;
import de.cg.cgge.game.Room;
import de.cg.cgge.io.MouseHelper;
import de.polygondev.hjp.ctrl.Resources;
import de.polygondev.hjp.ctrl.RoomBuilder;

import java.awt.*;
import java.awt.event.MouseEvent;

public class MainMenu extends GameObject {

    private int selectedButton = 0; //Useful for keyboard support and for later controller support; Also looks nicer
    
    private ui_Button but1;
    private ui_Button but2;
    private ui_Button but3;
    
    public MainMenu(Room room) {
        super(room);

        //Creating the buttons
        for (int i = 0; i<3; i++) {
            String text = "";
            //Gathering the text of the buttons
            switch (i) {
                case 0:
                    text = Resources.string_menu_main_play;
                    but1 = new ui_Button(room, 250 + (100 + 20) * i, 250, 200, 200, text, this);
                    break;
                case 1:
                    text = Resources.string_menu_main_settings;
                    but2 = new ui_Button(room, 250 + (100 + 20) * i, 350, 200, 200, text, this);
                    break;
                case 2:
                    text = Resources.string_menu_main_quit;
                    but3 = new ui_Button(room, 250 + (100 + 20) * i, 450, 200, 200, text, this);
                    break;
            }
            
            
            //new ui_Button(room, 250 + (100 + 20) * i,text, i, this);

        }
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        super.mouseClicked(e);
        
        if (but1.isButtonClicked(e)) {
            System.out.println("Play wurde gedrückt");
            
            //Not the final implementation
            Room destination = new RoomBuilder(room.getGameInstance()).initFromFile(0).build();
            room.getGameInstance().getDrawer().changeRoomSafely(destination);
        }
        if (but2.isButtonClicked(e)) {
            System.out.println("Optionen wurde gedrückt");
        }
        if (but3.isButtonClicked(e)) {
            System.out.println("Exit wurde gedrückt");
        }
    }
    
    public void ui_buttonEvent(MouseEvent e) {
        System.out.println("Hier passiert ja etwas wuuhuu");
    }
    
    @Override
    public void draw(Graphics g) {

        /*
            BACKGROUND
         */
        g.setColor(Resources.color_menu_main_background);
        g.fillRect(0,0,room.getGameInstance().getWidth(), room.getGameInstance().getHeight());

        /*
            TITLE
         */
        g.setColor(Resources.color_menu_main_title);
        g.setFont(Resources.font_menu_main_title);

        //To center the title
        var titleBounds = g.getFontMetrics().getStringBounds(Resources.string_menu_main_title, g).getBounds();
        int titleX = (int) (room.getGameInstance().getWidth()/2-(titleBounds.getWidth()/2));
        int titleY = 200;
    
        g.drawString(Resources.string_menu_main_title, titleX, 150);
        
        but1.buttonDraw(g);
        but2.buttonDraw(g);
        but3.buttonDraw(g);

        
    }

}
