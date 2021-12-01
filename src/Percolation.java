import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
	class Square {
		//-1= blocked
		//0 = open
		//1 = full
		int state;
		int x;
		int y;
		int id;
		
		public Square(int state, int x, int y) {
			this.state = state;
			this.x = x;
			this.y = y;
			this.id = (n * y) + (x+1); // starts in 0
		}
		public int getId() {
			return this.id;
		}
		public int getState() {
			return this.state;
		}
		void fill() {
			this.state = 1;
		}
		int getX() {
			return this.x;
		}
		int getY() {
			return this.y;
		}
		
		void openBlock() {
			this.state = 0;
		}
		boolean isOpenBlock() {
			return this.state == 0;
		}
		boolean isFullBlock() {
			return this.state == 1;
		}
	}
	
	Square[][] grid;
	int n;
	
	//*****Here STARTS****************
	// creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
    	this.n = n;
    	grid= new Square[n][n];
    	for(int i=0; i < grid.length; i++) {
    		for(int j=0; j<grid[i].length; j++) {
    			grid[i][j] = new Square(-1, j, i);
    		}
    	}
    }
    
    //method to print
    void printGrid() {
    	for(int i=0; i < grid.length; i++) {
    		for(int j=0; j<grid[i].length; j++) {
    			System.out.print(" "+grid[i][j].getState());
    		}
    		System.out.println();
    	}
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
    	grid[row][col].openBlock();
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
    	return grid[row][col].isOpenBlock();
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
    	return grid[row][col].isFullBlock();
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
    	int nOpen=0;
    	for(int i=0; i < grid.length; i++) {
    		for(int j=0; j<grid[i].length; j++) {
    			if(grid[i][j].isOpenBlock() || grid[i][j].isFullBlock()) nOpen++;
    		}
    	}
    	return nOpen;
    }

    // does the system percolate?
	public boolean percolates() {
    	int N = (this.n * this.n) + 2; //0 ->top , 17->bottom
    	WeightedQuickUnionUF WQUFrep = new WeightedQuickUnionUF(N);
    	
    	boolean isPercolated = false;
    	//check top row
    	for(int i=0; i<grid[0].length; i++) {
    		if(grid[0][i].isOpenBlock()) {
    			int q = grid[0][i].getId();
    			WQUFrep.union(0, q);
    			grid[0][i].fill();
    			isPercolated = true;
    		}
    	}
    	if(isPercolated) {
	    	//check bottom row
	    	int lastRowStart = grid[grid.length-1][0].getId();
	    	for(int i=0; i<grid[grid.length-1].length; i++) {
	    		if(grid[grid.length-1][i].isOpenBlock()) {
	    			WQUFrep.union(N-1, lastRowStart+i);
	    		}
	    	}
	    	
	    	//CHECKING UNIONS INSIDE
	    	for(int i=1; i < grid.length; i++) {
	    		for(int j=0; j<grid[i].length; j++) {
	    			if(grid[i][j].isOpenBlock()) {
	    				//getUP
	    				if(grid[i-1][j].isFullBlock()) {
			    			int p = grid[i-1][j].getId();
			    			int q = grid[i][j].getId();
			    			WQUFrep.union(p, q); //Revisar
			    			grid[i][j].fill();
		    			}
		    			//getLeft
		    			if(j-1>=0) {
			    			if(grid[i][j-1].isFullBlock()) {
			    				int p = grid[i][j-1].getId();
			    				int q = grid[i][j].getId();
			    				WQUFrep.union(p, q); //Revisar
			    				grid[i][j].fill();
			    			}
		    			}
	    			}
	    		}
	    	}
    	}
    	isPercolated = WQUFrep.find(0) == WQUFrep.find(N-1);	
	
    	//TRAZA
    	//System.out.println("count(Graph): "+WQUFrep.count());
    	return isPercolated;
	    
    	
    	
    }

    // test client (optional)
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
    	perc.open(0, 0);
    	perc.open(1, 0);
    	perc.open(1, 1);
    	perc.open(1, 2);
    	perc.open(2, 0);
    	perc.open(2, 2);
    	
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
