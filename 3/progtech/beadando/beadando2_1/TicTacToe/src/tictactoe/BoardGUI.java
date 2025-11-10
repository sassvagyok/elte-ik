/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tictactoe;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author matep
 */
public class BoardGUI {
    private JButton[][] buttons;
    private Board board;
    private JPanel boardPanel;
    private JLabel playerLabel;
    private Point currentPoint;

    private int round = 0;

    /**
     * Board és gombok létrehozása.
     * @param boardRows
     * @param boardCols 
     */
    public BoardGUI(int boardRows, int boardCols) {
        board = new Board(boardRows, boardCols);
        boardPanel = new JPanel();
        boardPanel.setLayout(new GridLayout(board.getBoardRows(), board.getBoardCols()));
        buttons = new JButton[board.getBoardRows()][board.getBoardCols()];
        
        for (int i = 0; i < board.getBoardRows(); ++i) {
           for (int j = 0; j < board.getBoardCols(); ++j) {
                JButton button = new JButton();
                button.addActionListener(new ButtonListener(i, j));
                button.setPreferredSize(new Dimension(60, 60));
                buttons[i][j] = button;
                boardPanel.add(button);
            } 
        }
        
        playerLabel = new JLabel("Player 1");
        playerLabel.setHorizontalAlignment(JLabel.RIGHT);
    }

    /**
     * Aktív játékos kiírása.
     * @param player 
     */
    public void updatePlayerLabel(int player) {
        playerLabel.setText("Player " + String.valueOf(player));
    }
    
    /**
     * Játék frissítése.
     */
    public void refresh() {
        Field field = board.get(currentPoint);
        JButton button = buttons[currentPoint.x][currentPoint.y];
        button.setText(field.getCharacter());
        
        updatePlayerLabel(round % 2 == 0 ? 1 : 2);
        
        if (board.isFull()) {
            reset();
        }
        
        if (board.winner() != -1) {
            JOptionPane.showMessageDialog(boardPanel, "Winner: Player " + board.winner(), "Congrats!", JOptionPane.PLAIN_MESSAGE);
            reset();
        }
    }
    
    /**
     * Új játék indítása.
     */
    public void reset() {
        round = 0;
        board = new Board(board.getBoardRows(), board.getBoardCols());
        
        for (int i = 0; i < buttons.length; ++i) {
            for (int j = 0; j < buttons[i].length; ++j) {
                buttons[i][j].setText("");
            }
        }
        
        updatePlayerLabel(1);
    }
    
    public JPanel getBoardPanel() {
        return boardPanel;
    }

    public JLabel getPlayerLabel() {
        return playerLabel;
    }
    
    class ButtonListener implements ActionListener {

        private int x, y;

        public ButtonListener(int x, int y) {
            this.x = x;
            this.y = y;
        }

        /**
         * Új karakter potyogtatása.
         * @param e 
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            currentPoint = null;
            int player = round % 2 == 0 ? 1 : 2;
            String character = player == 1 ? "X" : "O";
            Field topField = null;
            
            for (int i = 0; i < board.getBoardRows(); ++i) {
                if (board.get(i, y).getCharacter() == null) {
                    topField = board.get(i, y);
                    currentPoint = new Point(i, y);
                }
            }
            
            if (topField == null) {
                return;
            }
            
            topField.setCharacter(character);
            topField.setPlayer(player);
            
            round++;
            
            refresh();
        }
    }
}
