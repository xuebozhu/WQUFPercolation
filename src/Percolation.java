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
		int getId() {
			return this.id;
		}
		int getState() {
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
	int N;
	WeightedQuickUnionUF WQUFrep; 
	
	//*****Here STARTS****************
	// creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
    	//Grid Creation
    	this.n = n;
    	grid= new Square[n][n];
    	for(int i=0; i < grid.length; i++) {
    		for(int j=0; j<grid[i].length; j++) {
    			grid[i][j] = new Square(-1, j, i);
    		}
    	}
    	
    	//Graph Creation
    	this.N = (this.n * this.n) + 2; //0 ->top , 17->bottom
    	WQUFrep = new WeightedQuickUnionUF(N);

    	//Illegal arguments j-1>=0 and i+1<grid.lenght
    	
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
    	//TOP
    	if(row == 0) {
    		WQUFrep.union(0, grid[row][col].getId());
    	}
    	//BOT
    	if(row==grid.length-1) {
    		WQUFrep.union(N-1, grid[row][col].getId());
    	}
    	//MID
    	if(row>=0 && row<grid.length) {
    		//getLeft
    		if(col-1>=0) { //Illegal argument left
	    		if(grid[row][col-1].isOpenBlock()) {
	    			int p = grid[row][col-1].getId();
    				int q = grid[row][col].getId();
    				WQUFrep.union(p, q); //Revisar
	    		}
    		}
    		//getRight
    		if(col+1<=grid[row].length-1) { //Illegal argument right
    			if(grid[row][col+1].isOpenBlock()) {
    				int p = grid[row][col+1].getId();
    				int q = grid[row][col].getId();
    				WQUFrep.union(p, q); //Revisar
    			}
    		}
    		//getUP
    		if(row-1>=0) { //Illegal argument up
    			if(grid[row-1][col].isOpenBlock()) {
    				int p = grid[row-1][col].getId();
    				int q = grid[row][col].getId();
    				WQUFrep.union(p, q); //Revisar
    			}
    		}
    		//getDOWn
    		if(row+1<=grid.length-1) { //Illegal argument down
    			if(grid[row+1][col].isOpenBlock()) {
    				int p = grid[row+1][col].getId();
    				int q = grid[row][col].getId();
    				WQUFrep.union(p, q); //Revisar
    			}
    		}
    	}

    	//^^^^OPEN
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
    	//TRAZA
    	//System.out.println("count(Graph): "+WQUFrep.count());
    	return WQUFrep.find(0) == WQUFrep.find(N-1);
    }
}