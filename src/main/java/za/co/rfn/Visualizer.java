package za.co.rfn;

import za.co.rfn.ui.VisualizerPanel;
import za.co.rfn.ui.VisualizerRunner;

import javax.swing.*;
import java.awt.*;

public class Visualizer {

    public static void main(String[] args) {
        JFrame gameFrame = new JFrame("Replay Visualizer");
        gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameFrame.setBounds(20, 20, 1250, 600);
        gameFrame.setLayout(new GridLayout(1, 2, 20, 0));

        gameFrame.setVisible(true);

        JPanel player1VisualizerContainer = new JPanel(new BorderLayout());

        JLabel player1VisualizerLabel = new JLabel();
        VisualizerPanel player1VisualizerPanel = new VisualizerPanel(VisualizerPanel.Target.PLAYER1);
        player1VisualizerContainer.add(player1VisualizerLabel, BorderLayout.NORTH);
        player1VisualizerContainer.add(player1VisualizerPanel, BorderLayout.CENTER);

        gameFrame.add(player1VisualizerContainer);

        JLabel player2VisualizerLabel = new JLabel();
        JPanel player2VisualizerContainer = new JPanel(new BorderLayout());
        VisualizerPanel player2VisualizerPanel = new VisualizerPanel(VisualizerPanel.Target.PLAYER2);
        player2VisualizerContainer.add(player2VisualizerLabel, BorderLayout.NORTH);
        player2VisualizerContainer.add(player2VisualizerPanel, BorderLayout.CENTER);

        gameFrame.add(player2VisualizerContainer);

        gameFrame.setVisible(true);

        VisualizerRunner visualizerRunner = new VisualizerRunner(player1VisualizerPanel, player2VisualizerPanel, player1VisualizerLabel, player2VisualizerLabel);
        visualizerRunner.setGameDirectory(args[0]);
        visualizerRunner.run();
    }

}
