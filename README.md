# Reversi Game

Reversi game implementation in Java.

This project was developed as part of a design of algorithms course at university.

The game features three gameplay modes:
- Player vs Player
- Player vs AI
- AI vs AI

## Board Class

In the `Board` class, I implemented the core of the game – the game board. It includes methods for tasks such as:
- Initializing the board
- Printing its current state
- Handling player moves
- Evaluating the game's outcome

The class uses an 8x8 grid to visually represent the game board. It incorporates crucial game mechanics like move validation, disc flipping, scoring, and checking for game completion.

## ReversiGame Class

The `ReversiGame` class acts as the game manager, overseeing interactions between players and artificial intelligence. It seamlessly integrates the functionalities of the `Board` class and implements the Minimax algorithm for AI decision-making.

### Game Modes

The game supports three modes:
1. **Player vs. AI:** Players take turns with the AI, allowing human players to input their moves.
2. **Player vs. Player:** Two human players take turns making moves.
3. **AI vs. AI:** The AI players play against each other, showcasing the AI's decision-making abilities. A short delay between moves provides clarity in observing the game.

My focus was on providing a thoughtful implementation of the Minimax algorithm, coupled with an evaluation function, to enable strategic decision-making for the AI player.

The Minimax algorithm's application for AI decision-making underscored my understanding of game tree exploration and optimization techniques, including the clever use of alpha-beta pruning.

# AI VS AI test

![ai vs ai 3](https://github.com/OmarHajjeh/Reversi_game/assets/134964735/66ac5933-5831-4f1b-9df2-deba921c31ee)

