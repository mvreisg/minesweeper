package minesweeper.model;

public class MatchInfo {

    private MatchState state;
    
    public MatchInfo(MatchState state){
        this.state = state;
    }
    
    public MatchState getState(){
        return this.state;
    }
    
}