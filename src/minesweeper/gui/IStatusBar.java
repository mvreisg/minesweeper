package minesweeper.gui;

import minesweeper.model.StatisticsListener;

public interface IStatusBar {

    void startIStatusBar();
    
    void listen(StatusBarListener listener);
    
    void unlisten(StatusBarListener listener);
    
    StatisticsListener getStatisticsListener();
    
}