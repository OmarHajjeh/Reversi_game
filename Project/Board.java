package Project;

public class Board {
	private char[][] grid; // 'X' for player 1, 'O' for player 2, ' ' for empty
	private char currentTurn = 'X'; // or 'O' depending on who starts first

	public Board() {
		// Initialize the board with an 8x8 grid
		grid = new char[8][8];

		// Set up the initial position
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				grid[i][j] = ' '; // Initialize all cells as empty
			}
		}
		// Place the central discs
		grid[3][3] = 'X';
		grid[3][4] = 'O';
		grid[4][3] = 'O';
		grid[4][4] = 'X';
	}

	public void printBoard() {
		System.out.println("  0 1 2 3 4 5 6 7");
		System.out.println(" +-+-+-+-+-+-+-+-");

		for (int i = 0; i < grid.length; i++) {
			System.out.print(i + "|");
			for (int j = 0; j < grid[i].length; j++) {
				System.out.print(grid[i][j] + "|");
			}
			System.out.println("\n +-+-+-+-+-+-+-+-");
		}
	}

	public Board(Board other) {
		this.grid = new char[other.grid.length][other.grid[0].length];
		for (int i = 0; i < other.grid.length; i++) {
			for (int j = 0; j < other.grid[i].length; j++) {
				this.grid[i][j] = other.grid[i][j];
			}
		}
	}

	public boolean isValidMove(int row, int col, char player) {
		// Check if the specified position is within the board boundaries
		if (row < 0 || row >= grid.length || col < 0 || col >= grid[row].length) {
			return false;
		}

		// Check if the cell is empty
		if (grid[row][col] != ' ') {
			return false;
		}

		// Check if the move results in flipping opponent's discs
		for (int i = -1; i <= 1; i++) {
			for (int j = -1; j <= 1; j++) {
				if (i != 0 || j != 0) {
					if (isValidDirection(row, col, player, i, j)) {
						return true;
					}
				}
			}
		}

		return false;
	}

	private boolean isValidDirection(int row, int col, char player, int deltaRow, int deltaCol) {
		int opponentRow = row + deltaRow;
		int opponentCol = col + deltaCol;

		// Check if the opponent's disc is present in the specified direction
		if (opponentRow >= 0 && opponentRow < grid.length && opponentCol >= 0 && opponentCol < grid[opponentRow].length
				&& grid[opponentRow][opponentCol] != player && grid[opponentRow][opponentCol] != ' ') {

			// Check if there is a sequence of opponent's discs followed by the player's
			// disc
			while (opponentRow >= 0 && opponentRow < grid.length && opponentCol >= 0
					&& opponentCol < grid[opponentRow].length && grid[opponentRow][opponentCol] != ' '
					&& grid[opponentRow][opponentCol] != player) {

				opponentRow += deltaRow;
				opponentCol += deltaCol;
			}

			// If the sequence ends with the player's disc, the move is valid
			if (opponentRow >= 0 && opponentRow < grid.length && opponentCol >= 0
					&& opponentCol < grid[opponentRow].length && grid[opponentRow][opponentCol] == player) {

				return true;
			}
		}

		return false;
	}

	public void makeMove(int row, int col, char player) {
		if (!isValidMove(row, col, player)) {
			System.out.println("Invalid move. Try again.");
			return;
		}

		grid[row][col] = player;

		// Flip opponent discs in all valid directions
		for (int i = -1; i <= 1; i++) {
			for (int j = -1; j <= 1; j++) {
				if (i != 0 || j != 0) {
					flipDirection(row, col, player, i, j);
				}
			}
		}
	}

	private void flipDirection(int row, int col, char player, int deltaRow, int deltaCol) {
		// Iterate in both directions along the specified axis
		for (int direction = -1; direction <= 1; direction += 2) {
			int currentRow = row + deltaRow * direction;
			int currentCol = col + deltaCol * direction;

			// Check if the current cell is within the board boundaries
			while (currentRow >= 0 && currentRow < grid.length && currentCol >= 0
					&& currentCol < grid[currentRow].length) {

				// Check if the current cell contains the player's disc
				if (grid[currentRow][currentCol] == player) {
					// Flip opponent discs in the sequence
					int flipRow = row + deltaRow * direction;
					int flipCol = col + deltaCol * direction;

					while (flipRow != currentRow || flipCol != currentCol) {
						// Check if the current cell is not empty before flipping
						if (grid[flipRow][flipCol] != ' ') {
							grid[flipRow][flipCol] = player;
						}

						flipRow += deltaRow * direction;
						flipCol += deltaCol * direction;
					}

					// Break from the loop after flipping opponent discs
					break;
				}

				// Move to the next cell in the specified direction
				currentRow += deltaRow * direction;
				currentCol += deltaCol * direction;
			}
		}
	}

	public boolean isGameOver() {
		return !hasValidMoves('X') && !hasValidMoves('O');
	}

	public boolean hasValidMoves(char player) {
		// Check if the player has any valid moves
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) {
				if (isValidMove(i, j, player)) {
					return true;
				}
			}
		}
		return false;
	}

	public int[] getScore() {
		int[] score = new int[2];
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) {
				if (grid[i][j] == 'X') {
					score[0]++;
				} else if (grid[i][j] == 'O') {
					score[1]++;
				}
			}
		}
		System.out.println("X score :" + score[0]);
		System.out.println("O score :" + score[1]);
		return score;
	}

	public char getCurrentPlayer() {
		return currentTurn;
	}

	public void switchPlayer() {
		currentTurn = (currentTurn == 'X') ? 'O' : 'X';	
		System.out.println("  ");
	}

	public int getSize() {
		return grid.length;
	}

	public char getCell(int row, int col) {
		if (row >= 0 && row < grid.length && col >= 0 && col < grid[row].length) {
			return grid[row][col];
		} else {
			throw new IllegalArgumentException("Invalid row or column index");
		}
	}

	public boolean isBoardFull() {
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) {
				if (grid[i][j] == ' ') {
					return false; // There is an empty cell, the board is not full
				}
			}
		}
		return true; // All cells are occupied
	}

}
