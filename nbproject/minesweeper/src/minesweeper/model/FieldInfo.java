package minesweeper.model;

public class FieldInfo {
    
    private Cell[][] cells;
    private int rows;
    private int columns;
    private int cellWidth;
    private int cellHeight;
    private int minedCellsCount;
    private int flaggedCellsCount;
    
    public FieldInfo(Cell[][] cells, int rows, int columns, int cellWidth, int cellHeight, int minedCellsCount, int flaggedCellsCount){
        this.cells = cells;
        this.rows = rows;
        this.columns = columns;
        this.cellWidth = cellWidth;
        this.cellHeight = cellHeight;
        this.minedCellsCount = minedCellsCount;
        this.flaggedCellsCount = flaggedCellsCount;
    }
    
    public Cell[][] getCells(){
        return this.cells;
    }
    
    public int getRows(){
        return this.rows;
    }
    
    public int getColumns(){
        return this.columns;
    }
    
    public int getCellWidth(){
        return this.cellWidth;
    }
    
    public int getCellHeight(){
        return this.cellHeight;
    }
    
    public int getMinedCellsCount(){
        return this.minedCellsCount;
    }
    
    public int getFlaggedCellsCount(){
        return this.flaggedCellsCount;
    }
    
}