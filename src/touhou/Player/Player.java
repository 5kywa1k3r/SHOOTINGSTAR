package touhou.Player;


import com.sun.xml.internal.bind.v2.runtime.reflect.opt.Const;
import jdk.internal.util.xml.impl.Input;
import tklibs.SpriteUtils;
import touhou.Bases.Constraints;
import touhou.Bases.Vector2D;
import touhou.Inputs.InputManager;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.beans.IndexedPropertyChangeEvent;
import java.util.ArrayList;

public class Player {
    private static final int SPEED = 10;
    private static final long LIMITBULLET = 140;
    public Vector2D position;
    public BufferedImage image;
    public BufferedImage image2, image3, image4;
    public InputManager inputManager;
    public Constraints constraints;
    public ArrayList<PlayerSpell> playerSpells;
    public int charge_up = 0;
    private int dx, dy;
    private int start_charge = 50, nor_charge = 1000, ultimate = 3000;
    private long currentTime = 0, previousTime = 0, currentTime2 = 0, previousTime2 = 0, currentTime3 = 0, previousTime3 = 0;
    private int bulletShooted = 150;

    public Player(){
        position = new Vector2D(384/2, 600);
        image = SpriteUtils.loadImage("assets/images/players/straight/4.png");
        image3 = SpriteUtils.loadImage("assets/images/players/straight/4_ver2.png");
        image4 = SpriteUtils.loadImage("assets/images/player-bullets/a/128128purplebullet_aura.png");
    }
    //ham tao - constructor

    public void run(){
        if (inputManager.upPressed) {
            position.addUp(0,-SPEED);
        }
        if (inputManager.downPressed) {
            position.addUp(0,SPEED);
        }
        if (inputManager.leftPressed) {
            position.addUp(-SPEED,0);
        }
        if (inputManager.rightPressed) {
            position.addUp(SPEED,0);
        }

        if (constraints != null){
            constraints.make(position);
        }

        castSpell();
    }

    private void castSpell() {
        if (inputManager.vPressed) {
            charge_up += 10;
            if (charge_up > ultimate) charge_up = ultimate;
            switch (charge_up) {
                case 50:
                    dx = -16; dy = -10;
                    image2 = SpriteUtils.loadImage("assets/images/player-bullets/a/6464rsz_steamworkshop_webupload_previewfile_412402931_preview.png");
                    break;
                case 1000:
                    dx = -83; dy = -76;
                    image2 = SpriteUtils.loadImage("assets/images/player-bullets/a/200200rsz_steamworkshop_webupload_previewfile.png");
                    break;
                case 3000:
                    dx = -144; dy= -126;
                    image2 = SpriteUtils.loadImage("assets/images/player-bullets/a/320320aura_ulti.png");
                    break;
            }
        }
        else {
            if (charge_up != 0){
                if (charge_up >= nor_charge && charge_up < ultimate){
                    PlayerSpell newSpell = new PlayerSpell(this.position,"assets/images/player-bullets/a/300_300rsz_11maginvshockspellart.png",0,-136,-255,false);
                    playerSpells.add(newSpell);
                }
                else
                    if (charge_up == ultimate){
                        PlayerSpell newSpell = new PlayerSpell(this.position,"assets/images/player-bullets/a/600_600_rsz_21maginvshockspellart.png",0,-286,-405,false);
                        playerSpells.add(newSpell);
                    }
                charge_up = 0;
            }
            else
                if (inputManager.xPressed) {
                    currentTime = System.currentTimeMillis();
                    //System.out.println("current",currentTime);
                    if (currentTime - previousTime > LIMITBULLET) {
                        PlayerSpell newSpell = new PlayerSpell(this.position, "assets/images/player-bullets/a/1.png", 0, 12, 0,(playerSpells.size() % bulletShooted > 100));
                        PlayerSpell newSpell2 = new PlayerSpell(this.position, "assets/images/player-bullets/a/1.png", 0, -6, 0, (playerSpells.size() % bulletShooted > 100));
                        playerSpells.add(newSpell);
                        playerSpells.add(newSpell2);
                        previousTime = currentTime;
                    }
                }
            /*    if (inputManager.cPressed) {
                    currentTime2 = System.currentTimeMillis();
                    System.out.println(currentTime - previousTime);
                    if (currentTime2 - previousTime2 > LIMITBULLET) {
                        PlayerSpell newSpell = new PlayerSpell(this.position, "assets/images/player-bullets/a/3.png", 1, 0, 3);
                        playerSpells.add(newSpell);
                        previousTime2 = currentTime2;
                    }
                }
                if (inputManager.zPressed) {
                    currentTime3 = System.currentTimeMillis();
                    System.out.println(currentTime);
                    if (currentTime3 - previousTime3 > LIMITBULLET) {
                        PlayerSpell newSpell = new PlayerSpell(this.position, "assets/images/player-bullets/a/3.png", -1, 6, 3);
                        playerSpells.add(newSpell);
                        previousTime3 = currentTime3;
                    }
                }*/
            image2 = null;
        }
    }

    public void render(Graphics2D g2d){
        g2d.drawImage(image2, (int) position.x + dx, (int) position.y + dy, null);

        if (playerSpells.size() % bulletShooted > 100){
            g2d.drawImage(image4, (int) position.x - 48, (int) position.y - 44, null);
            g2d.drawImage(image3, (int) position.x, (int) position.y, null);
        }
        else
            g2d.drawImage(image,(int) position.x, (int) position.y, null);
    }

}
//-48 -44 : 128 128
//-16 -10 : 64 64
//-83 -76 : 200 200