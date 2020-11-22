package gabi.gameOfLife.gui;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Cell extends JButton implements MouseListener {
    private int x,y;
    private boolean isAlive;
    public static final int cellSize = 10;
    private int neighboursCount = 0;

    public Cell(int x, int y, boolean isAlive) {
        super();
        this.x = x;
        this.y = y;
        this.isAlive = isAlive;
        addMouseListener(this);
        setBorderPainted(false);
    }

    public int getNeighboursCount() {
        return neighboursCount;
    }

    public void setNeighboursCount(int neighboursCount) {
        this.neighboursCount = neighboursCount;
    }

    public int getGridX() {
        return x;
    }

    public void setGridX(int x) {
        this.x = x;
    }


    public int getGridY() {
        return y;
    }

    public void setGridY(int y) {
        this.y = y;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
        if(isAlive){
            setBackground(Color.WHITE);
        }else {
            setBackground(Color.BLACK);
        }
    }
    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        setAlive(!isAlive);
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        if(e.getModifiersEx() == MouseEvent.BUTTON1_DOWN_MASK){
            setAlive(!isAlive);
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
