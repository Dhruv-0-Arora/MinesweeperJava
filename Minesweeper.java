package Project2;

import java.util.Random;

public class Minesweeper {
    private final int size;
    private final int mines;
    private final char[][] board;
    private final boolean[][] mineLocations;
    private boolean[][] revealed;
    private boolean gameLost = false;

    public Minesweeper(int size, int mines) {
        this.size = size;
        this.mines = mines;
        board = new char[size][size];
        mineLocations = new boolean[size][size];
        revealed = new boolean[size][size];
        initializeBoard();
        placeMines();
        calculateHints();
    }

    private void initializeBoard() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                board[i][j] = '-';
                mineLocations[i][j] = false;
                revealed[i][j] = false;
            }
        }
    }

    private void placeMines() {
        Random random = new Random();
        int placedMines = 0;
        while (placedMines < mines) {
            int row = random.nextInt(size);
            int col = random.nextInt(size);
            if (!mineLocations[row][col]) {
                mineLocations[row][col] = true;
                placedMines++;
            }
        }
    }

    private void calculateHints() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (!mineLocations[i][j]) {
                    int count = countAdjacentMines(i, j);
                    if (count > 0) board[i][j] = (char) (count + '0');
                }
            }
        }
    }

    private int countAdjacentMines(int row, int col) {
        int count = 0;
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                int r = row + i, c = col + j;
                if (r >= 0 && r < size && c >= 0 && c < size && mineLocations[r][c]) {
                    count++;
                }
            }
        }
        return count;
    }

    public void reveal(int row, int col) {
        if (row < 0 || row >= size || col < 0 || col >= size || revealed[row][col]) return;
        revealed[row][col] = true;
        if (mineLocations[row][col]) {
            gameLost = true;
            return;
        }
        if (board[row][col] == '-') {
            board[row][col] = ' ';
            for (int i = -1; i <= 1; i++) {
                for (int j = -1; j <= 1; j++) {
                    reveal(row + i, col + j);
                }
            }
        }
    }

    public boolean isGameLost() {
        return gameLost;
    }

    public boolean isGameWon() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (!mineLocations[i][j] && !revealed[i][j]) return false;
            }
        }
        return true;
    }

    public char getCellDisplay(int row, int col) {
        return revealed[row][col] ? board[row][col] : '-';
    }

    public char getFullCellDisplay(int row, int col) {
        return mineLocations[row][col] ? '*' : board[row][col];
    }

    public int getSize() {
        return size;
    }
}
