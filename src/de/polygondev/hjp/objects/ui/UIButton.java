package de.polygondev.hjp.objects.ui;

import de.cg.cgge.game.GameObject;
import de.cg.cgge.game.Room;
import de.cg.cgge.io.MouseHelper;
import de.cg.cgge.io.Sound;

import java.awt.*;
import java.awt.event.MouseEvent;

public class UIButton extends GameObject{
    
    private MouseHelper mouseHelper;
    
    
    private String text;
    private GameObject owner;
    
    private float x = 0;
    private float y = 0;
    private float xback = 0;
    
    private float width = 0;
    private float height = 0;
    
    private LAYOUT flow = LAYOUT.RIGHT;
    private boolean isFlowSet = false;
    private Color textcolor;
    private Color textHighlightedColor;
    
    private Sound sound;
    
    /**
     * sets the flow values to a enum string
     */
    public enum LAYOUT { RIGHT, CENTERED, LEFT }
    
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
    public UIButton(Room room, float x, float y, String text, LAYOUT flow, Font font, Color textcolor, Color textHighlightedColor) {
        super(room);
        //super(room);
        initButton(room, x, y, text, flow, font, textcolor, textHighlightedColor);
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
    public UIButton(Room room, float x, float y, String text, LAYOUT flow, Color textcolor, Color textHighlightedColor) {
        super(room);
        //super(room);
        initButton(room, x, y, text, flow, null, textcolor, textHighlightedColor);
    }
    
    private void initButton(Room room, float x, float y, String text, LAYOUT flow, Font font, Color textcolor, Color textHighlightedColor) {
    
        this.x = x;
        this.y = y;
        this.xback = x;
        
        this.solid = false;
        
        this.font = font;
        this.text = text;
        this.textcolor = textcolor;
        this.textHighlightedColor = textHighlightedColor;
        
        this.flow = flow;
    
        this.mouseHelper = new MouseHelper(room.getGameInstance());
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
        this.isFlowSet = false;
    }
    
    /**
     * gets the x value of the button
     * @return float x
     */
    @Override
    public float getX() {
        return x;
    }
    
    /**
     * sets the x value of the button (works in runtime)
     * @param x float
     */
    @Override
    public void setX(float x) {
        this.x = x;
        this.xback = x;
    }
    
    /**
     * gets the y value of the button
     * @return float y
     */
    @Override
    public float getY() {
        return y;
    }
    
    /**
     * sets the y value of the button (works in runtime)
     * @param y float
     */
    @Override
    public void setY(float y) {
        this.y = y;
    }
    
    /**
     * gets the layout of the button
     * @return {@link LAYOUT}
     */
    public LAYOUT getFlow() {
        return flow;
    }
    
    /**
     * sets the value of the button (works in runtime)
     * @param flow {@link LAYOUT}
     */
    public void setFlow(LAYOUT flow) {
        this.flow = flow;
        this.isFlowSet = false;
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
        g.setFont(font);
        
        //To center the button
        var bounds = g.getFontMetrics().getStringBounds(text, g).getBounds();
        
        width = (float) bounds.getWidth();
        height = (float) bounds.getHeight();
        
        if (!isFlowSet) {
            switch (flow) {
                case RIGHT:
                    x = xback;
                    isFlowSet = true;
                    break;
                case CENTERED:
                    x = xback - width / 2;
                    isFlowSet = true;
                    break;
                case LEFT:
                    x = xback - width;
                    isFlowSet = true;
                    break;
            }
        }
        
        if (inMouseRange()) {
            
            g.setColor(textHighlightedColor);
            
        } else {
            
            g.setColor(textcolor);
            
        }
        
        //Raising the button on mouse hovering
        int dy = (inMouseRange() ? (int) y - 3 : (int) y);
        
        g.drawString(text, (int) x, (int) (dy + bounds.getHeight()));
        g.setFont(null);
    }
    
    private boolean inMouseRange() {
        
        int mx = mouseHelper.getMouseX();
        int my = mouseHelper.getMouseY();
        
        return (mx > x && mx < x + width && my > y && my < y + height);
    }
    
    /**
     * tests if the button is clicked and returns a boolean if it is
     * @param e {@link MouseEvent}
     * @return {@link Boolean}
     */
    public boolean isButtonClicked(MouseEvent e) {
        
        //Hier kommt das mouse event vom main menü oder jedem anderen gameobject
        if (inMouseRange()) {
            return true;
        } else {
            return false;
        }
    }
    
}
