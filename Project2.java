import java.util.Scanner;

public class Project2 {

    /** 
     * Main method to initialize the board and start the game by running the recursive function
     */
    public static void main(String[] args) {
        Minesweeper game = new Minesweeper(5, 5);
        recursiveGameLoop(game, new Scanner(System.in));
    }

    /** 
     * Recursively plays the game until the game is won or lost.
     */
    private static void recursiveGameLoop(Minesweeper game, Scanner scanner) {
        if (game.isGameLost()) { // Base case if the game is lost
            System.out.println("Game Over! You hit a mine.");
            printFullBoard(game);
            return;
        }

        // Base case if the game is won
        if (game.isGameWon()) {
            System.out.println("Congratulations! You've won.");
            printFullBoard(game);
            return;
        }

        /** Recursive case */

        // Print the board and ask for user input
        printBoard(game);
        System.out.print("Enter row and column (e.g., 1 2): ");
        int row = scanner.nextInt();
        int col = scanner.nextInt();

        // Check if the input is valid
        if (isValidInput(row, col, game)) {
            game.reveal(row, col);
        }

        // Recursively call the function
        recursiveGameLoop(game, scanner);
    }

    /** 
     * Checks if the input is valid or not
     * If not, prints an error message
     */
    private static boolean isValidInput(int row, int col, Minesweeper game) {
        if (row < 0 || row >= game.getSize() || col < 0 || col >= game.getSize()) {
            System.out.println("Invalid input. Please try again.");
            return false;
        } else if (game.isRevealed(row, col)) { // if the input has already been revealed
            System.out.println("Cell has already been revealed. Please try again.");
            return false;
        }
        return true; 
    }

    /** 
     * Prints the board
     */
    private static void printBoard(Minesweeper game) {
        System.out.print("  ");
        for (int i = 0; i < game.getSize(); i++) System.out.print(i + " ");
        System.out.println();
        for (int i = 0; i < game.getSize(); i++) {
            System.out.print(i + " ");
            for (int j = 0; j < game.getSize(); j++) {
                System.out.print(game.getCellDisplay(i, j) + " ");
            }
            System.out.println();
        }
    }

    /** 
     * Prints the full board with all the mines unhidden
     */
    private static void printFullBoard(Minesweeper game) {
        System.out.print("  ");
        for (int i = 0; i < game.getSize(); i++) System.out.print(i + " ");
        System.out.println();
        for (int i = 0; i < game.getSize(); i++) {
            System.out.print(i + " ");
            for (int j = 0; j < game.getSize(); j++) {
                System.out.print(game.getFullCellDisplay(i, j) + " ");
            }
            System.out.println();
        }
    }
}
