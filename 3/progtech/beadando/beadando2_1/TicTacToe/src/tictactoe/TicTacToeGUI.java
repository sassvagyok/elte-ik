/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tictactoe;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**
 *
 * @author matep
 */
public class TicTacToeGUI {
    private JFrame frame;
    private BoardGUI boardGUI;
    
    private final int INITIAL_ROWS = 8;
    private final int INITIAL_COLS = 5;

    /**
     * Fő ablak létrehozása.
     */
    public TicTacToeGUI() {
        frame = new JFrame("TicTacToe");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        boardGUI = new BoardGUI(INITIAL_ROWS, INITIAL_COLS);
        frame.getContentPane().add(boardGUI.getBoardPanel(), BorderLayout.CENTER);
        frame.getContentPane().add(boardGUI.getPlayerLabel(), BorderLayout.SOUTH);
        
        JMenuBar menuBar = new JMenuBar();
        frame.setJMenuBar(menuBar);
        JMenu gameMenu = new JMenu("Game");
        menuBar.add(gameMenu);
        JMenu newMenu = new JMenu("New");
        gameMenu.add(newMenu);
        int[] boardRowSizes = new int[]{8, 10, 12};
        int[] boardColSizes = new int[]{5, 6, 7};
        for (int i = 0; i < boardRowSizes.length; ++i) {
            JMenuItem sizeMenuItem = new JMenuItem(boardRowSizes[i] + "x" + boardColSizes[i]);
            newMenu.add(sizeMenuItem);
            final int index = i;
            sizeMenuItem.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    frame.getContentPane().remove(boardGUI.getBoardPanel());
                    frame.getContentPane().remove(boardGUI.getPlayerLabel());
                    boardGUI = new BoardGUI(boardRowSizes[index], boardColSizes[index]);
                    frame.getContentPane().add(boardGUI.getBoardPanel(), BorderLayout.CENTER);
                    frame.getContentPane().add(boardGUI.getPlayerLabel(), BorderLayout.SOUTH);
                    frame.pack();
                }
            });
        }
        JMenuItem exitMenuItem = new JMenuItem("Exit");
        gameMenu.add(exitMenuItem);
        exitMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                System.exit(0);
            }
        });
        
        frame.pack();
        frame.setVisible(true);
    }
    
    
}
