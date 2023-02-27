package minesweeper.gui;

import javax.swing.BoxLayout;
import javax.swing.JFrame;

public class Frame extends JFrame implements IFrame {
    
    private IStatusBar statusBar;
    private IFieldPanel fieldPanel;
    
    public Frame(){
        super("minesweeper");
        statusBar = new StatusBar();
        fieldPanel = new FieldPanel();
    }

    @Override
    public void startIFrame() {
        setDefaultCloseOperation(Frame.EXIT_ON_CLOSE);
        setResizable(false);
        add((StatusBar)statusBar);
        add((FieldPanel)fieldPanel);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        pack();
        setVisible(true);
        statusBar.startIStatusBar();
        fieldPanel.startIFieldPanel();
    }

    @Override
    public IFieldPanel getIFieldPanel() {
        return fieldPanel;
    }

    @Override
    public IStatusBar getIStatusBar() {
        return statusBar;
    }
    
}