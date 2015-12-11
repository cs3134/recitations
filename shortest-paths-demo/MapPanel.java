import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class MapPanel extends JPanel {

    // graph layout parameters
    static final int cell_size = 20;

    TwoDMap map;

    public MapPanel(TwoDMap map) {
        this.map = map;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g); // paint the panel
        paintGraph(g); // paint the graph
    }

    public void paintGraph(Graphics g) {
        for (int i=0;i < map.width;i++)
            for (int j=0;j < map.height;j++)
                switch (map.getPos(i,j)) {
                    case 1:
                        g.setColor(Color.BLACK);
                        g.drawRect(i * cell_size, j * cell_size, cell_size, cell_size);
                        g.fillRect(i * cell_size, j * cell_size, cell_size, cell_size);
                        break;
                    case 2:
                        g.setColor(Color.RED);
                        g.drawRect(i * cell_size, j * cell_size, cell_size, cell_size);
                        g.fillRect(i * cell_size, j * cell_size, cell_size, cell_size);
                        break;
                    case 3:
                        g.setColor(Color.BLUE);
                        g.drawRect(i * cell_size, j * cell_size, cell_size, cell_size);
                        g.fillRect(i * cell_size, j * cell_size, cell_size, cell_size);
                        break;
                    case 4:
                        g.setColor(Color.LIGHT_GRAY);
                        g.drawRect(i * cell_size, j * cell_size, cell_size, cell_size);
                        g.fillRect(i * cell_size, j * cell_size, cell_size, cell_size);
                        break;
                    default:
                        g.setColor(Color.WHITE);
                        g.fillRect(i * cell_size, j * cell_size, cell_size, cell_size);
                        g.setColor(Color.BLACK);
                        g.drawRect(i * cell_size, j * cell_size, cell_size, cell_size);
                        break;
                }
    }


}
