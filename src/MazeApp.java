package src;

import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.List;

public class MazeApp extends Application {
    private static final int CELL_SIZE = 40;
    private Maze maze;
    private Pane mazePane;
    private Label dfsLabel, bfsLabel;

    @Override
    public void start(Stage primaryStage) {
        maze = new Maze("maze.txt"); // Chemin relatif depuis src/

        BorderPane root = new BorderPane();
        root.setPadding(new Insets(10));

        mazePane = new Pane();
        drawMaze();

        HBox controlPane = new HBox(20);
        controlPane.setPadding(new Insets(10));
        Button dfsButton = new Button("Résoudre avec DFS");
        Button bfsButton = new Button("Résoudre avec BFS");
        dfsLabel = new Label("DFS: -");
        bfsLabel = new Label("BFS: -");
        controlPane.getChildren().addAll(dfsButton, bfsButton, dfsLabel, bfsLabel);

        dfsButton.setOnAction(e -> solveAndAnimate(MazeSolver::solveDFS, dfsLabel, "DFS"));
        bfsButton.setOnAction(e -> solveAndAnimate(MazeSolver::solveBFS, bfsLabel, "BFS"));

        root.setCenter(mazePane);
        root.setBottom(controlPane);

        Scene scene = new Scene(root, maze.getCols() * CELL_SIZE + 40, maze.getRows() * CELL_SIZE + 100);
        primaryStage.setTitle("Résolution de Labyrinthe");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void drawMaze() {
        mazePane.getChildren().clear();
        char[][] grid = maze.getMaze();
        for (int i = 0; i < maze.getRows(); i++) {
            for (int j = 0; j < maze.getCols(); j++) {
                Rectangle cell = new Rectangle(j * CELL_SIZE, i * CELL_SIZE, CELL_SIZE, CELL_SIZE);
                switch (grid[i][j]) {
                    case '#': cell.setFill(Color.BLACK); break;
                    case 'S': cell.setFill(Color.GREEN); break;
                    case 'E': cell.setFill(Color.RED); break;
                    default: cell.setFill(Color.WHITE); break;
                }
                cell.setStroke(Color.GRAY);
                mazePane.getChildren().add(cell);
            }
        }
    }

    private void solveAndAnimate(java.util.function.Function<Maze, List<int[]>> solver, Label resultLabel, String method) {
        drawMaze();
        long startTime = System.nanoTime();
        List<int[]> path = solver.apply(maze);
        long endTime = System.nanoTime();

        if (path != null) {
            double timeMs = (endTime - startTime) / 1000000.0;
            resultLabel.setText(method + ": " + path.size() + " étapes, " + String.format("%.2f", timeMs) + " ms");

            for (int i = 0; i < path.size(); i++) {
                int[] pos = path.get(i);
                if (maze.getMaze()[pos[0]][pos[1]] != 'S' && maze.getMaze()[pos[0]][pos[1]] != 'E') {
                    PauseTransition pause = new PauseTransition(Duration.millis(200 * i));
                    int finalI = i;
                    pause.setOnFinished(e -> {
                        Rectangle cell = new Rectangle(pos[1] * CELL_SIZE, pos[0] * CELL_SIZE, CELL_SIZE, CELL_SIZE);
                        cell.setFill(Color.BLUE);
                        cell.setStroke(Color.GRAY);
                        mazePane.getChildren().add(cell);
                    });
                    pause.play();
                }
            }
        } else {
            resultLabel.setText(method + ": Aucune solution");
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}