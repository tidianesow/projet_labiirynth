package src;

import java.util.*;

public class MazeSolver {
    private static final int[][] DIRECTIONS = { {0, 1}, {1, 0}, {0, -1}, {-1, 0} };

    public static List<int[]> solveDFS(Maze maze) {
        char[][] grid = maze.getMaze();
        int rows = maze.getRows(), cols = maze.getCols();
        boolean[][] visited = new boolean[rows][cols];
        Stack<int[]> stack = new Stack<>();
        Map<int[], int[]> parent = new HashMap<>();

        int[] start = {maze.getStartX(), maze.getStartY()};
        stack.push(start);
        visited[start[0]][start[1]] = true;

        while (!stack.isEmpty()) {
            int[] current = stack.pop();
            int x = current[0], y = current[1];

            if (x == maze.getEndX() && y == maze.getEndY()) {
                return buildPath(parent, start, current);
            }

            for (int[] dir : DIRECTIONS) {
                int newX = x + dir[0], newY = y + dir[1];
                if (isValidMove(newX, newY, grid, visited, rows, cols)) {
                    int[] next = {newX, newY};
                    stack.push(next);
                    visited[newX][newY] = true;
                    parent.put(next, current);
                }
            }
        }
        return null;
    }

    public static List<int[]> solveBFS(Maze maze) {
        char[][] grid = maze.getMaze();
        int rows = maze.getRows(), cols = maze.getCols();
        boolean[][] visited = new boolean[rows][cols];
        Queue<int[]> queue = new LinkedList<>();
        Map<int[], int[]> parent = new HashMap<>();

        int[] start = {maze.getStartX(), maze.getStartY()};
        queue.add(start);
        visited[start[0]][start[1]] = true;

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int x = current[0], y = current[1];

            if (x == maze.getEndX() && y == maze.getEndY()) {
                return buildPath(parent, start, current);
            }

            for (int[] dir : DIRECTIONS) {
                int newX = x + dir[0], newY = y + dir[1];
                if (isValidMove(newX, newY, grid, visited, rows, cols)) {
                    int[] next = {newX, newY};
                    queue.add(next);
                    visited[newX][newY] = true;
                    parent.put(next, current);
                }
            }
        }
        return null;
    }

    private static boolean isValidMove(int x, int y, char[][] grid, boolean[][] visited, int rows, int cols) {
        return x >= 0 && x < rows && y >= 0 && y < cols && !visited[x][y] && (grid[x][y] == '=' || grid[x][y] == 'E');
    }

    private static List<int[]> buildPath(Map<int[], int[]> parent, int[] start, int[] end) {
        List<int[]> path = new ArrayList<>();
        int[] current = end;
        while (!Arrays.equals(current, start)) {
            path.add(current);
            current = parent.get(current);
        }
        path.add(start);
        Collections.reverse(path);
        return path;
    }
}