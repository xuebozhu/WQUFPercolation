import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
	private class Square {
		// -1= blocked
		// 0 = open
		
		int state;
		int id;
		
		public Square(int state, int x, int y) {
			this.state = state;
			this.id = (n * y) + (x + 1); // starts in 0
		}
		

		int getId() {
			return this.id;
		}
		
		int getState() { return this.state; }
		 

		void openBlock() {
			this.state = 0;
		}

		boolean isOpenBlock() {
			return this.state == 0 || this.state == 1;
		}

		boolean isFullBlock() {
			return this.state == 1;
		}

		boolean isBlockedBlock() {
			return this.state == -1;
		}
	}

	private final Square[][] grid;
	private final int n;
	private final int N;
	private final WeightedQuickUnionUF WQUFrep;

	// creates n-by-n grid, with all sites initially blocked
	public Percolation(int n) {
		
		if (n <= 0)
			throw new IllegalArgumentException("Value not valid");
		
		// grid Creation
		this.n = n;
		grid = new Square[n][n];
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) {
				grid[i][j] = new Square(-1, j, i);
			}
		}

		// Graph Creation
		this.N = (this.n * this.n) + 2;
		WQUFrep = new WeightedQuickUnionUF(N);

		// Illegal arguments j-1>=0 and i+1<grid.lenght

	}

	// opens the site (row, col) if it is not open already
	public void open(int row, int col) {

		int gridRow = row - 1;
		int gridCol = col - 1;
		
		if ((gridRow < 0 || gridRow >= grid.length) || (gridCol < 0 || gridCol >= grid[0].length))
			throw new IllegalArgumentException("Value not valid");

		if (grid[gridRow][gridCol].isBlockedBlock()) {
			grid[gridRow][gridCol].openBlock();
		}
		
		int q = grid[gridRow][gridCol].getId();

		// TOP
		if (gridRow == 0) {
			WQUFrep.union(0, grid[gridRow][gridCol].getId());
		}
		// BOT
		if (gridRow == grid.length - 1) {
			WQUFrep.union(N - 1, grid[gridRow][gridCol].getId());
		}
		// MID
		if (gridRow >= 0 && gridRow < grid.length) {
			// getLeft
			if (gridCol - 1 >= 0) { // Illegal argument left
				if (grid[gridRow][gridCol - 1].isOpenBlock()) {
					int p = grid[gridRow][gridCol - 1].getId();
					WQUFrep.union(p, q); // Revisar
				}
			}
			// getRight
			if (gridCol + 1 <= grid[gridRow].length - 1) { // Illegal argument right
				if (grid[gridRow][gridCol + 1].isOpenBlock()) {
					int p = grid[gridRow][gridCol + 1].getId();
					WQUFrep.union(p, q); // Revisar
				}
			}
			// getUP
			if (gridRow - 1 >= 0) { // Illegal argument up
				if (grid[gridRow - 1][gridCol].isOpenBlock()) {
					int p = grid[gridRow - 1][gridCol].getId();
					WQUFrep.union(p, q); // Revisar
				}
			}
			// getDOWn
			if (gridRow + 1 <= grid.length - 1) { // Illegal argument down
				if (grid[gridRow + 1][gridCol].isOpenBlock()) {
					int p = grid[gridRow + 1][gridCol].getId();
					WQUFrep.union(p, q); // Revisar
				}
			}
		}
	}

	// is the site (row, col) open?
	public boolean isOpen(int row, int col) {
		int gridRow = row - 1;
		int gridCol = col - 1;

		if ((gridRow < 0 || gridRow >= grid.length) || (gridCol < 0 || gridCol >= grid[0].length))
			throw new IllegalArgumentException("Value not valid");
		return grid[gridRow][gridCol].isOpenBlock();
	}

	// is the site (row, col) full?
	public boolean isFull(int row, int col) {
		int gridRow = row - 1;
		int gridCol = col - 1;
		
		int q = grid[gridRow][gridCol].getId();

		if ((gridRow < 0 || gridRow >= grid.length) || (gridCol < 0 || gridCol >= grid[0].length))
			throw new IllegalArgumentException("Value not valid");
		return WQUFrep.find(0) == WQUFrep.find(q);
	}

	// returns the number of open sites
	public int numberOfOpenSites() {
		int nOpen = 0;
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) {
				if (grid[i][j].isOpenBlock() || grid[i][j].isFullBlock())
					nOpen++;
			}
		}
		return nOpen;
	}

	// does the system percolate?
	public boolean percolates() {
		return WQUFrep.find(0) == WQUFrep.find(N - 1);
	}
}