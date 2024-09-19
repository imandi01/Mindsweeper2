import java.util.Scanner;

public class Main {
    // Main method to start the game
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Create a Minesweeper game with specific dimensions and number of mines
        Minesweeper game = new Minesweeper(10, 10, 10);

        // Game loop
        while (!game.getGameOver()) {
            game.displayBoard();

            // Get player input for row, col, and action (reveal, flag, or unflag)
            System.out.println("Enter your move (format: row col action), where action is 'reveal', 'flag', or 'unflag':");

            int row = scanner.nextInt();
            int col = scanner.nextInt();
            String action = scanner.next();

            // Execute the player's move
            game.playerMove(row, col, action);

            // Check for win condition
            if (game.checkWin()) {
                System.out.println("Congratulations! You've won the game.");
                break;
            }

            // Check for loss condition
            if (game.checkLoss(row, col)) {
                System.out.println("Game Over! You hit a mine.");
                game.setGameOver(true);
            }
        }

        // Display final state of the board
        game.displayBoard();
    }
}