package de.polygondev.hjp.objects.ui;

import de.cg.cgge.game.GameObject;
import de.cg.cgge.game.Room;

import java.awt.*;

public class UILabel extends GameObject {
    
    private String text;
    private GameObject owner;
    
    private float x = 0;
    private float y = 0;
    private float xback = 0;
    
    private LAYOUT flow = LAYOUT.RIGHT;
    private boolean isFlowSet = false;
    private Color textcolor;
    
    public enum LAYOUT { RIGHT, CENTERED, LEFT }
    
    private Font font;
    
    public UILabel(Room room, float x, float y, String text, LAYOUT flow, Font font, Color textcolor) {
        super(room);
        initButton(room, x, y, text, flow, font, textcolor);
    }
    
    public UILabel(Room room, float x, float y, String text, LAYOUT flow, Color textcolor) {
        super(room);
        initButton(room, x, y, text, flow, null, textcolor);
    }
    
    private void initButton(Room room, float x, float y, String text, LAYOUT flow, Font font, Color textcolor) {
    
        this.x = x;
        this.y = y;
        this.xback = x;
    
        this.solid = false;
        
        this.font = font;
        this.text = text;
        this.textcolor = textcolor;
        
        this.flow = flow;
    }
    
    public String getText() {
        return text;
    }
    
    public void setText(String text) {
        this.text = text;
        this.isFlowSet = false;
    }
    
    @Override
    public float getX() {
        return x;
    }
    
    @Override
    public void setX(float x) {
        this.x = x;
        this.xback = x;
    }
    
    @Override
    public float getY() {
        return y;
    }
    
    @Override
    public void setY(float y) {
        this.y = y;
    }
    
    public LAYOUT getFlow() {
        return flow;
    }
    
    public void setFlow(LAYOUT flow) {
        this.flow = flow;
        this.isFlowSet = false;
    }
    
    public Font getFont() {
        return font;
    }
    
    public void setFont(Font font) {
        this.font = font;
        isFlowSet = false;
    }
    
    @Override
    public void draw(Graphics g) {
        g.setFont(font);
        
        //To center the button
        var bounds = g.getFontMetrics().getStringBounds(text, g).getBounds();
    
        float width  = (float) bounds.getWidth();
        float height = (float) bounds.getHeight();
        
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
        
        g.setColor(textcolor);
        g.drawString(text, (int) x, (int) y);
        g.setFont(null);
    }
    
}
