package minesweeper.model;

import java.util.ArrayList;
import java.util.Random;
import minesweeper.gui.FieldPanelListener;
import minesweeper.gui.MouseInfo;

public class Field implements FieldPanelListener, MatchListener {
    
    private int rows;
    private int columns;
    private int cellWidth;
    private int cellHeight;
    private Cell[][] cells;
    private int minedCellsCount;
    private int flaggedCellsCount;
    private boolean detonated;
    
    private ArrayList<FieldListener> listeners;
    
    public Field(int rows, int columns, int cellWidth, int cellHeight){
        this.rows = rows;
        this.columns = columns;
        this.cellWidth = cellWidth;
        this.cellHeight = cellHeight;
        cells = new Cell[rows][columns];
        listeners = new ArrayList<>();
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
    
    public Cell[][] getCells(){
        return this.cells;
    }
    
    private void start(){
        generate();
        mapBombs();
        
    }
    
    private void generate(){
        detonated = false;
        Random random = new Random();
        for (int r = 0; r < rows; r++){
            for (int c = 0; c < columns; c++){
                Cell cell = null;
                if (random.nextDouble() < 0.15){
                    cell = new MinedCell(r, c, cellWidth, cellHeight, CellState.HIDDEN_UNFLAGGED);
                    minedCellsCount++;
                }
                else{
                    cell = new ClearCell(r, c, cellWidth, cellHeight, CellState.HIDDEN_UNFLAGGED);
                }
                cells[r][c] = cell;
            }
        }
        onStateChanged();
    }
    
    private void mapBombs(){
        for (int r = 0; r < rows; r++){
            for (int c = 0; c < columns; c++){
                Cell cell = cells[r][c];
                if (cell instanceof ClearCell){
                    ((ClearCell) cell).countBombs(this);
                }
            }
        }
        onStateChanged();
    }
    
    private void revealAll() {
        for (int r = 0; r < rows; r++){
            for (int c = 0; c < columns; c++){
                cells[r][c].reveal();
            }
        }
        onStateChanged();
    }

    @Override
    public void mouseClicked(MouseInfo info) {
        for (int r = 0; r < rows; r++){
            for (int c = 0; c < columns; c++){
                Cell cell = cells[r][c];
                if (cell.matchClick(info.getX(), info.getY())){                    
                    switch(info.getButton()){
                        case LEFT:
                            switch(cell.getState()){
                                case HIDDEN_UNFLAGGED:
                                    cell.reveal();
                                    if (cell instanceof ClearCell){
                                        ClearCell clearCell = (ClearCell)cell;
                                        if (clearCell.getBombCount() > 0){
                                            break;
                                        }
                                        ArrayList<Cell> allCells = new ArrayList<>();
                                        mapClearCells(clearCell, allCells);
                                        allCells.forEach((cellItem) -> cellItem.reveal());                                 
                                    }
                                    if (cell instanceof MinedCell){
                                        revealAll();
                                        detonated = true;
                                    }
                                    break;
                            }
                            break;
                        case MIDDLE:
                            break;
                        case RIGHT:
                            switch(cell.getState()){
                                case HIDDEN_UNFLAGGED:
                                    if (cell.flag()){
                                        flaggedCellsCount++;    
                                    }
                                    break;
                                case HIDDEN_FLAGGED:
                                    if (cell.unflag()){
                                        flaggedCellsCount--;    
                                    }
                                    break;
                            }
                            break;
                    }
                    onStateChanged();
                    return;
                }
            }
        }
    }

    private void mapClearCells(Cell cell, ArrayList<Cell> allCells) {
        int rS = cell.getRow() - 1;
        int rE = cell.getRow() + 1;
        int cS = cell.getColumn() - 1;
        int cE = cell.getColumn() + 1;
        if (rS < 0){
            rS = 0;
        }
        if (cS < 0){
            cS = 0;
        }
        if (rE >= getColumns()){
            rE = getColumns() - 1;
        }
        if (cE >= getRows()){
            cE = getRows() - 1;
        }
        for (int r = rS; r <= rE; r++){
            for (int c = cS; c <= cE; c++){
                Cell requestedCell = cells[r][c];
                if (requestedCell instanceof ClearCell){
                    ClearCell clearCell = (ClearCell)requestedCell;
                    if (clearCell.getBombCount() > 0){
                        if (!allCells.contains(clearCell)){
                            allCells.add(clearCell);
                            continue;
                        }
                    }
                    if (clearCell.getBombCount() == 0){
                        if (!allCells.contains(clearCell)){
                            allCells.add(clearCell);
                            mapClearCells(clearCell, allCells);
                        }
                    }
                }
            }
        }
    }
    
    public void listen(FieldListener listener){
        if (listeners.contains(listener)){
            return;
        }
        listeners.add(listener);
    }
    
    public void unlisten(FieldListener listener){
        if (!listeners.contains(listener)){
            return;
        }
        listeners.remove(listener);
    }
    
    private void onStateChanged(){
        stateChanged(
            new FieldInfo(
                cells.clone(), 
                rows,
                columns,
                cellWidth, 
                cellHeight, 
                minedCellsCount, 
                flaggedCellsCount,
                detonated
            )
        );
    }
    
    private void stateChanged(FieldInfo info){
        listeners.forEach((listener) -> listener.fieldStateChanged(info));
    }

    @Override
    public void matchStateChanged(MatchInfo info) {
        switch (info.getState()) {
            default:
                throw new RuntimeException();
            case STARTING:
                System.out.println("b");
                start();
                break;
            case IN_PROGRESS:
                break;
            case WIN:
                break;
            case LOST:
                break;
        }
    }
    
}