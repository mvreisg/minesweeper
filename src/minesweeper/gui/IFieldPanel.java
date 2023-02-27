package minesweeper.gui;

import minesweeper.model.FieldListener;

public interface IFieldPanel {
    
    void startIFieldPanel();
    
    void listen(FieldPanelListener listener);
    
    void unlisten(FieldPanelListener listener);
    
    FieldListener getFieldListener();
    
}