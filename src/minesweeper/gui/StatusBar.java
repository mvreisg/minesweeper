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
import javax.swing.JComponent;
import minesweeper.model.StatisticsInfo;
import minesweeper.model.StatisticsListener;

public class StatusBar extends JComponent implements IStatusBar, MouseListener, StatisticsListener {

    private List<StatusBarListener> listeners;
    
    private BufferedImage image;
    
    public StatusBar(){
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
        return 100;
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
    public void statisticsStateChanged(StatisticsInfo info) {     
        image = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = (Graphics2D)image.createGraphics();
        g.setColor(Color.BLACK);
        double totalSeconds = info.getSeconds();        
        g.drawString(String.format("%f", totalSeconds), 20, 20);
        repaint();
    }

    @Override
    public void startIStatusBar() {
        addMouseListener(this);
    }

    @Override
    public void listen(StatusBarListener listener) {
        if (listeners.contains(listener)){
            return;
        }
        listeners.add(listener);
    }

    @Override
    public void unlisten(StatusBarListener listener) {
        if (!listeners.contains(listener)){
            return;
        }
        listeners.remove(listener);
    }

    @Override
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}

    @Override
    public StatisticsListener getStatisticsListener() {
        return this;
    }

}