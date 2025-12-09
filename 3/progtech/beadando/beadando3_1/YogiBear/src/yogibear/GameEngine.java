/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yogibear;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.Timer;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 *
 * @author matep
 */
public class GameEngine extends JPanel {

    private final int FPS = 240;
    private final int YOGI_X = 300;
    private final int YOGI_Y = 600;
    private final int YOGI_WIDTH = 30;
    private final int YOGI_HEIGHT = 30;
    private final int YOGI_MOVEMENT = 1;

    private boolean paused = false;
    private Image background;
    private int levelNum = 0;
    private Level level;
    private int lives = 3;
    private int collectedBaskets = 0;
    private Yogi yogi;
    private Timer newFrameTimer;
    private JLabel livesLabel;
    private JLabel basketLabel;
    private YogiBearGUI gui;

    public GameEngine() {
        super();
        background = new ImageIcon("assets/sprites/bg.png").getImage();
        this.getInputMap().put(KeyStroke.getKeyStroke("A"), "pressed a");
        this.getActionMap().put("pressed a", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                yogi.setVelx(-YOGI_MOVEMENT);
            }
        });
        this.getInputMap().put(KeyStroke.getKeyStroke("D"), "pressed d");
        this.getActionMap().put("pressed d", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                yogi.setVelx(YOGI_MOVEMENT);
            }
        });
        this.getInputMap().put(KeyStroke.getKeyStroke("S"), "pressed s");
        this.getActionMap().put("pressed s", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                yogi.setVely(YOGI_MOVEMENT);
            }
        });
        this.getInputMap().put(KeyStroke.getKeyStroke("W"), "pressed w");
        this.getActionMap().put("pressed w", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                yogi.setVely(-YOGI_MOVEMENT);
            }
        });
        this.getInputMap().put(KeyStroke.getKeyStroke("released A"), "released a");
        this.getActionMap().put("released a", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                yogi.setVelx(0);
            }
        });
        this.getInputMap().put(KeyStroke.getKeyStroke("released D"), "released d");
        this.getActionMap().put("released d", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                yogi.setVelx(0);
            }
        });
        this.getInputMap().put(KeyStroke.getKeyStroke("released S"), "released s");
        this.getActionMap().put("released s", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                yogi.setVely(0);
            }
        });
        this.getInputMap().put(KeyStroke.getKeyStroke("released W"), "released w");
        this.getActionMap().put("released w", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                yogi.setVely(0);
            }
        });
        this.getInputMap().put(KeyStroke.getKeyStroke("ESCAPE"), "escape");
        this.getActionMap().put("escape", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                paused = !paused;
            }
        });
        restart(false);
        newFrameTimer = new Timer(1000 / FPS, new NewFrameListener());
        newFrameTimer.start();
    }

    public void setGui(YogiBearGUI gui) {
        this.gui = gui;
    }
    
    public void setStatusLabels(JLabel livesLabel, JLabel basketLabel) {
        this.livesLabel = livesLabel;
        this.basketLabel = basketLabel;
    }
    
    /**
     * JLabel-ek frissítése
     */
    public void updateLabels() {
        if (livesLabel != null && basketLabel != null) {
            livesLabel.setText("Lives: " + lives);
            basketLabel.setText("Baskets: " + collectedBaskets);
        }
    }

    /**
     * Új pálya indítása, az előrehaladás visszaállításával vagy nélkül
     * @param reset 
     */
    public void restart(boolean reset) {
        if (reset) {
            levelNum = 0;
            lives = 3;
            collectedBaskets = 0;
            gui.resetStartingTime();
        }
        
        levelNum = levelNum == 10 ? 0 : levelNum;
        
        try {
            level = new Level("assets/levels/level0" + levelNum + ".txt");
        } catch (IOException ex) {
            Logger.getLogger(GameEngine.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        
        Image yogiImage = new ImageIcon("assets/sprites/yogi.png").getImage();
        yogi = new Yogi(YOGI_X, YOGI_Y, YOGI_WIDTH, YOGI_HEIGHT, yogiImage);
    }

    @Override
    protected void paintComponent(Graphics grphcs) {
        super.paintComponent(grphcs);
        grphcs.drawImage(background, 0, 0, 600, 600, null);
        level.draw(grphcs);
        yogi.draw(grphcs);
    }
    
    /**
     * Játék végén megjelenő highscore mentés
     */
    public void gameOver() {
        JTextField nameField = new JTextField();
        Object[] message = {
            "Game Over!",
            "Baskets: " + collectedBaskets,
            "Name:", nameField
        };
        
        int option = JOptionPane.showConfirmDialog(
            GameEngine.this, 
            message, 
            "Game Over", 
            JOptionPane.OK_CANCEL_OPTION
        );
        
        if (option == JOptionPane.OK_OPTION) {
            String playerName = nameField.getText().trim();
            if (!playerName.isEmpty()) {
                try {
                    HighScores highScores = new HighScores(10);
                    highScores.putHighScore(playerName, collectedBaskets);
                } catch (Exception ex) {
                    Logger.getLogger(GameEngine.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                }
            }
        }
        restart(true);
    }

    public void setPaused(boolean paused) {
        this.paused = paused;
    }

    class NewFrameListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            if (!paused) {
                int oldX = yogi.getX();
                int oldY = yogi.getY();
                
                yogi.moveX();
                
                if (level.collidesWithTree(yogi)) {
                    yogi.setX(oldX);
                }
                
                yogi.moveY();
                if (level.collidesWithTree(yogi)) {
                    yogi.setY(oldY);
                }
                
                int pickedUp = level.picksUp(yogi);
                collectedBaskets += pickedUp;
                level.moveGuards();
                
                if (level.collidesWithGuard(yogi)) {
                    lives--;
                    if (lives > 0) {
                        yogi.setX(YOGI_X);
                        yogi.setY(YOGI_Y);
                    } else {
                        gameOver();
                    }
                }
                
                updateLabels();
            }
            if (level.isOver()) {
                levelNum++;
                restart(false);
            }
            repaint();
        }
    }
}
