/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tictactoe;

import java.awt.Point;

/**
 *
 * @author matep
 */
public class Board {
    private Field[][] board;
    private final int boardRows;
    private final int boardCols;

    /**
     * Megadott sornyi és oszlopnyi Field létrehozása.
     * @param boardRows
     * @param boardCols 
     */
    public Board(int boardRows, int boardCols) {
        this.boardRows = boardRows;
        this.boardCols = boardCols;
        board = new Field[this.boardRows][this.boardCols];
        for (int i = 0; i < this.boardRows; ++i) {
            for (int j = 0; j < this.boardCols; ++j) {
                board[i][j] = new Field();
            }
        }
    }
    
    /**
     * Ha van átlósan vagy vízszintesen 4 ugyanolyan karakter, a nyertes játékos visszaadása.
     * @return 
     */
    public int winner() {
        for (int i = 0; i < boardRows; i++) {
            for (int j = 0; j < boardCols; j++) {
                if (board[i][j].getCharacter() != null) {
                    if (j <= boardCols - 4 &&
                        board[i][j].getCharacter().equals(board[i][j+1].getCharacter()) &&
                        board[i][j].getCharacter().equals(board[i][j+2].getCharacter()) &&
                        board[i][j].getCharacter().equals(board[i][j+3].getCharacter())) {
                        return board[i][j].getPlayer();
                    }

                    if (i <= boardRows -4 && j <= boardCols -4 &&
                        board[i][j].getCharacter().equals(board[i+1][j+1].getCharacter()) &&
                        board[i][j].getCharacter().equals(board[i+2][j+2].getCharacter()) &&
                        board[i][j].getCharacter().equals(board[i+3][j+3].getCharacter())) {
                            return board[i][j].getPlayer();
                    }

                    if (i <= boardRows - 4 && j >= 3 &&
                        board[i][j].getCharacter().equals(board[i+1][j-1].getCharacter()) &&
                        board[i][j].getCharacter().equals(board[i+2][j-2].getCharacter()) &&
                        board[i][j].getCharacter().equals(board[i+3][j-3].getCharacter())) {
                            return board[i][j].getPlayer();
                    }
                }
            }
        }
        return -1;
    }
    
    /**
     * Tele van-e az egész tábla?
     * @return 
     */
    public boolean isFull() {
        for (int i = 0; i < boardRows; i++) {
            for (int j = 0; j < boardCols; j++) {
                if (board[i][j].getCharacter() == null) {
                    return false;
                }
            }
        }
        
        return true;
    }

    public void setBoard(Field[][] board) {
        this.board = board;
    }

    public Field[][] getBoard() {
        return board;
    }

    /**
     * Field visszaadása x, y index alapján.
     * @param x
     * @param y
     * @return 
     */
    public Field get(int x, int y) {
        return board[x][y];
    }
    
    /**
     * Field visszaadása egy pont alapján.
     * @param point
     * @return 
     */
    public Field get(Point point) {
        int x = (int)point.getX();
        int y = (int)point.getY();
        return get(x, y);
    }

    public int getBoardCols() {
        return boardCols;
    }

    public int getBoardRows() {
        return boardRows;
    }
    
}
