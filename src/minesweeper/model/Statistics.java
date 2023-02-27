package minesweeper.model;

import java.util.ArrayList;
import javax.swing.Timer;
import minesweeper.gui.MouseInfo;
import minesweeper.gui.StatusBarListener;
import static minesweeper.model.MatchState.IN_PROGRESS;
import static minesweeper.model.MatchState.LOST;
import static minesweeper.model.MatchState.STARTING;
import static minesweeper.model.MatchState.WIN;

public class Statistics implements StatusBarListener, MatchListener {
    
    private double seconds;
    private Timer timer;
    
    private ArrayList<StatisticsListener> listeners;
    
    public Statistics(){
        listeners = new ArrayList<>();
        timer = new Timer(1000, (ae) -> {
            seconds += 1.0;
            stateChanged(new StatisticsInfo(seconds));
        });
    }
    
    private void start(){
        timer.start();
    }    
    
    private void stop(){
        timer.stop();
    }
    
    private void restart(){
        timer.restart();
    }
    
    public void listen(StatisticsListener listener){
        if (listeners.contains(listener)){
            return;
        }
        listeners.add(listener);
    }
    
    public void unlisten(StatisticsListener listener){
        if (!listeners.contains(listener)){
            return;
        }
        listeners.remove(listener);
    }
    
    private void stateChanged(StatisticsInfo info){
        listeners.forEach((listener) -> listener.statisticsStateChanged(info));
    }

    @Override
    public void matchStateChanged(MatchInfo info) {
        switch (info.getState()) {
            default:
                throw new RuntimeException();
            case STARTING:
                start();
                break;
            case IN_PROGRESS:
                break;
            case WIN:
                stop();
                break;
            case LOST:
                stop();
                break;
        }
    }

    @Override
    public void mouseClicked(MouseInfo info) {
        
    }
    
}