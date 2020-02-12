package de.polygondev.hjp.objects.ui;

import de.cg.cgge.game.GameObject;
import de.cg.cgge.game.Room;
import de.polygondev.hjp.ctrl.Resources;
import de.polygondev.hjp.ctrl.RoomBuilder;
import de.polygondev.hjp.enums.RoomType;

import java.awt.*;
import java.awt.event.MouseEvent;

public class MainMenu extends GameObject {

    private int selectedButton = 0; //Useful for keyboard support and for later controller support; Also looks nicer
    
    private UIButton but1;
    private UIButton but2;
    private UIButton but3;
    
    /**
     * Hier werden die ui elemente initialisiert
     * @param room
     */
    public MainMenu(Room room) {
        super(room);
        
        UILabel title = new UILabel(room, room.getGameInstance().getWidth() / 2f, 150, Resources.string_menu_main_title, UILabel.LAYOUT.CENTERED, Resources.font_menu_main_title
                , Resources.color_menu_main_title);
        
        //Creating the buttons
        for (int i = 0; i<3; i++) {
            String text = "";
            //Gathering the text of the buttons
            switch (i) {
                case 0:
                    text = Resources.string_menu_main_play;
                    but1 = new UIButton(room,
                                         room.getGameInstance().getWidth()/2f,
                                        260,
                                        text,
                                        UIButton.LAYOUT.CENTERED,
                                        Resources.font_menu_main_buttons,
                                        Resources.color_menu_main_buttons_unhighlighted,
                                        Resources.color_menu_main_buttons_highlighted);
                    break;
                case 1:
                    text = Resources.string_menu_main_settings;
                    but2 = new UIButton(room,
                                         room.getGameInstance().getWidth()/2f,
                                        390,
                                        text,
                                        UIButton.LAYOUT.CENTERED,
                                        Resources.font_menu_main_buttons,
                                        Resources.color_menu_main_buttons_unhighlighted,
                                        Resources.color_menu_main_buttons_highlighted);
                    break;
                case 2:
                    text = Resources.string_menu_main_quit;
                    but3 = new UIButton(room,
                                         room.getGameInstance().getWidth()/2f,
                                        510,
                                        text,
                                        UIButton.LAYOUT.CENTERED,
                                        Resources.font_menu_main_buttons,
                                        Resources.color_menu_main_buttons_unhighlighted,
                                        Resources.color_menu_main_buttons_highlighted);
                    break;
            }
        }
        
    }
    
    /**
     * hier werden buttonklicks registriert
     * @param e
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        super.mouseClicked(e);
        
        if (but1.isButtonClicked(e)) {
            
            //Not the final implementation
            Room destination = new RoomBuilder(room.getGameInstance()).initFromFile(RoomType.GAME).build();
            room.getGameInstance().getDrawer().changeRoomSafely(destination);
        }
        if (but2.isButtonClicked(e)) {
            
            but1.setFlow(UIButton.LAYOUT.RIGHT);
            but2.setFlow(UIButton.LAYOUT.RIGHT);
            but3.setFlow(UIButton.LAYOUT.RIGHT);
        }
        if (but3.isButtonClicked(e)) {
    
            Runtime.getRuntime().exit(0);
            
        }
    }
    
    /**
     * hier wird alles gezeichnet. Wobei jedes ui element seinen eigenen zeichner hat
     * @param g
     */
    @Override
    public void draw(Graphics g) {

        /*
            BACKGROUND
         */
        g.setColor(Resources.color_menu_main_background);
        g.fillRect(0,0,room.getGameInstance().getWidth(), room.getGameInstance().getHeight());
    }

}
