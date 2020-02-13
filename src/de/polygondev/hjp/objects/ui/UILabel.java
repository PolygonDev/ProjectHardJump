package de.polygondev.hjp.objects.ui;

import de.cg.cgge.game.Room;

import java.awt.*;
import java.lang.reflect.Method;

public class UILabel extends UIObject {
    
    private String text;
    private Color textcolor;
    
    private Font font;
    
    public UILabel(Room room, UILayout flow, float x, float y, String text, Font font, Color textcolor) {
        super(room,flow,  x, y, 0, 0);
        initButton(text,font,textcolor);
    }
    
    public UILabel(Room room, UILayout flow, float x, float y, String text, Color textcolor) {
        super(room, flow,  x, y, 0, 0);
        initButton(text,null,textcolor);
    }
    
    public UILabel(Room room, float x, float y, String text, Color textcolor) {
        super(room, null, x, y, 0, 0);
        initButton(text, null, textcolor);
    }
    
    private void initButton(String text, Font font, Color textcolor) {
        this.font = font;
        this.text = text;
        this.textcolor = textcolor;
    }
    
    public String getText() {
        return text;
    }
    
    public void setText(String text) {
        this.text = text;
    }
    
    public Font getFont() {
        return font;
    }
    
    public void setFont(Font font) {
        this.font = font;
    }
    
    @Override
    public void draw(Graphics g) {
        super.draw(g);
        g.setFont(font);
        
        //To get automatic height and width from the text length
        var bounds = g.getFontMetrics().getStringBounds(text, g).getBounds();
    
        setWidth((int) bounds.getWidth());
        setHeight((int) bounds.getHeight());
        
        g.setColor(textcolor);
        g.drawString(text, (int) x, (int) y);
        
        //To not accidently change fonts of other texts in the game
        g.setFont(null);
    }
    
}
