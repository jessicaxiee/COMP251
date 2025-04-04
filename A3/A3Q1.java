import java.util.*;

public class A3Q1 {
	public static int[] saving_frogs(String[][] board) {
		int ROWS = board.length;
		int COLS = board[0].length;
		int[][] DIRECTIONS = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
		boolean[][] visited = new boolean[ROWS][COLS];
		int unreachedFood = 0;
		int minFrogs = 0;

		// Find entrances
		List<int[]> entrances = new ArrayList<>();

		for (int i = 0; i < ROWS; i++) {
			if (foundEntrance(board[i][0])) {
				entrances.add(new int[]{i, 0});
			}
			if (foundEntrance(board[i][COLS - 1])) {
				entrances.add(new int[]{i, COLS - 1});
			}
		}
		for (int j = 0; j < COLS; j++) {
			if (foundEntrance(board[0][j])) {
				entrances.add(new int[]{0, j});
			}
			if (foundEntrance(board[ROWS - 1][j])) {
				entrances.add(new int[]{ROWS - 1, j});  // fixed this line too
			}
		}

		// BFS for each entrance
		for (int[] position : entrances) {
			int row = position[0];
			int col = position[1];

			if (visited[row][col]) {
				continue;
			}

			boolean found = false;
			Queue<int[]> queue = new LinkedList<>();
			queue.add(new int[]{row, col});
			visited[row][col] = true;

			while (!queue.isEmpty()) {
				int[] current = queue.poll();
				int x = current[0];
				int y = current[1];
				if (board[x][y].equals(".")) {
					found = true;
				}
				for (int[] dir : DIRECTIONS) {
					int newX = x + dir[0];
					int newY = y + dir[1];
					if (newX >= 0 && newX < ROWS && newY >= 0 && newY < COLS &&
							!visited[newX][newY] &&
							(board[newX][newY].equals(".") || board[newX][newY].equals(" "))) {
						visited[newX][newY] = true;
						queue.add(new int[]{newX, newY});
					}
				}
			}

			if (found) {
				minFrogs++;
			}
		}

		// Count unreached food
		for (int i = 0; i < ROWS; i++) {
			for (int j = 0; j < COLS; j++) {
				if (!visited[i][j] && board[i][j].equals(".")) {
					unreachedFood++;
				}
			}
		}

		return new int[]{minFrogs, unreachedFood};
	}

	// Helper function for entrance
	private static boolean foundEntrance(String s) {
		return s.length() == 1 && Character.isUpperCase(s.charAt(0)) && !s.equals("X");
	}
}
