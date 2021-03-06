package de.polygondev.hjp.objects.ui;

import de.cg.cgge.game.GameObject;
import de.cg.cgge.game.Room;
import de.polygondev.hjp.ctrl.Resources;
import de.polygondev.hjp.ctrl.RoomBuilder;
import de.polygondev.hjp.enums.RoomType;
import de.polygondev.hjp.enums.UILayout;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.Iterator;

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
        
        UILabel title = new UILabel(room, UILayout.CENTERED, room.getGameInstance().getWidth() / 2f, 150, Resources.string_menu_main_title,
                                    Resources.font_menu_main_title, Resources.color_menu_main_title);
        
        try {
            for (Iterator<GameObject> it = room.getObjectManager().getObjects().iterator(); it.hasNext(); ){
                System.out.println("ele1 " + it.next());
            }
        } catch (Exception ignored) {}
        
        //Creating the buttons
        for (int i = 0; i<3; i++) {
            String text = "";
            //Gathering the text of the buttons
    
            try {
                switch (i) {
                    case 0:
                        text = Resources.string_menu_main_play;
                        but1 = new UIButton(room,
                                            room.getGameInstance().getWidth() / 2f,
                                            260,
                                            text,
                                            UILayout.CENTERED,
                                            Resources.font_menu_main_buttons,
                                            Resources.color_menu_main_buttons_unhighlighted,
                                            Resources.color_menu_main_buttons_highlighted);
                        break;
                    case 1:
                        text = Resources.string_menu_main_settings;
                        but2 = new UIButton(room,
                                            room.getGameInstance().getWidth() / 2f,
                                            390,
                                            text,
                                            UILayout.CENTERED,
                                            Resources.font_menu_main_buttons,
                                            Resources.color_menu_main_buttons_unhighlighted,
                                            Resources.color_menu_main_buttons_highlighted);
                        break;
                    case 2:
                        text = Resources.string_menu_main_quit;
                        but3 = new UIButton(room,
                                            room.getGameInstance().getWidth() / 2f,
                                            510,
                                            text,
                                            UILayout.CENTERED,
                                            Resources.font_menu_main_buttons,
                                            Resources.color_menu_main_buttons_unhighlighted,
                                            Resources.color_menu_main_buttons_highlighted);
                        break;
                }
            }
            catch (Exception ignored) {}
        }
    }
    
    /**
     * hier werden buttonklicks registriert
     * @param e
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        super.mouseClicked(e);
        
        if (but1.isMouseHover()) {
            
            //Not the final implementation
            Room destination = new RoomBuilder(room.getGameInstance()).initFromFile(RoomType.GAME).build();
            room.getGameInstance().getDrawer().changeRoomSafely(destination);
        }
        if (but2.isMouseHover()) {
            
            but1.setOriginLayout(UILayout.RIGHT);
            but2.setOriginLayout(UILayout.RIGHT);
            but3.setOriginLayout(UILayout.RIGHT);
        }
        if (but3.isMouseHover()) {
            
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
