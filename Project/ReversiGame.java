package Project;

import java.util.Scanner;

public class ReversiGame {
	private Board board;
	private Scanner scanner;
	int depth = 6; // Adjust the depth based on desired difficulty

	public ReversiGame() {
		// Initialize the game
		board = new Board();
		scanner = new Scanner(System.in);
	}

	public void playGame() {

		System.out.println("Enter Game mode : 1 player vs AI | 2 player vs player  | 3 AI vs AI");
		System.out.println("Enter 1 or 2 or 3 for corresponding mode");

		int userInput = scanner.nextInt();
		if (userInput == 1) {
			// Initialize the game
			System.out.println("Game mode 1: player vs AI ");
			// Game loop
			while (!board.isGameOver() && !isGameTied()) {
				board.printBoard();
				char currentPlayer = board.getCurrentPlayer();
				System.out.println("Player " + currentPlayer + "'s turn");

				int row, col;
				if (currentPlayer == 'X') {
					do {
						System.out.print("Enter row (0-7): ");
						row = scanner.nextInt();

						System.out.print("Enter column (0-7): ");
						col = scanner.nextInt();

						if (!board.isValidMove(row, col, currentPlayer)) {
							System.out.println("Invalid move. Try again.");
						}
					} while (!board.isValidMove(row, col, currentPlayer));

					board.makeMove(row, col, currentPlayer);
					board.switchPlayer();

				} else {
					// AI player's turn
					makeAIMove(depth);
					board.switchPlayer();
				}
			}
			// Display final results
			board.printBoard();
			char winner = getWinner();
			if (winner == ' ') {
				System.out.println("It's a tie!");
			} else {
				System.out.println("Player " + winner + " wins!");
			}
			try {
				// delay
				Thread.sleep(6000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			resetGame();
		}

		else if (userInput == 2) {
			System.out.println("Game mode 2: player vs player");

			// Game loop
			while (!board.isGameOver() && !isGameTied()) {
				board.printBoard();
				char currentPlayer = board.getCurrentPlayer();
				System.out.println("Player " + currentPlayer + "'s turn");

				int row, col;
				do {
					System.out.print("Enter row (0-7): ");
					row = scanner.nextInt();

					System.out.print("Enter column (0-7): ");
					col = scanner.nextInt();

					if (!board.isValidMove(row, col, currentPlayer)) {
						System.out.println("Invalid move. Try again.");
					}
				} while (!board.isValidMove(row, col, currentPlayer));

				board.makeMove(row, col, currentPlayer);
				board.switchPlayer();
			}

			// Display final results
			board.printBoard();
			char winner = getWinner();
			if (winner == ' ') {
				System.out.println("It's a tie!");
			} else {
				System.out.println("Player " + winner + " wins!");
			}
			try {
				// delay
				Thread.sleep(6000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			resetGame();
		}

		else if (userInput == 3) {
			System.out.println("Game mode 3: AI vs AI");
			while (!board.isGameOver() && !isGameTied()) {
				board.printBoard();
				char currentPlayer = board.getCurrentPlayer();
				System.out.println("Player " + currentPlayer + "'s turn");

				if (currentPlayer == 'X') {
					makeAIMove(depth);
					board.switchPlayer();
					try {
						// delay
						Thread.sleep(10);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}

				} else if (currentPlayer == 'O') {
					makeAIMove(depth);
					board.switchPlayer();
					try {
						// delay
						Thread.sleep(10);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}

				}
			}
			board.printBoard();
			char winner = getWinner();
			if (winner == ' ') {
				System.out.println("It's a tie!");
			} else {
				System.out.println("Player " + winner + " wins!");
			}
			try {
				// delay
				Thread.sleep(6000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			resetGame();
		}
	}

	public void makeAIMove(int depth) {
		int[] bestMove = findBestMove(depth);
		int row = bestMove[0];
		int col = bestMove[1];
		char currentPlayer = board.getCurrentPlayer();
		if (currentPlayer == 'O') {
			board.makeMove(row, col, 'O');
		}

		else if (currentPlayer == 'X') {
			board.makeMove(row, col, 'X');
		}
	}

	private int[] findBestMove(int depth) {
		int maxScore = Integer.MIN_VALUE;
		int[] bestMove = { -1, -1 };
		char currentPlayer = board.getCurrentPlayer();

		if (currentPlayer == 'O') {
			for (int i = 0; i < board.getSize(); i++) {
				for (int j = 0; j < board.getSize(); j++) {
					if (board.isValidMove(i, j, 'O')) {
						Board newBoard = new Board(board);
						newBoard.makeMove(i, j, 'O');
						// Introduce randomness with a probability
						if (Math.random() < 0.2) {
							int score = evaluateBoard(newBoard, 'O');
							if (score > maxScore) {
								maxScore = score;
								bestMove[0] = i;
								bestMove[1] = j;
							}
						} else {
							// Use Minimax with alpha-beta pruning for the rest of the moves
							int score = minimax(newBoard, depth, Integer.MIN_VALUE, Integer.MAX_VALUE, 'X');

							if (score > maxScore) {
								maxScore = score;
								bestMove[0] = i;
								bestMove[1] = j;
							}
						}
					}
				}
			}
		} else if (currentPlayer == 'X') {
			for (int i = 0; i < board.getSize(); i++) {
				for (int j = 0; j < board.getSize(); j++) {
					if (board.isValidMove(i, j, 'X')) {
						Board newBoard = new Board(board);
						newBoard.makeMove(i, j, 'X');

						// Introduce randomness with a probability
						if (Math.random() < 0.2) {
							int score = evaluateBoard(newBoard, 'O');
							if (score > maxScore) {
								maxScore = score;
								bestMove[0] = i;
								bestMove[1] = j;
							}
						} else {

							int score = minimax(newBoard, depth, Integer.MIN_VALUE, Integer.MAX_VALUE, 'O');

							if (score > maxScore) {
								maxScore = score;
								bestMove[0] = i;
								bestMove[1] = j;
							}
						}
					}
				}
			}
		}
		return bestMove;
	}

	private int minimax(Board board, int depth, int alpha, int beta, char currentPlayer) {
		if (depth == 0 || board.isGameOver()) {
			return evaluateBoard(board, currentPlayer);
		}
		if (currentPlayer == 'X') {
			int maxScore = Integer.MIN_VALUE;
			for (int i = 0; i < board.getSize(); i++) {
				for (int j = 0; j < board.getSize(); j++) {
					if (board.isValidMove(i, j, currentPlayer)) {
						Board newBoard = new Board(board);
						newBoard.makeMove(i, j, currentPlayer);
						int score = minimax(newBoard, depth - 1, alpha, beta, 'O');
						maxScore = Math.max(maxScore, score);
						alpha = Math.max(alpha, score);
						if (beta <= alpha) {
							break; // Beta cutoff
						}
					}
				}
			}
			return maxScore;
		} else {
			int minScore = Integer.MAX_VALUE;
			for (int i = 0; i < board.getSize(); i++) {
				for (int j = 0; j < board.getSize(); j++) {
					if (board.isValidMove(i, j, currentPlayer)) {
						Board newBoard = new Board(board);
						newBoard.makeMove(i, j, currentPlayer);
						int score = minimax(newBoard, depth - 1, alpha, beta, 'X');
						minScore = Math.min(minScore, score);
						beta = Math.min(beta, score);
						if (beta <= alpha) {
							break; // Alpha cutoff
						}
					}
				}
			}
			return minScore;
		}
	}

	private int evaluateBoard(Board board, char currentPlayer) {
		int playerScore = 0;
		int opponentScore = 0;

		// Factors for evaluation
		int cornerWeight = 5;
		int edgeWeight = 2;

		for (int i = 0; i < board.getSize(); i++) {
			for (int j = 0; j < board.getSize(); j++) {
				char cell = board.getCell(i, j);

				if (cell == currentPlayer) {
					playerScore++;

					// Bonus for corners
					if ((i == 0 || i == board.getSize() - 1) && (j == 0 || j == board.getSize() - 1)) {
						playerScore += cornerWeight;
					}

					// Bonus for edges
					else if (i == 0 || i == board.getSize() - 1 || j == 0 || j == board.getSize() - 1) {
						playerScore += edgeWeight;
					}
				} else if (cell != ' ') {
					opponentScore++;

					// Penalty for opponent discs in corners
					if ((i == 0 || i == board.getSize() - 1) && (j == 0 || j == board.getSize() - 1)) {
						opponentScore += cornerWeight;
					}

					// Penalty for opponent discs on edges
					else if (i == 0 || i == board.getSize() - 1 || j == 0 || j == board.getSize() - 1) {
						opponentScore += edgeWeight;
					}
				}
			}
		}

		// You can add more factors to the evaluation, such as mobility, stability, etc.

		return playerScore - opponentScore;
	}

	public void resetGame() {
		board = new Board();
		ReversiGame game = new ReversiGame();
		game.playGame();
	}

	public char getWinner() {
		int[] score = board.getScore();
		if (score[0] > score[1]) {
			return 'X';
		} else if (score[0] < score[1]) {
			return 'O';
		} else {
			return ' '; // Game is tied
		}
	}

	public boolean isGameTied() {
		return board.isBoardFull() && !board.hasValidMoves('X') && !board.hasValidMoves('O');
	}

	public static void main(String[] args) {
		ReversiGame game = new ReversiGame();
		game.playGame();
	}
}
