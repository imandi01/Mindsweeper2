import java.util.Random;

public class Minesweeper {

    // Data members
    private char[][] board;   // The game board where cells will be displayed
    private boolean[][] mines; // Array to track the locations of mines
    private boolean[][] revealed; // Array to track which cells have been revealed
    private int rows; // Number of rows in the board
    private int cols; // Number of columns in the board
    private int numMines; // Number of mines in the game
    private boolean gameOver; // Boolean to check if the game is over

    // Constructor to initialize the board with the specified dimensions and number of mines
    public Minesweeper(int rows, int cols, int numMines) {
        this.rows = rows;
        this.cols = cols;
        this.numMines = numMines;
        this.board = new char[rows][cols];
        this.mines = new boolean[rows][cols];
        this.revealed = new boolean[rows][cols];
        this.gameOver = false;

        initializeBoard();
        placeMines();
        calculateNumbers();

        // Call methods to initialize the board and place mines
    }

    public boolean getGameOver() {
        return this.gameOver;
    }

    public void setGameOver(boolean status) {
        this.gameOver = status;

    }

    // Method to initialize the game board with empty values
    private void initializeBoard() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                board[i][j] = '-'; // Initialize all cells as unrevealed
                revealed[i][j] = false; // No cell is revealed initially
            }
        }
    }

    // Method to randomly place mines on the board
    private void placeMines() {
        Random rand = new Random();
        int minesPlaced = 0;
        while (minesPlaced < numMines) {
            int row = rand.nextInt(rows);
            int col = rand.nextInt(cols);

            if (!mines[row][col]) { // If there's no mine already at this position
                mines[row][col] = true;
                minesPlaced++;
            }
        }
    }

    // Method to calculate numbers on the board for non-mine cells
    private void calculateNumbers() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (!mines[i][j]) {
                    int adjacentMines = countAdjacentMines(i, j);
                    if (adjacentMines > 0) {
                        board[i][j] = (char) (adjacentMines + '0'); // Store as a number char
                    }
                }
            }
        }
    }

    private int countAdjacentMines(int row, int col) {
        int count = 0;
        for (int i = row - 1; i <= row + 1; i++) {
            for (int j = col - 1; j <= col + 1; j++) {
                if (i >= 0 && i < rows && j >= 0 && j < cols && mines[i][j]) {
                    count++;
                }
            }
        }
        return count;
    }

    // Method to display the current state of the board
    public void displayBoard() {
        System.out.println("Current Board:");
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (revealed[i][j]) {
                    System.out.print(board[i][j] + " ");
                } else {
                    System.out.print("- ");
                }
            }
            System.out.println();
        }
    }

    // Method to handle a player's move (reveal a cell or place a flag)
    public void playerMove(int row, int col, String action) {
        if (row < 0 || row >= rows || col < 0 || col >= cols) {
            System.out.println("Invalid move. Please try again.");
            return;
        }

        if (action.equals("reveal")) {
            if (!revealed[row][col]) {
                revealCell(row, col);
            } else {
                System.out.println("Cell already revealed.");
            }
        } else if (action.equals("flag")) {
            flagCell(row, col);
        } else if (action.equals("unflag")) {
            unflagCell(row, col);
        }

        if (checkWin()) {
            System.out.println("Congratulations, you've won the game!");
            gameOver = true;
        } else if (checkLoss(row, col)) {
            System.out.println("Game over! You hit a mine.");
            gameOver = true;
        }
    }

    // Method to check if the player has won the game
    public boolean checkWin() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (!mines[i][j] && !revealed[i][j]) {
                    return false; // Unrevealed non-mine cell found
                }
            }
        }
        return true;
    }

    // Method to check if the player has lost the game
    public boolean checkLoss(int row, int col) {
        return mines[row][col];
    }

    // Method to reveal a cell (and adjacent cells if necessary)
    private void revealCell(int row, int col) {
        if (row < 0 || row >= rows || col < 0 || col >= cols || revealed[row][col]) {
            return; // Invalid cell or already revealed
        }

        revealed[row][col] = true;

        if (mines[row][col]) {
            board[row][col] = '*'; // Mark mine on the board
        } else if (board[row][col] == '-') { // No adjacent mines, reveal surrounding cells
            for (int i = row - 1; i <= row + 1; i++) {
                for (int j = col - 1; j <= col + 1; j++) {
                    revealCell(i, j);
                }
            }
        }
    }

    // Method to flag a cell as containing a mine
    private void flagCell(int row, int col) {

        if (!revealed[row][col]) {
            board[row][col] = 'F'; // Flag the cell
            revealed[row][col] = true;
        }
    }

    // Method to unflag a cell
    private void unflagCell(int row, int col) {
        if (board[row][col] == 'F') {
            board[row][col] = '-'; // Reset the cell
            revealed[row][col] = false;
        }
    }

}