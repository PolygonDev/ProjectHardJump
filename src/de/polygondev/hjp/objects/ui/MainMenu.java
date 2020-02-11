package de.polygondev.hjp.objects.ui;

import de.cg.cgge.game.GameObject;
import de.cg.cgge.game.Room;
import de.cg.cgge.io.MouseHelper;
import de.polygondev.hjp.ctrl.Resources;

import java.awt.*;
import java.awt.event.MouseEvent;

public class MainMenu extends GameObject {

    private int selectedButton = 0; //Useful for keyboard support and for later controller support; Also looks nicer

    public MainMenu(Room room) {
        super(room);

        //Creating the buttons
        for (int i = 0; i<3; i++) {
            String text = "";
            //Gathering the text of the buttons
            switch (i) {
                case 0:
                    text = Resources.string_menu_main_play;
                    break;
                case 1:
                    text = Resources.string_menu_main_settings;
                    break;
                case 2:
                    text = Resources.string_menu_main_quit;
                    break;
            }

            new Button(room, 250 + (100 + 20) * i, text, i, this);

        }
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

        g.drawString(Resources.string_menu_main_title, titleX, 200);
    }

    /**
     *          CLASS FOR THE BUTTON
     */
    private static class Button extends GameObject{

        private int id;

        private String text;

        private MainMenu owner;

        private MouseHelper mouseHelper;

        public Button(Room room, int y, String text, int id, MainMenu owner) {
            super(room);

            this.y = y;
            this.text = text;
            this.owner = owner;
            this.id = id;

            this.mouseHelper = new MouseHelper(room.getGameInstance());
        }

        @Override
        public void draw(Graphics g) {
            //To center the button
            var bounds = g.getFontMetrics().getStringBounds(text, g).getBounds();
            x = (int) (room.getGameInstance().getWidth()/2-(bounds.getWidth()/2));
            w = (int) bounds.getWidth();
            h = (int) bounds.getHeight();

            if (owner.selectedButton == id) g.setColor(Resources.color_menu_main_buttons_highlighted);
            else                            g.setColor(Resources.color_menu_main_buttons_unhighlighted);

            //Raising the button on mouse hovering
            int dy = (inMouseRange() ? (int)y-3 : (int)y);

            g.drawString(text, (int) x, (int) (dy+bounds.getHeight()));

        }

        @Override
        public void step() {

            if (inMouseRange()) {
                owner.selectedButton = id;
            }
        }

        private boolean inMouseRange() {

            int mx = mouseHelper.getMouseX();
            int my = mouseHelper.getMouseY();

            return (mx > x && mx < x+w && my > y && my < y+h);
        }

        @Override
        public void mouseClicked(MouseEvent e) {

            if (inMouseRange()) {
                switch (id) {
                    case 0:
                        break;
                    case 1:
                        break;
                    case 2:
                        break;
                }
            }
        }
    }

}
