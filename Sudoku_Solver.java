import java.util.Scanner;

public class SudokuSolver {
    private static final int SIZE = 9;
    private static final int EMPTY = 0;

    // The Sudoku puzzle using backtracking
    public static boolean solveSudoku(int[][] grid) {
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                if (grid[row][col] == EMPTY) {
                    for (int num = 1; num <= SIZE; num++) {
                        if (isValid(grid, row, col, num)) {
                            grid[row][col] = num;
                            if (solveSudoku(grid)) {
                                return true;
                            }
                            grid[row][col] = EMPTY; // Backtrack
                        }
                    }
                    return false; // No valid number found
                }
            }
        }
        return true; // Solved
    }

    // Checks if placing num in grid[row][col] is valid
    private static boolean isValid(int[][] grid, int row, int col, int num) {
        for (int i = 0; i < SIZE; i++) {
            if (grid[row][i] == num || grid[i][col] == num) {
                return false;
            }
        }
        int boxRow = row - row % 3;
        int boxCol = col - col % 3;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (grid[boxRow + i][boxCol + j] == num) {
                    return false;
                }
            }
        }
        return true;
    }

    // Prints the Sudoku grid
    public static void printGrid(int[][] grid) {
        for (int[] row : grid) {
            for (int num : row) {
                System.out.print(num + " ");
            }
            System.out.println();
        }
    }

    // Reads Sudoku grid from user input
    public static void readGrid(int[][] grid) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the Sudoku puzzle (use 0 for empty cells):");
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                grid[i][j] = scanner.nextInt();
            }
        }
        scanner.close();
    }

    // Main method for testing
    public static void main(String[] args) {
        int[][] grid = new int[SIZE][SIZE];
        readGrid(grid);

        if (solveSudoku(grid)) {
            System.out.println("Solved Sudoku:");
            printGrid(grid);
        } else {
            System.out.println("No solution exists.");
        }
    }
}
