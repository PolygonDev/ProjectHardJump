package de.polygondev.hjp.objects.ui;

import de.cg.cgge.game.Room;
import de.cg.cgge.io.Sound;
import de.polygondev.hjp.enums.UILayout;

import java.awt.*;

public class UIButton extends UIObject {
    
    private String text;
    private Color textcolor;
    private Color textHighlightedColor;
    
    private Sound sound;
    private Font font;
    
    /**
     * Generates button with custom arguments
     * @param room sets the Room in which this element will be drawn
     * @param x sets the x value where it should be in the room
     * @param y sets the y value where it should be in the room
     * @param text sets the button text that is displayed
     * @param flow sets the flow Layout of the button
     * @param font sets the font of the button
     * @param textcolor sets the color of the button
     * @param textHighlightedColor sets the highlighted color of the button
     */
    public UIButton(Room room, float x, float y, String text, UILayout flow, Font font, Color textcolor, Color textHighlightedColor) {
        super(room, x, y, 0, 0);
        
        initButton(room,text, flow, font, textcolor, textHighlightedColor);
    }
    
    /**
     * Generates button with custom arguments
     * @param room sets the Room in which this element will be drawn
     * @param x sets the x value where it should be in the room
     * @param y sets the y value where it should be in the room
     * @param text sets the button text that is displayed
     * @param flow sets the flow Layout of the button
     * @param textcolor sets the color of the button
     * @param textHighlightedColor sets the highlighted color of the button
     */
    public UIButton(Room room, float x, float y, String text, UILayout flow, Color textcolor, Color textHighlightedColor) {
        super(room, x, y, 0, 0);
        
        initButton(room,text,flow,null,textcolor,textHighlightedColor);
    }
    
    private void initButton(Room room, String text, UILayout flow, Font font, Color textcolor, Color textHighlightedColor) {
        
        this.setOriginLayout(flow);
        
        this.font = font;
        this.text = text;
        this.textcolor = textcolor;
        this.textHighlightedColor = textHighlightedColor;
    }
    
    /**
     * Gets the text of the button
     * @return String
     */
    public String getText() {
        return text;
    }
    
    /**
     * sets the text of the button (can be changed in runtime)
     * @param text String
     */
    public void setText(String text) {
        this.text = text;
    }
    
    /**
     * gets the font of the button
     * @return {@link Font}
     */
    public Font getFont() {
        return font;
    }
    
    /**
     * sets the fot of the button (works in runtime)
     * @param font {@link Font}
     */
    public void setFont(Font font) {
        this.font = font;
    }
    
    /**
     * gets the text color of the button
     * @return {@link Color}
     */
    public Color getTextcolor() {
        return textcolor;
    }
    
    /**
     * sets the text color of the button (works in runtime)
     * @param textcolor {@link Color}
     */
    public void setTextcolor(Color textcolor) {
        this.textcolor = textcolor;
    }
    
    /**
     * gets the highlighted textcolor of the button
     * @return {@link Color}
     */
    public Color getTextHighlightedColor() {
        return textHighlightedColor;
    }
    
    /**
     * sets the highlighted textcolor oft he button (works in runtime)
     * @param textHighlightedColor {@link Color}
     */
    public void setTextHighlightedColor(Color textHighlightedColor) {
        this.textHighlightedColor = textHighlightedColor;
    }
    
    /**
     * this is the draw function of the button (please DO NOT TOUCH!!)
     * @param g {@link Graphics}
     */
    @Override
    public void draw(Graphics g) {
        super.draw(g);
        
        g.setFont(font);
        
        //To center the button
        var bounds = g.getFontMetrics().getStringBounds(text, g).getBounds();
    
        setWidth((int) bounds.getWidth());
        setHeight((int) bounds.getHeight());
        
        if (isMouseHover()) {
            
            g.setColor(textHighlightedColor);
            
        } else {
            
            g.setColor(textcolor);
            
        }
        
        //Raising the button on mouse hovering
        int dy = (isMouseHover() ? (int) y - 3 : (int) y);
        
        g.drawString(text, (int) x, (int) (dy + bounds.getHeight()));
        g.setFont(null);
    }
    
}
