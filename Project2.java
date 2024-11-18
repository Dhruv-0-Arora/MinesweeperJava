package Project2;

import java.util.Scanner;

public class Project2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Minesweeper game = new Minesweeper(5, 5);
        
        while (!game.isGameLost() && !game.isGameWon()) {
            printBoard(game);
            System.out.print("Enter row and column: ");
            int row = scanner.nextInt();
            int col = scanner.nextInt();
            if (row >= 0 && row < game.getSize() && col >= 0 && col < game.getSize()) {
                game.reveal(row, col);
                if (game.isGameLost()) {
                    System.out.println("Game Over! You hit a mine.");
                    printFullBoard(game);
                }
            }
        }
        if (!game.isGameLost()) System.out.println("Congratulations! You've won.");
        scanner.close();
    }

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
