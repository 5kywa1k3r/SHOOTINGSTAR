package touhou.Inputs;

import java.awt.event.KeyEvent;

import static java.awt.event.KeyEvent.*;

public class InputManager {
    public boolean upPressed;
    public boolean downPressed;
    public boolean leftPressed;
    public boolean rightPressed;
    public boolean xPressed;
    public boolean cPressed;
    public boolean zPressed;
    public boolean vPressed;

    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case VK_RIGHT:
                rightPressed = true;
                break;
            case VK_LEFT:
                leftPressed = true;
                break;
            case VK_UP:
                upPressed = true;
                break;
            case VK_DOWN:
                downPressed = true;
                break;
            case VK_X:
                xPressed = true;
                break;
            case VK_C:
                cPressed = true;
                break;
            case VK_Z:
                zPressed = true;
                break;
            case VK_V:
                vPressed = true;
                xPressed = false;
                cPressed = false;
                zPressed = false;
                break;
        }
    }

    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case VK_RIGHT:
                rightPressed = false;
                break;
            case VK_LEFT:
                leftPressed = false;
                break;
            case VK_UP:
                upPressed = false;
                break;
            case VK_DOWN:
                downPressed = false;
                break;
            case VK_X:
                xPressed = false;
                break;
            case VK_C:
                cPressed = false;
                break;
            case VK_Z:
                zPressed = false;
                break;
            case VK_V:
                vPressed = false;
                break;
        }
    }
}
