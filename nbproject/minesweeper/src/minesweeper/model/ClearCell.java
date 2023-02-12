package minesweeper.model;

import java.util.ArrayList;

public class ClearCell extends Cell {
    
    private int bombCount;
    
    public ClearCell(int row, int column, int width, int height, CellState state) {
        super(row, column, width, height, state);
    }
    
    public int getBombCount(){
        return this.bombCount;
    }
    
    public void countBombs(Field field){
        Cell[][] cells = field.getCells().clone();
        int rowStart = getRow() - 1;
        if (rowStart < 0){
            rowStart = 0;
        }
        int rowEnd = getRow() + 1;
        if (rowEnd >= field.getRows()){
            rowEnd = field.getRows() - 1;
        }
        int columnStart = getColumn() - 1;
        if (columnStart < 0){
            columnStart = 0;
        }
        int columnEnd = getColumn() + 1;
        if (columnEnd >= field.getColumns()){
            columnEnd = field.getColumns() - 1;
        }
        for (int r = rowStart; r <= rowEnd; r++){
            for (int c = columnStart; c <= columnEnd; c++){    
                Cell cell = cells[r][c];
                if (cell instanceof MinedCell){
                    bombCount++;
                }
            }
        }
    }
    
}