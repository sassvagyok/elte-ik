/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yogibear;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author matep
 */
public class YogiBearGUI {
    private JFrame frame;
    private GameEngine gameArea;
    private JLabel livesLabel;
    private JLabel basketLabel;
    private JLabel timeLabel;
    private Timer timer;
    private long startTime;

    public YogiBearGUI() {
        frame = new JFrame("Yogi Bear");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        
        gameArea = new GameEngine();
        gameArea.setGui(this);
        gameArea.setPreferredSize(new Dimension(600, 600));
        frame.getContentPane().add(gameArea, BorderLayout.CENTER);
        
        JMenuBar menuBar = new JMenuBar();
        frame.setJMenuBar(menuBar);
        JMenu gameMenu = new JMenu("Game");
        menuBar.add(gameMenu);
        JMenuItem newMenuItem = new JMenuItem("New");
        gameMenu.add(newMenuItem);
        
        newMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameArea.restart(true);
                resetStartingTime();
            }
        });
        
        JMenuItem highScoresMenuItem = new JMenuItem("Highscores");
        gameMenu.add(highScoresMenuItem);
        highScoresMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameArea.setPaused(true);
                showHighScores();
                gameArea.setPaused(false);
            }
        });
        
        JPanel statusPanel = new JPanel();
        livesLabel = new JLabel("Lives: 3");
        basketLabel = new JLabel("Baskets: 0");
        timeLabel = new JLabel(" ");
        timer = new Timer(10, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                timeLabel.setText("Time: " + elapsedTime() / 1000 + " s");
            }
        });
        
        resetStartingTime();
        timer.start();
        statusPanel.add(livesLabel);
        statusPanel.add(basketLabel);
        statusPanel.add(timeLabel);
        frame.add(statusPanel, BorderLayout.SOUTH);
        
        gameArea.setStatusLabels(livesLabel, basketLabel);
        
        frame.setResizable(false);
        frame.pack();
        frame.setVisible(true);
    }
    
    public void resetStartingTime() {
        startTime = System.currentTimeMillis();
    }
    
    public long elapsedTime() {
        return System.currentTimeMillis() - startTime;
    }
    
    /**
     * Elmentett eredmények kijelzése
     */
    public void showHighScores() {
        try {
            HighScores highScores = new HighScores(10);
            ArrayList<HighScore> scores = highScores.getHighScores();
            
            String hs = "";
            
            for (int i = 0; i < scores.size(); i++) {
                HighScore score = scores.get(i);
                hs += i + 1 + ". " + score.getName() + " - " + score.getScore() + "\n";
            }
            
            if (scores.isEmpty()) {
                hs += "No high scores!";
            }
            
            JOptionPane.showMessageDialog(frame, hs, "Highscores", JOptionPane.INFORMATION_MESSAGE);
                
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(frame, 
                "Error: " + ex.getMessage(), 
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
