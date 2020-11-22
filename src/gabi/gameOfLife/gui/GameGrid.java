package gabi.gameOfLife.gui;

import javax.swing.*;
import java.awt.*;

public class GameGrid extends JPanel {
    private Cell[][] cells;
    private int rows, cols;
    private static final int[][] offsetsNeighbours = {
            {-1,-1}, //NW
            {0,-1}, //W
            {1,-1}, //sw
            {1,0}, //S
            {1,1}, //SE
            {0,1}, //E
            {-1,1}, //NE
            {-1,0} //N
    };

    public GameGrid(int rows, int cols) {
        //BLACK - dead cell, WHITE - live cell
        this.rows = rows;
        this.cols = cols;
        cells =  new Cell[rows][cols];
        setLayout(new GridLayout(rows,cols,1,1));
        setBackground(Color.BLACK);

    }

    public void buildGrid() {
        for(int r = 0; r < rows; r++) {
            for(int c = 0; c < cols; c++) {
                cells[r][c] = new Cell(r,c,false);
                cells[r][c].setBackground(Color.BLACK);
                this.add(cells[r][c]);
            }
        }
    }

    public void checkNeighbours(){
        //return the numbers of neighbours alive

        for(int r = 0; r < rows; r++) {
            for(int c = 0; c < cols; c++) {
                //for every cell in our grid
                int neighboursCount = 0;
                for(int[] n : offsetsNeighbours){
                    //iterate over all its neighbours and check if they are alive
                    if(checkCell(r + n[0],c + n[1])){
                        neighboursCount++;
                    }
                }
                cells[r][c].setNeighboursCount(neighboursCount);
            }
        }
    }

    public boolean checkCell(int x, int y){
        //this one will check if the cell is not on the borders and if its alive
        //will return true just when all conditions are true;
        return x >= 0 && y >= 0 && x < cols && y < rows && cells[x][y].isAlive();
    }

    public void updateCell(){
        for(int x = 0; x < rows; x++) {
            for(int y = 0; y < cols; y++) {
                if(cells[x][y].isAlive() && (cells[x][y].getNeighboursCount() < 2 || cells[x][y].getNeighboursCount() > 3)){
                    // dies by underpopulation or overpopulation
                    //by default will remain alive if has 2 or exactly 3 neighbours
                    cells[x][y].setAlive(false);
                }
                if(! cells[x][y].isAlive() && cells[x][y].getNeighboursCount() == 3){
                    cells[x][y].setAlive(true);
                }
            }
        }
    }
    public void resetGrid(){
        for(int r = 0; r < rows; r++) {
            for(int c = 0; c < cols; c++) {
                cells[r][c].setAlive(false);
                cells[r][c].setNeighboursCount(0);
            }
        }
    }
}
