Overview

This is a console-based implementation of the classic Minesweeper game in Java.
The program generates a board with hidden mines, and the player’s goal is to reveal all safe cells without hitting a mine.

Features

Random mine placement.

Board numbers show how many mines are nearby.

Actions: reveal, flag, and unflag cells.

Win when all safe cells are revealed.

Lose if a mine is revealed.

Class Details

Minesweeper: Main class that manages the board, mines, and gameplay logic.

Key methods include:

initializeBoard() – sets up the game board

placeMines() – randomly places mines

calculateNumbers() – calculates adjacent mine counts

displayBoard() – shows the current board

playerMove(row, col, action) – handles player actions

checkWin() and checkLoss() – check game status
