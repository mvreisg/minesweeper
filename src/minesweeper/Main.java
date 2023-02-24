package minesweeper;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import minesweeper.gui.FieldPanel;
import minesweeper.gui.StatusBar;
import minesweeper.inputs.Mouse;
import minesweeper.model.Match;

public class Main {
    
    private Match match;
    private Mouse mouse;
    private JFrame frame;
    private StatusBar statusBar;
    private FieldPanel fieldPanel;
    
    public static void main(String[] args) {
        new Main().start();
    }
    
    private Main(){
        mouse = new Mouse();        
        match = new Match();
        frame = new JFrame("minesweeper");
        statusBar = new StatusBar();
        fieldPanel = new FieldPanel();
    }
    
    private void start(){
        frame.getContentPane().add(statusBar);
        frame.getContentPane().add(fieldPanel);
        frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.pack();
        frame.setVisible(true);
        fieldPanel.addMouseListener(mouse);
        match.receiveMouseHandler(mouse);
        match.receiveStatisticsComponent(statusBar);
        match.receiveFieldComponent(fieldPanel);
        match.start();
    }

}