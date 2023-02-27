package minesweeper.gui;

public class MouseInfo {
    
    private int x;
    private int y;
    private MouseButton button;
    
    public MouseInfo(int x, int y, MouseButton button){
        this.x = x;
        this.y = y;
        this.button = button;
    }

    public int getX() {
        return this.x;
    }
    
    public int getY() {
        return this.y;
    }
    
    public MouseButton getButton(){
        return this.button;
    }
    
}