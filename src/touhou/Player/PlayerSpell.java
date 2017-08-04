package touhou.Player;

import tklibs.SpriteUtils;
import touhou.Bases.Constraints;
import touhou.Bases.Vector2D;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.lang.reflect.Array;
import java.util.Arrays;

public class PlayerSpell {
    private static final int SPEED = 20 ;


    public Vector2D position;
    public BufferedImage image, image2;
    public Constraints constraints = new Constraints(0,600,0,384);
    private float typebullet;
    public PlayerSpell(Vector2D playerposition, String str, int typebullet, int specx, int specy, boolean purpleBullet) {
        position = new Vector2D(playerposition.x + specx, playerposition.y + specy);
        //nor: +3 -15
        //small spec: 2 -10
        //nor spec: -136 -255
        //ultimate: -286 -405
        this.typebullet = typebullet;
        this.image = SpriteUtils.loadImage(str);
        if (purpleBullet){
            image2 = SpriteUtils.loadImage("assets/images/player-bullets/a/35_rsz_1lightning-effect-psd91884.png");
        }
        else image2 = null;
    }

    public void run(){
        position.y -= SPEED;
        if (typebullet == 1)
            position.x += 3;
        if (typebullet == -1)
            position.x -= 3;
    }

    public void render(Graphics2D g2d) {
        //if (!check())
        g2d.drawImage(image, (int) this.position.x, (int) this.position.y, null);
        g2d.drawImage(image2, (int) this.position.x - 6, (int) this.position.y - 3,null);
    }

    //public boolean check() {
      //  if (position.x > constraints.right || position.y < 0 || position.x < 0)
        //    return false;
       // else return false;
    //}
}
//- 137*2
//- 190*2