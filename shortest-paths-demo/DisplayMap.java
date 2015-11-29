import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JFrame;

public class DisplayMap extends JFrame {

    MapPanel mapPanel;

    public DisplayMap(TwoDMap graph) {

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.setLocationRelativeTo(null);
        setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));

        mapPanel = new MapPanel(graph);
        this.add(mapPanel);
        Dimension dimension = new Dimension(400, 420);
        this.setPreferredSize(dimension);
        this.pack();
    }

    public void refresh() {
        this.update(this.getGraphics());
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private class ComputeHandler implements ActionListener {
        public ComputeHandler() {
        }

        public void actionPerformed(ActionEvent e) {
        }
    }
}

