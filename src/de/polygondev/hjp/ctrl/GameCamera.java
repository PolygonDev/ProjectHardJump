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
        this.setYpadding(100);
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
            targetX = (int) objX + room.getGameInstance().getWidth()/2;
        }

        if (objY-ypos < ypadding || objY-ypos > room.getGameInstance().getHeight()-ypadding) {
            followOnY = true;
            targetY = (int) objY;
        }

        if (followOnX) {
            if (Math.abs(getX()-(targetX)) > camSpeed) {
                int dir = ((targetX - xpos >= 0) ? -1 : 1);

                xpos += camSpeed*dir;
            } else {
                followOnX = false;
            }
        }

        if (followOnY) {
            if (Math.abs(ypos-targetY+room.getGameInstance().getHeight()/2) > camSpeed*2) {
                followOnY = false;
            } else {
                int dir = ((targetY - ypos >= 0) ? -1 : 1);

                setY(ypos+camSpeed*dir);
            }
        }

    }
}
