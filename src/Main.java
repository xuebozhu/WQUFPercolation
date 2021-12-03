
public class Main {

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
    	perc.open(0, 1);
    	perc.open(0, 2);
    	perc.open(1, 0);
    	perc.open(1, 1);
    	perc.open(1, 2);
//    	perc.open(2, 0);
    	perc.open(2, 2);

    	//open some TEST 2*2
//    	perc.open(0, 0);
//    	perc.open(1, 1);
    	boolean percos = perc.percolates();


    	System.out.println("Check OPENS: "+perc.isOpen(6, 6));
//    	System.out.println(perc.numberOfOpenSites());
//    	System.out.println(percos);
    }

}
