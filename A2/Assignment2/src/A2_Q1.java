import java.util.*;

public class A2_Q1 {

	private static final int ROWS = 5;
	private static final int COLS = 9;
	private static final int[][] DIRECTIONS = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

	public static int[] game(String[][] board) {
		int minBalls = Integer.MAX_VALUE;
		int minMoves = Integer.MAX_VALUE;
		int ballCount = 0;

		//checking cell at board[i][j]
		for (int i = 0; i < ROWS; i++) {
			for (int j = 0; j < COLS; j++) {
				if (board[i][j].equals("o")) {    //exists a ball
					ballCount++;
				}
			}
		}
		return backtracking(board, ballCount, 0, minBalls, minMoves);
	}

	public static int[] backtracking(String[][] board, int ballsLeft, int moves, int minBalls, int minMoves) {
		//Base case
		if (ballsLeft == 1) {        //final leaf
			return new int[]{minBalls, minMoves};
		}

		//Comparing current solution with best solution
		if (ballsLeft < minBalls || (ballsLeft == minBalls && moves < minMoves)) {
			minBalls = ballsLeft;
			minMoves = moves;
		}

		//Iterating through the cells
		for (int i = 0; i < ROWS; i++) {
			for (int j = 0; j < COLS; j++) {
				if (board[i][j].equals("o")) {
					for (int[] dir : DIRECTIONS) {
						int x = i + dir[0];
						int y = j + dir[1];
						int newX = x + dir[0];
						int newY = y + dir[1];

						if (isValidMove(board, i, j, x, y, newX, newY)) {
							board[i][j] = ".";
							board[x][y] = ".";
							board[newX][newY] = "o";

							int[] newResult = backtracking(board, ballsLeft - 1, moves + 1, minBalls, minMoves);
							minBalls = newResult[0];
							minMoves = newResult[1];

							board[i][j] = "o";
							board[x][y] = "o";
							board[newX][newY] = ".";
						}
					}
				}
			}
		}

		return new int[]{minBalls, minMoves};
	}

	private static boolean isValidMove(String[][] board, int i, int j, int x, int y, int newX, int newY) {
			return x >= 0 && x < ROWS && y >= 0 && y < COLS &&
					newX >= 0 && newX < ROWS && newY >= 0 && newY < COLS &&
					board[x][y].equals("o") && board[newX][newY].equals(".");
		}
}


