package minesweeper.inputs;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.SwingUtilities;

public class Mouse implements MouseListener {   

    private ArrayList<minesweeper.inputs.MouseListener> listeners;
    
    public Mouse(){
        listeners = new ArrayList<>();
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {        
        if (SwingUtilities.isLeftMouseButton(e)){
            onClick(new MouseInfo(e.getX(), e.getY(), MouseButtons.LEFT));
            return;
        }
        if (SwingUtilities.isRightMouseButton(e)){
            onClick(new MouseInfo(e.getX(), e.getY(), MouseButtons.RIGHT));
            return;
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}
    
    public void listen(minesweeper.inputs.MouseListener listener){
        if (listeners.contains(listener)){
            return;
        }
        listeners.add(listener);
    }
    
    public void unlisten(minesweeper.inputs.MouseListener listener){
        if (!listeners.contains(listener)){
            return;
        }
        listeners.remove(listener);
    }
    
    private void onClick(MouseInfo info){
        listeners.forEach((listener) -> listener.mouseClicked(info));
    }
    
}