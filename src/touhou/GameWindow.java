package touhou;

import tklibs.SpriteUtils;
import touhou.Bases.Constraints;
import touhou.Inputs.InputManager;
import touhou.Player.Player;
import touhou.Player.PlayerSpell;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.lang.reflect.Constructor;
import java.util.ArrayList;

import static java.awt.event.KeyEvent.*;

/**
 * Created by huynq on 7/29/17.
 */
public class GameWindow extends Frame {

    private long lastTimeUpdate;
    private long currentTime;
    private Graphics2D windowGraphics;

    private Graphics2D backbufferGraphics;


    private BufferedImage background;
    private BufferedImage backbufferImage;

    Player player = new Player();
    ArrayList<PlayerSpell> playerSpells = new ArrayList<>();
    InputManager inputManager = new InputManager();
    private int backgroundy = - 2500;
    private int BACKGROUNDSPEED = 10;

    public GameWindow() {
        background = SpriteUtils.loadImage("assets/images/background/0.png");
        player.constraints = new Constraints(30,690,0,364);
        player.playerSpells = this.playerSpells;
        player.inputManager = this.inputManager;
        setupGameLoop();
        setupWindow();
    }

    private void setupGameLoop() {
        lastTimeUpdate = -1;
    }

    private void setupWindow() {
        this.setSize(384, 768);

        this.setTitle("Touhou - Remade by QHuyDTVT");
        this.setVisible(true);

        this.backbufferImage = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);
        this.backbufferGraphics = (Graphics2D) this.backbufferImage.getGraphics();

        this.windowGraphics = (Graphics2D) this.getGraphics();

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        this.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
               inputManager.keyPressed(e);
            }

            @Override
            public void keyReleased(KeyEvent e) {
                inputManager.keyReleased(e);
            }
        });
    }

    public void loop() {
        while(true) {
            if (lastTimeUpdate == -1) {
                lastTimeUpdate = System.currentTimeMillis();
            }
            currentTime = System.currentTimeMillis();
            if (currentTime - lastTimeUpdate > 17) {
                run();
                render();
                lastTimeUpdate = currentTime;
            }
        }
    }

    private void run() {
        if (backgroundy < 0) backgroundy += BACKGROUNDSPEED;
        player.run();
        for (PlayerSpell playerSpell : playerSpells){
            playerSpell.run();
        }
        //while ((playerSpells.size()>0) && (playerSpells.get(playerSpells.size() - 1).check()))
            //playerSpells.remove(playerSpells.size() - 1);
    }

    private void render() {
        backbufferGraphics.setColor(Color.black);
        backbufferGraphics.fillRect(0, 0, 384, 768);
        backbufferGraphics.drawImage(background, 0, backgroundy,null);
        player.render(backbufferGraphics);
        for (PlayerSpell playerSpell: playerSpells){
            playerSpell.render(backbufferGraphics);
        }
        windowGraphics.drawImage(backbufferImage, 0, 0, null);
    }
}