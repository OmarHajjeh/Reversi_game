# Board Class Documentation

## Overview

The `Board` class represents the game board for a two-player Reversi (Othello) game. It provides methods to initialize the board, make moves, check for valid moves, and determine the game's outcome. The board is an 8x8 grid where players take turns placing their discs ('X' or 'O') on empty cells. The game involves flipping the opponent's discs when a player places their disc in a way that surrounds the opponent's discs.

## Class Structure

### Fields

- `private char[][] grid`: A 2D array representing the game board. 'X' for player 1, 'O' for player 2, and ' ' for empty cells.
- `private char currentTurn`: Represents the current player ('X' or 'O').

### Constructors

- `public Board()`: Initializes the board with an 8x8 grid and sets up the initial position with central discs.
- `public Board(Board other)`: Copy constructor that creates a new board identical to the provided board.

## Methods

### Board Manipulation

- `public void printBoard()`: Prints the current state of the board.
- `public void makeMove(int row, int col, char player)`: Attempts to make a move for the specified player at the given row and column.
- `private void flipDirection(int row, int col, char player, int deltaRow, int deltaCol)`: Helper method to flip opponent discs in a specific direction after a move.

### Move Validation

- `public boolean isValidMove(int row, int col, char player)`: Checks if a move at the specified position is valid for the given player.
- `private boolean isValidDirection(int row, int col, char player, int deltaRow, int deltaCol)`: Checks if a move in a specific direction results in flipping opponent discs.

### Game State

- `public boolean isGameOver()`: Checks if the game is over.
- `public boolean hasValidMoves(char player)`: Checks if the specified player has any valid moves.
- `public int[] getScore()`: Calculates and returns the current score for both players.

### Player and Board Information

- `public char getCurrentPlayer()`: Gets the current player.
- `public void switchPlayer()`: Switches the current player.
- `public int getSize()`: Gets the size of the board (8 in this case).
- `public char getCell(int row, int col)`: Gets the disc at the specified row and column.
- `public boolean isBoardFull()`: Checks if the board is completely filled.

## Example Usage

```java
public static void main(String[] args) {
    // Create a new board
    Board board = new Board();

    // Print the initial state of the board
    board.printBoard();

    // Make a move for player 'X'
    board.makeMove(2, 3, 'X');

    // Print the updated board
    board.printBoard();

    // Check if the game is over
    if (board.isGameOver()) {
        System.out.println("Game over!");
    } else {
        // Switch to the next player
        board.switchPlayer();
    }
}
