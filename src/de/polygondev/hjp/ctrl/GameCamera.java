package de.polygondev.hjp.ctrl;

import de.cg.cgge.game.Camera;
import de.cg.cgge.game.GameObject;
import de.cg.cgge.game.Room;
import de.polygondev.hjp.objects.ingame.Player;

public class GameCamera extends Camera {

    private Player toFollow;
    private Room room;

    public GameCamera(Player toFollow, Room room, int speed) {
        super(toFollow, room);

        this.toFollow = toFollow;
        this.room = room;

        this.setXpadding(300);
        this.setYpadding(0);
        this.setSpeed(speed);
    }

    private boolean followOnX = false;
    private boolean followOnY = false;

    private int targetX = 0;
    private int targetY = 0;

    @Override
    public void update() {

        float objX = toFollow.getX();
        float objY = toFollow.getY();

        if (objX-xpos < xpadding || objX-xpos > room.getGameInstance().getWidth()-xpadding) {
            followOnX = true;
            targetX = (int) objX - room.getGameInstance().getWidth()/2;

        }

        if (objY-ypos < ypadding-300 || objY-ypos > room.getGameInstance().getHeight()-ypadding) {
            followOnY = true;
            targetY = (int) objY - room.getGameInstance().getHeight()/2;
        }

        if (followOnX) {
            if (xpos-targetX < 0) {
                xpos+=camSpeed;
            } else {
                xpos-=camSpeed;
            }
            if (xpos > targetX-camSpeed && xpos < targetX+camSpeed) {
                followOnX = false;
            }
        }

        if (followOnY) {
            if (ypos-targetY < 0) {
                ypos+=camSpeed;
            } else {
                ypos-=camSpeed;
            }
            if (ypos > targetY-camSpeed && ypos < targetY+camSpeed) {
                followOnY = false;
            }
        }

    }
}
