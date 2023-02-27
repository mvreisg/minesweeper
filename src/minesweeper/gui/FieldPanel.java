package minesweeper.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.swing.JComponent;
import javax.swing.SwingUtilities;
import minesweeper.model.Cell;
import minesweeper.model.ClearCell;
import minesweeper.model.FieldInfo;
import minesweeper.model.FieldListener;
import minesweeper.model.MinedCell;

public class FieldPanel extends JComponent implements MouseListener, IFieldPanel, FieldListener {
    
    private List<FieldPanelListener> listeners;
    
    private BufferedImage image;
    
    public FieldPanel(){
        super();
        listeners = new ArrayList<>();
        image = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
    }

    @Override
    public int getWidth() {
        return 500;
    }

    @Override
    public int getHeight() {
        return 500;
    }        
    
    @Override
    public Dimension getSize() {
        return new Dimension(getWidth(), getHeight());
    }

    @Override
    public Dimension getMinimumSize() {
        return getSize();
    }

    @Override
    public Dimension getMaximumSize() {
        return getSize();
    }

    @Override
    public Dimension getPreferredSize() {
        return getSize();
    }
    
    @Override
    public void paint(Graphics g) {
        g.drawImage(image, 0, 0, this);
    }

    @Override
    public void fieldStateChanged(FieldInfo info) {
        System.out.println("a");
        Random random = new Random();
        image = new BufferedImage(info.getCellWidth() * info.getColumns(), info.getCellHeight() * info.getRows(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = (Graphics2D)image.createGraphics();
        for (int r = 0; r < info.getRows(); r++){
            for (int c = 0; c < info.getColumns(); c++){
                Cell cell = info.getCells()[r][c];
                int row = cell.getRow();
                int column = cell.getColumn();
                int width = info.getCellWidth();
                int height = info.getCellHeight();                
                int x = column * width;
                int y = row * height;
                int color = 0xFF << 24;
                switch(cell.getState()){
                    case REVEALED:
                        if (cell instanceof ClearCell){
                            color |= 0xFFFFFF;                            
                        }
                        if (cell instanceof MinedCell){
                            color |= 0xFF0000;
                        }
                        break;
                    case HIDDEN_FLAGGED:
                        color |= 0xFF8822; 
                        break;
                    case HIDDEN_UNFLAGGED:
                        color |= 0x0000FF; 
                        break;
                }
                g.setColor(new Color(color, true));
                g.fillRect(x, y, width, height);
                switch(cell.getState()){
                    case REVEALED:
                        if (cell instanceof ClearCell){
                            ClearCell clearCell = (ClearCell)cell;
                            g.setColor(Color.BLACK);
                            int bombCount = clearCell.getBombCount();
                            if (bombCount > 0){
                                g.drawString(String.format("%d", bombCount), x + 20, y + 30);    
                            }
                        }                        
                        break;                    
                }
            }
        }
        g.setColor(Color.BLACK);
        for (int r = 0; r < info.getRows(); r++){
            for (int c = 0; c < info.getColumns(); c++){
                g.drawLine(0, c * info.getCellHeight(), info.getColumns() * info.getCellWidth(), c * info.getCellHeight());    
                g.drawLine(c * info.getCellWidth(), 0, c * info.getCellWidth(), info.getRows() * info.getCellHeight());    
            }
        }
        repaint();
    }       

    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println("sdee");
        MouseButton button = null;
        if (SwingUtilities.isLeftMouseButton(e)){
            button = MouseButton.LEFT;
        }
        else if (SwingUtilities.isRightMouseButton(e)){
            button = MouseButton.RIGHT;
        }
        else if (SwingUtilities.isMiddleMouseButton(e)){
            button = MouseButton.MIDDLE;
        }
        else{
            throw new RuntimeException("???");
        }
        triggerMouseClicked(new MouseInfo(e.getX(), e.getY(), button));
    }

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}
    
    @Override
    public void listen(FieldPanelListener listener){
        if (listeners.contains(listener)){
            return;
        }
        listeners.add(listener);
    }
    
    @Override
    public void unlisten(FieldPanelListener listener){
        if (!listeners.contains(listener)){
            return;
        }
        listeners.remove(listener);
    }
    
    private void triggerMouseClicked(MouseInfo info){
        listeners.forEach((listener) -> listener.mouseClicked(info));
    }

    @Override
    public void startIFieldPanel() {
        addMouseListener(this);
    }

    @Override
    public FieldListener getFieldListener() {
        return this;
    }
    
}