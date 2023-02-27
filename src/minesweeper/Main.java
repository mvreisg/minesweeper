package minesweeper;

import minesweeper.gui.Frame;
import minesweeper.gui.IFrame;
import minesweeper.model.Match;

public class Main {
    
    private Match match;
    private IFrame frame;
    
    public static void main(String[] args) {
        new Main().start();
    }
    
    private Main(){
        match = new Match();
        frame = new Frame();
    }
    
    private void start(){
        frame.startIFrame();
        match.start(frame);
    }

}