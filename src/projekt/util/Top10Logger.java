package projekt.util;

import projekt.game.components.ships.players.Player;
import projekt.util.logger.Logger;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Top10Logger {
    public static String filename = "top10.txt";

    public static void manageWinner(String playerName, double score, double health, double gameTime) {
        double finalScore = score + health * 30 - gameTime / 100.;

        List<Player> players = getTop10();

        players.add(new Player(11, finalScore, playerName));

        Collections.sort(players);

        writeTop10(players);
    }

    private static void writeTop10(List<Player> players) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (int i = 0; i < Math.min(10, players.size()); i++) {
                writer.write((i + 1) + " " + players.get(i).name + " " + players.get(i).score + "\n");
            }
        } catch (IOException e) {
            Logger.error(e);
        }
    }

    public static List<Player> getTop10() {
        List<Player> players = new ArrayList<>();

        try {
            Path filePath = Path.of(filename);
            if (!Files.exists(filePath)) {
                Files.createFile(filePath);
            }

            try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    // Split the line by whitespace
                    String[] parts = line.split("\\s+");

                    // Extract the values
                    int place = Integer.parseInt(parts[0]);
                    String playerNick = parts[1];
                    double playerScore = Double.parseDouble(parts[2]);
                    players.add(new Player(place, playerScore, playerNick));
                }
            } catch (IOException e) {
                Logger.error(e);
            }
        } catch (IOException e) {
            Logger.error(e);
        }
        return players;
    }

    public static class Player implements Comparable<Player> {
        double score;
        String name;
        int place;

        public Player(int place, double score, String name) {
            this.score = score;
            this.name = name;
        }

        @Override
        public int compareTo(Player o) {
            return Double.compare(o.score, score);
        }

        @Override
        public String toString() {
            return name + " s: " + score;
        }
    }
}
