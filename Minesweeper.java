import java.util.Random;

public class Minesweeper {
    private final int size; // Size of the board
    private final int mines; // Number of mines
    private final char[][] board; // Board to display
    private final boolean[][] mineLocations; // Locations of mines
    private final boolean[][] revealed; // Revealed cells
    private boolean gameLost = false; // Flag to check if the game is lost

    /**
     * Constructor for Minesweeper class
     * 
     * @param size
     * @param mines
     */
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

    /** 
     * Initializes the board with empty cells
     */
    private void initializeBoard() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                board[i][j] = '-';
                mineLocations[i][j] = false;
                revealed[i][j] = false;
            }
        }
    }

    /** 
     * Places mines randomly on the board
     */
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

    /** 
     * Calculates the hints for each cell 
     */
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

    /** 
     * Counts the number of adjacent mines for a given cell 
     */
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

    /** 
     * Reveals the cell at the given row and column
     */
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

    /**
     * Checks if the cell at the given row and column is revealed or not
     */
    public boolean isRevealed(int row, int col) {
        return revealed[row][col];
    }

    /** 
     * Checks if the game is lost or not
     */
    public boolean isGameLost() {
        return gameLost;
    }

    /** 
     * Checks if the game is won or not
     */
    public boolean isGameWon() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (!mineLocations[i][j] && !revealed[i][j]) return false;
            }
        }
        return true;
    }

    /**
     * Returns the cell display, '-' if not revealed, or the cell value if revealed.
     */
    public char getCellDisplay(int row, int col) {
        return revealed[row][col] ? board[row][col] : '-';
    }

    /** 
     * Returns the full cell display, including mines.
     * '-' if not next to a number
     */
    public char getFullCellDisplay(int row, int col) {
        return mineLocations[row][col] ? '*' : board[row][col];
    }

    // Getter method for size
    public int getSize() {
        return size;
    }
}
