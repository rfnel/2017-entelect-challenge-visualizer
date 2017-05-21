package za.co.rfn.ui;

import za.co.entelect.domain.state.Cell;
import za.co.entelect.domain.state.GameState;
import java.awt.Color;
import java.awt.Graphics;
import java.util.List;

import javax.swing.*;

public class VisualizerPanel extends JPanel {

    public enum Target {PLAYER1, PLAYER2};

    private final Target target;

    private GameState gameState;

    private static final Color WATER = new Color(0, 100, 255);
    private static final Color SHIP = Color.GRAY;
    private static final Color MISS = Color.WHITE;
    private static final Color HIT = Color.RED;

    public VisualizerPanel(Target target) {
        this.target = target;
    }

    public void updateGameState(GameState gameState) {
        this.gameState = gameState;

        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (gameState != null) {
            int cellHeight = getHeight() / gameState.MapDimension;
            int cellWidth = getWidth() / gameState.MapDimension;

            List<Cell> cells = null;
            if (target == Target.PLAYER1) {
                cells = gameState.Player1Map.Cells;
            } else if (target == Target.PLAYER2) {
                cells = gameState.Player2Map.Cells;
            }

            for (Cell cell : cells) {
                int x = cellWidth * cell.X;
                int y = getHeight() - (cellHeight * cell.Y) - cellHeight;

                g.drawRect(x, y, cellWidth, cellHeight);

                if (cell.Occupied) {
                    g.setColor(SHIP);
                    g.fillRect(x + 1, y + 1, cellWidth - 1, cellHeight - 1);
                } else {
                    g.setColor(WATER);
                    g.fillRect(x + 1, y + 1, cellWidth - 1, cellHeight - 1);
                }

                if (cell.Hit) {
                    int centerX = x + (cellWidth / 2);
                    int centerY = y + (cellHeight / 2);

                    int radius = cellHeight/2;

                    g.setColor(cell.Occupied ? HIT : MISS);

                    g.fillOval(centerX - (radius/2), centerY - (radius/2), radius, radius);
                }

                g.setColor(Color.BLACK);

                g.drawString(cell.Y + ":" + cell.X, x + 1, y + 12);
            }
        }
    }
}
