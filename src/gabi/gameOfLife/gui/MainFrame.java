package gabi.gameOfLife.gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.Timer;

public class MainFrame extends JFrame  implements ActionListener{
    private boolean running = false;
    private JPanel containerPanel;
    private JButton playButton;
    private JButton resetButton;
    private JTextField generationsField;
    private JLabel generations;
    private JPanel controlPanel;
    private int generationsCnt = 0;
    private JPanel gamePanel;
    private final GameGrid gridPanel;
    private Timer timer;

    public MainFrame(int width, int height){
        setSize(width,height);
        setContentPane(containerPanel);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);

        gridPanel = new GameGrid(getHeight()/Cell.cellSize, getWidth()/Cell.cellSize);
        gridPanel.buildGrid();
        gamePanel.add(gridPanel);

        revalidate();
        repaint();

        playButton.setActionCommand("Start");
        playButton.addActionListener(this);
        resetButton.setActionCommand("Reset");
        resetButton.addActionListener(this);


    }

    public static void main(String[] args){
        new MainFrame(500,500);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //handle every buttons clicks
        String command = e.getActionCommand();
        if (command.equals("Start")) {
            if (!running) {
                timer = new Timer();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        generationsField.setText(String.valueOf(generationsCnt++));
                        gridPanel.checkNeighbours();
                        gridPanel.updateCell();
                        gamePanel.add(gridPanel);
                    }

                }, 0, 100);
                playButton.setText("Pause");
            } else {
                timer.cancel();
                playButton.setText("Start");
            }
            running = !running;

        } else if (command.equals("Reset")) {
            timer.cancel();
            generationsCnt = 0;
            gridPanel.resetGrid();
            generationsField.setText(String.valueOf(generationsCnt));
            playButton.setText("Start");

        }
    }
}
