//TMP
//import java.util.concurrent.TimeUnit;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

//Monte Carlo simulation

public class PercolationStats {
	double[] trialsArray;

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
    	Percolation perc = new Percolation(n);
    	
    	int N = n*n;
    	
		trialsArray = new double[trials];
    	//By number of trials
    	for(int i=0; i<trials; i++) {
    		
    		int totalOpen = 0;
    		double result=0;
	    	int increment = 1;
	    	
	    	perc = new Percolation(n);
	    	
	    	while(increment <= N && !perc.percolates()) {
	    		int iSquareRand = StdRandom.uniform(0, n);
	    		int jSquareRand = StdRandom.uniform(0, n);
	    		while(perc.isOpen(iSquareRand, jSquareRand)) { //*OPTIONAL: Registro de posiciones a no Calcular
	    			iSquareRand= StdRandom.uniform(0, n);
	    			jSquareRand= StdRandom.uniform(0, n);
	    			//TRAZA
//	    			try {
//						TimeUnit.SECONDS.sleep(0);
//						System.out.println("CALCULANDO");
//						System.out.println("iSquareRand: "+iSquareRand);
//						System.out.println("jSquareRand: "+jSquareRand);
//					} catch (InterruptedException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
	    			
	    		}
	    		perc.open(iSquareRand, jSquareRand);
	    		increment++;
	    		//TRAZA
//	    		perc.printGrid();
	    		totalOpen = perc.numberOfOpenSites();
//	    		System.out.println("Total open: "+ totalOpen);
//	    		System.out.println("Percolates(true/false): "+perc.percolates());
	    	}
	    	
//	    	System.out.println("FINAL Total open: "+ totalOpen);
	    	result = (double)totalOpen/N;
	    	increment =0;
//	    	System.out.println("RESULT P: " + result);
	    	trialsArray[i] = result;
    	}
    	
    }

    // sample mean of percolation threshold
    public double mean() {
    	return StdStats.mean(trialsArray);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
    	return StdStats.stddev(trialsArray);
    }

    // low endpoint of 95% confidence interval
//    public double confidenceLo() {
//    	
//    }

    // high endpoint of 95% confidence interval
//    public double confidenceHi() {
//    	
//    }

   // test client (see below)
   public static void main(String[] args) {
	   //TEST uniform distribution
	   PercolationStats percStats = new PercolationStats(2,10000);
	   double media = percStats.mean();
	   double dev = percStats.stddev();
	   System.out.println("------------------------");
	   System.out.println("Media: "+media);
	   System.out.println("Desv. Std: "+dev);
   }

}
