package minesweeper.model;

import java.util.ArrayList;
import java.util.List;
import minesweeper.gui.IFrame;

public class Match implements FieldListener, StatisticsListener {
    
    private MatchState state;
    private List<MatchListener> listeners;
    
    private Field field;
    private Statistics statistics;
    
    public Match(){
        state = MatchState.STARTING;
        listeners = new ArrayList<>();
        field = new Field(10, 10, 50,50);
        statistics = new Statistics();
    }
    
    public MatchState getState(){
        return this.state;
    }

    @Override
    public void fieldStateChanged(FieldInfo info) {
        if (info.getFlaggedCellsCount() == info.getMinedCellsCount()){
            stateChanged(new MatchInfo(MatchState.WIN));
        }
        if (info.getDetonated()){
            stateChanged(new MatchInfo(MatchState.LOST));
        }
    }
    
    @Override
    public void statisticsStateChanged(StatisticsInfo info) {
        
    }
    
    public void start(IFrame frame){
        frame.getIFieldPanel().listen(field);
        frame.getIStatusBar().listen(statistics);
        field.listen(frame.getIFieldPanel().getFieldListener());
        statistics.listen(frame.getIStatusBar().getStatisticsListener());
        listen(field);
        listen(statistics);
        field.listen(this);
        statistics.listen(this);
        stateChanged(new MatchInfo(MatchState.STARTING));
    }
    
    public void listen(MatchListener listener){
        if (listeners.contains(listener)){
            return;
        }
        listeners.add(listener);
    }
    
    public void unlisten(MatchListener listener){
        if (!listeners.contains(listener)){
            return;
        }
        listeners.remove(listener);
    }
    
    private void stateChanged(MatchInfo info){
        listeners.forEach((listener) -> listener.matchStateChanged(info));
    }
    
}