import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
	private class Square {
		// -1= blocked
		// 0 = open
		// 1 = full
		int state;
		int id;

		public Square(int state, int x, int y) {
			this.state = state;
			this.id = (n * y) + (x + 1); // starts in 0
		}

		int getId() {
			return this.id;
		}
		
		int getState() {
			return this.state;
		}

		void openBlock() {
			this.state = 0;
		}

		void fillBlock() {
			this.state = 1;
		}

		boolean isOpenBlock() {
			return this.state == 0 || this.state == 1;
		}

		boolean isFullBlock() {
			return this.state == 1;
		}
	}

	private final Square[][] grid;
	private final int n;
	private final int N;
	private final WeightedQuickUnionUF WQUFrep;

	// creates n-by-n grid, with all sites initially blocked
	public Percolation(int n) {
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
		
		grid[gridRow][gridCol].openBlock();

		// TOP
		if (gridRow == 0) {
			WQUFrep.union(0, grid[gridRow][gridCol].getId());
			grid[gridRow][gridCol].fillBlock();
		}
		// BOT
		if (gridRow == grid.length - 1) {
			WQUFrep.union(N - 1, grid[gridRow][gridCol].getId());
			grid[gridRow][gridCol].openBlock();
		}
		// MID
		if (gridRow >= 0 && gridRow < grid.length) {
			// getLeft
			if (gridCol - 1 >= 0) { // Illegal argument left
				if (grid[gridRow][gridCol - 1].isOpenBlock()) {
					int p = grid[gridRow][gridCol - 1].getId();
					int q = grid[gridRow][gridCol].getId();
					WQUFrep.union(p, q); // Revisar

					// Full checking
					if (grid[gridRow][gridCol - 1].isFullBlock()) {
						grid[gridRow][gridCol].fillBlock();
					} else {
						grid[gridRow][gridCol].openBlock();
					}
				}
			}
			// getRight
			if (gridCol + 1 <= grid[gridRow].length - 1) { // Illegal argument right
				if (grid[gridRow][gridCol + 1].isOpenBlock()) {
					int p = grid[gridRow][gridCol + 1].getId();
					int q = grid[gridRow][gridCol].getId();
					WQUFrep.union(p, q); // Revisar
					
					// Full checking
					if (grid[gridRow][gridCol + 1].isFullBlock()) {
						grid[gridRow][gridCol].fillBlock();
					} else {
						grid[gridRow][gridCol].openBlock();
					}

				}
			}
			// getUP
			if (gridRow - 1 >= 0) { // Illegal argument up
				if (grid[gridRow - 1][gridCol].isOpenBlock()) {
					int p = grid[gridRow - 1][gridCol].getId();
					int q = grid[gridRow][gridCol].getId();
					WQUFrep.union(p, q); // Revisar
					
					// Full checking
					if (grid[gridRow - 1][gridCol].isFullBlock()) {
						grid[gridRow][gridCol].fillBlock();
					} else {
						grid[gridRow][gridCol].openBlock();
					}
				}
			}
			// getDOWn
			if (gridRow + 1 <= grid.length - 1) { // Illegal argument down
				if (grid[gridRow + 1][gridCol].isOpenBlock()) {
					int p = grid[gridRow + 1][gridCol].getId();
					int q = grid[gridRow][gridCol].getId();
					WQUFrep.union(p, q); // Revisar
					grid[gridRow][gridCol].openBlock();
					// Full checking
				}
			}
		}

	}
	
	// print grid - BORRAR
	private void printGrid() {
    	for(int i=0; i < grid.length; i++) {
    		for(int j=0; j<grid[i].length; j++) {
    			System.out.print(" "+grid[i][j].getState());
    		}
    		System.out.println();
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

		if ((gridRow < 0 || gridRow >= grid.length) || (gridCol < 0 || gridCol >= grid[0].length))
			throw new IllegalArgumentException("Value not valid");
		return grid[gridRow][gridCol].isOpenBlock();
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

	public static void main(String[] args) {
    	Percolation perc = new Percolation(3);

    	//open some TEST 3*3
//    	perc.open(0, 0);
//    	perc.open(0, 1);
//    	perc.open(1, 0);
//    	perc.open(1, 2);
//    	perc.open(2, 1);
//    	perc.open(2, 2);

    	//open some TEST 3*3
//    	perc.open(0, 0);
//    	perc.open(0, 1);
//    	perc.open(0, 2);
//    	perc.open(1, 0);
//    	perc.open(1, 2);
//    	perc.open(2, 1);
//    	perc.open(2, 2);

    	//open some TEST 3*3
//    	perc.open(1, 1);
//    	perc.open(1, 2);
    	perc.open(1, 3);
    	perc.open(2, 1);
//    	perc.open(2, 2);
    	perc.open(2, 3);
//    	perc.open(2, 0);
    	perc.open(3, 3);

    	//open some TEST 2*2
//    	perc.open(0, 0);
//    	perc.open(1, 1);
    	boolean percos = perc.percolates();




    	perc.printGrid();
    	System.out.println();
    	System.out.println(perc.numberOfOpenSites());
    	System.out.println(percos);
    }
}