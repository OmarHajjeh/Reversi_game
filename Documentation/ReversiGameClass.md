# ReversiGame Class Documentation

## Overview

The `ReversiGame` class orchestrates the Reversi (Othello) game, managing player moves, AI moves, and the overall flow of the game. It utilizes the `Board` class to represent the game board and employs the Minimax algorithm for AI decision-making. This class supports three game modes: player vs. AI, player vs. player, and AI vs. AI.

## Class Structure

### Fields

- `private Board board`: Represents the game board, providing methods for board manipulation and state assessment.
- `private Scanner scanner`: Facilitates user input for player moves.
- `int depth`: The depth parameter for the Minimax algorithm, influencing the AI player's decision-making.

## Constructors

- `public ReversiGame()`: Initializes the game by creating a new instance of the `Board` and sets up the scanner for user input.

## Methods

### Game Initialization and Flow

- `public void playGame()`: Initiates and controls the game loop, allowing players to make moves and handling AI moves based on user input for game mode selection.
- `public void resetGame()`: Resets the game by creating a new instance of the `Board`. Useful for starting a new game.

### Player and AI Moves

- `public void makeAIMove(int depth)`: Initiates the AI player's move by finding the best move using the Minimax algorithm with a specified depth.
- `private int[] findBestMove(int depth)`: Finds the best move for the AI player using the Minimax algorithm and returns the chosen move coordinates.

### Minimax Algorithm

- `private int minimax(Board board, int depth, int alpha, int beta, char currentPlayer)`: Implements the Minimax algorithm, a recursive algorithm for searching through the game tree and selecting the best move for the AI player. The alpha-beta pruning technique is applied for optimization.

### Evaluation Function

- `private int evaluateBoard(Board board, char currentPlayer)`: Evaluates the game board based on various factors, including disc count, corners, and edges. This function is crucial for the Minimax algorithm to assess the board state and make informed decisions.

### Winner and Tie Detection

- `public char getWinner()`: Determines the winner based on the final score, returning 'X' for player 1, 'O' for player 2, or ' ' if the game is tied.
- `public boolean isGameTied()`: Checks if the game ended in a tie by verifying if the board is full and neither player has valid moves.

# In-Depth Explanation

### Minimax Algorithm and Evaluation Function

The core of the AI decision-making process lies in the Minimax algorithm. This algorithm explores possible moves recursively and assigns a score to each board state. The `minimax` method takes into account the current player, depth of recursion, and the alpha-beta pruning technique to optimize the search process. The `evaluateBoard` function assigns scores to different board states based on factors such as disc count, corners, and edges. Developers can adjust the depth parameter in `makeAIMove` to control the AI's difficulty level.

### Game Modes

The `playGame` method allows users to choose between three game modes:

- **Player vs. AI (Mode 1):** Players take turns with the AI, allowing human players to input their moves.
- **Player vs. Player (Mode 2):** Two human players take turns making moves.
- **AI vs. AI (Mode 3):** The AI players play against each other, showcasing the AI's decision-making abilities. A short delay between moves provides clarity in observing the game.

### User Input and Output

The `Scanner` class enables user input for row and column selection during player moves. The game state is displayed after each move, providing clarity on the current player and the board's status. The game concludes by displaying the final board state and announcing the winner or a tie.

### Game Reset

The `resetGame` method allows for starting a new game without restarting the entire application. It creates a fresh instance of the `Board` class, resetting the game state.


## Example Usage

```java
public static void main(String[] args) {
    ReversiGame game = new ReversiGame();
    game.playGame();
}



