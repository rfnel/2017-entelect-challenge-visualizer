package za.co.rfn.ui;

import com.google.gson.Gson;
import za.co.entelect.domain.state.BattleshipPlayer;
import za.co.entelect.domain.state.GameState;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class VisualizerRunner implements Runnable {

    private String gameDirectory;

    private VisualizerPanel player1VisualizerPanel;
    private VisualizerPanel player2VisualizerPanel;
    private JLabel player1VisualizerLabel;
    private JLabel player2VisualizerLabel;

    private List<GameState> roundStates = new ArrayList<>();

    private final Gson gson = new Gson();

    private static final String STATE_FILE_NAME = "state.json";

    public VisualizerRunner(VisualizerPanel player1VisualizerPanel, VisualizerPanel player2VisualizerPanel, JLabel player1VisualizerLabel, JLabel player2VisualizerLabel) {
        this.player1VisualizerPanel = player1VisualizerPanel;
        this.player2VisualizerPanel = player2VisualizerPanel;
        this.player1VisualizerLabel = player1VisualizerLabel;
        this.player2VisualizerLabel = player2VisualizerLabel;
    }

    public void setGameDirectory(String gameDirectory) {
        this.gameDirectory = gameDirectory;
    }

    @Override
    public void run() {
        loadRoundStates();

        for (int i = 0; i < roundStates.size(); i++) {
            GameState roundState = roundStates.get(i);
            player1VisualizerPanel.updateGameState(roundState);
            player2VisualizerPanel.updateGameState(roundState);

            BattleshipPlayer player1 = roundState.Player1Map.Owner;
            player1VisualizerLabel.setText("Name: " + player1.Name + ", Points: " + player1.Points + ", Round: " + roundState.Round);

            BattleshipPlayer player2 = roundState.Player2Map.Owner;
            player2VisualizerLabel.setText("Name: " + player2.Name + ", Points: " + player2.Points + ", Round: " + roundState.Round);

            try {
                Thread.sleep(500);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }

    private void loadRoundStates() {
        roundStates.clear();

        File gameDir = new File(gameDirectory);

        File[] roundDirs = gameDir.listFiles();
        Arrays.sort(roundDirs, (File o1, File o2) -> {
            String name1Numbers = o1.getName().replaceAll("[\\D]", "");
            String name2Numbers = o2.getName().replaceAll("[\\D]", "");

            return Integer.valueOf(name1Numbers) - Integer.valueOf(name2Numbers);
        });

        for (File roundDir : roundDirs) {
            GameState roundState = loadRoundState(roundDir.getAbsolutePath());
            roundStates.add(roundState);
        }

        System.out.printf("Loaded %d rounds.%n", roundStates.size());
    }

    private GameState loadRoundState(String directory) {
        try {
            List<String> lines = Files.readAllLines(Paths.get(directory, STATE_FILE_NAME));

            String json = String.join("\n", lines);

            return gson.fromJson(new StringReader(json), GameState.class);
        } catch (IOException ex) {
            throw new IllegalStateException("Failed to read GameState", ex);
        }
    }
}
