package minesweeper.model;

public abstract class Cell {
    
    private int row;
    private int column;
    private int width = 10;
    private int height = 10;
    private CellState state;
    
    public Cell(int row, int column, int width, int height, CellState state){
        this.row = row;
        this.column = column;
        this.width = width;
        this.height = height;
        this.state = state;
    }
    
    public int getRow(){
        return row;
    }
    
    public int getColumn(){
        return column;
    }
    
    public int getWidth(){
        return width;
    }
    
    public int getHeight(){
        return height;
    }
    
    public CellState getState(){
        return this.state;
    }
    
    public void setState(CellState state){
        this.state = state;
    }
    
    public boolean matchClick(int x, int y){
        boolean clicked = x >= column * width && x < column * width + width && y >= row * height && y < row * height + height;
        return clicked; 
    }

    public void reveal() {
        this.state = CellState.REVEALED;
    }

    public boolean flag() {
        return (this.state = CellState.HIDDEN_FLAGGED) == CellState.HIDDEN_FLAGGED;
    }

    public boolean unflag() {
        return (this.state = CellState.HIDDEN_UNFLAGGED) == CellState.HIDDEN_UNFLAGGED;
    }
    
}