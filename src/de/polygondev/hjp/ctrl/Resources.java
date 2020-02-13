package de.polygondev.hjp.ctrl;

import de.cg.cgge.files.FileContents;
import de.cg.cgge.files.GameFile;
import de.cg.cgge.gui.AnimatedSprite;
import de.cg.cgge.gui.Sprite;

import java.awt.*;
import java.io.IOException;

public class Resources {

    private static FileContents fcStrings = getContents("res//game//lang//en.data");
    private static FileContents fcColors = getContents("res//game//colors.data");
    private static FileContents fcStyles = getContents("res//game//styles.data");

    public static final String string_menu_main_play = fcStrings.getFromKeyword("menu_main_play");
    public static final String string_menu_main_settings = fcStrings.getFromKeyword("menu_main_settings");
    public static final String string_menu_main_quit = fcStrings.getFromKeyword("menu_main_quit");
    public static final String string_menu_main_title = fcStrings.getFromKeyword("menu_main_title");

    public static final Color color_menu_main_buttons_unhighlighted = Color.decode(fcColors.getFromKeyword("menu_main_buttons_unhighlighted"));
    public static final Color color_menu_main_buttons_highlighted = Color.decode(fcColors.getFromKeyword("menu_main_buttons_highlighted"));
    public static final Color color_menu_main_background = Color.decode(fcColors.getFromKeyword("menu_main_background"));
    public static final Color color_menu_main_title = Color.decode(fcColors.getFromKeyword("menu_main_title"));
    public static final Color color_game_background = Color.decode(fcColors.getFromKeyword("game_background"));

    public static final Font font_menu_main_buttons = new Font(fcStyles.getFromKeyword("font_menu_main_buttons_name"), Font.PLAIN, Integer.parseInt(fcStyles.getFromKeyword("font_menu_main_buttons_size")));
    public static final Font font_menu_main_title = new Font(fcStyles.getFromKeyword("font_menu_main_title_name"), Font.PLAIN, Integer.parseInt(fcStyles.getFromKeyword("font_menu_main_title_size")));

    public static final AnimatedSprite as_player = new AnimatedSprite(64, 64, 0,
             "res//game//sprites//player//idle.png",
                    "res//game//sprites//player//walk1.png",
                    "res//game//sprites//player//walk2.png");

    public static final Sprite sprite_jetpack = new Sprite("res//game//sprites//collectables//jetpack.png", 64, 64, 0);


    private static FileContents getContents(String path) {

        try {
            GameFile gf = new GameFile(path);
            gf.loadToMemory();
            return gf.getContents();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
    
    public static void setFcStrings(String fcStringsDir) {
        //TODO
    }
    
    public static FileContents getFcStrings() {
        return fcStrings;
    }
    
    public static FileContents getFcColors() {
        return fcColors;
    }
    
    public static FileContents getFcStyles() {
        return fcStyles;
    }

}
