package src;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

public class Maze {
    private char[][] maze;
    private int rows, cols;
    private int startX, startY;
    private int endX, endY;

    public Maze(String filename) {
        loadMaze(filename);
    }

    private void loadMaze(String filename) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            List<String> lines = new ArrayList<>();
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }
            rows = lines.size();
            cols = lines.get(0).length();
            maze = new char[rows][cols];

            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    maze[i][j] = lines.get(i).charAt(j);
                    if (maze[i][j] == 'S') {
                        startX = i;
                        startY = j;
                    } else if (maze[i][j] == 'E') {
                        endX = i;
                        endY = j;
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Erreur lors du chargement du fichier : " + e.getMessage());
        }
    }

    public char[][] getMaze() { return maze; }
    public int getStartX() { return startX; }
    public int getStartY() { return startY; }
    public int getEndX() { return endX; }
    public int getEndY() { return endY; }
    public int getRows() { return rows; }
    public int getCols() { return cols; }
}