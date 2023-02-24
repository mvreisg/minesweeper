package minesweeper.inputs;

public class MouseInfo {
    
    private int x;
    private int y;
    private MouseButtons button;
    
    public MouseInfo(int x, int y, MouseButtons button){
        this.x = x;
        this.y = y;
        this.button = button;
    }
    
    public int getX(){
        return this.x;
    }
    
    public int getY(){
        return this.y;
    }
    
    public MouseButtons getButton(){
        return this.button;
    }
    
}