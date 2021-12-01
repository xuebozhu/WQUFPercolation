//TMP
//import java.util.concurrent.TimeUnit;


import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

//Monte Carlo simulation

public class PercolationStats {
	double[] trialsArray;
	int N;

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
    	Percolation perc = new Percolation(n);
    	
    	this.N = n*n;
    	
		trialsArray = new double[trials];
    	//By number of trials
    	for(int i=0; i<trials; i++) {
    		
    		int totalOpen = 0;
    		double result=0;
	    	
	    	perc = new Percolation(n);
	    	

    		//random not repetitive number
    		int[] arr = new int[this.N];
    	    for (int x = 0; x < arr.length; x++) {
    	        arr[x] = x;
    	    }
    	    StdRandom.shuffle(arr);
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
    	    int x = 0;
	    	while(!perc.percolates() && x<arr.length) { 
    	    	int iSquareRand = arr[x] / n;
    			int jSquareRand = arr[x] % n;	
    			perc.open(iSquareRand, jSquareRand);
    			x++;
    	    }
    		//TRAZA
//	    		perc.printGrid();
    		totalOpen = perc.numberOfOpenSites();
//	    		System.out.println("Total open: "+ totalOpen);
//	    		System.out.println("Percolates(true/false): "+perc.percolates());
    	
    	
//	    	System.out.println("FINAL Total open: "+ totalOpen);
	    	result = (double)totalOpen/N;
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
    public double confidenceLo() {
    	double mean = this.mean();
	    // calculate standard deviation
	    double standardDeviation = this.stddev();
	    // value for 95% confidence interval, source: https://en.wikipedia.org/wiki/Confidence_interval#Basic_Steps
	    double confidenceLevel = 1.96;
	    double temp = confidenceLevel * standardDeviation / Math.sqrt(this.trialsArray.length);
	    return mean - temp;
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
    	double mean = this.mean();
	    // calculate standard deviation
	    double standardDeviation = this.stddev();
	    // value for 95% confidence interval, source: https://en.wikipedia.org/wiki/Confidence_interval#Basic_Steps
	    double confidenceLevel = 1.96;
	    double temp = confidenceLevel * standardDeviation / Math.sqrt(this.trialsArray.length);
    	return mean + temp;
    }

   // test client (see below)
   public static void main(String[] args) {
	   //TEST uniform distribution
	   PercolationStats percStats = new PercolationStats(100,100);
	   double media = percStats.mean();
	   double dev = percStats.stddev();
	   System.out.println("------------------------");
	   System.out.println("Media: "+media);
	   System.out.println("Desv. Std: "+dev);
	   System.out.println("95% confidence interval = ["+percStats.confidenceLo()+", "+percStats.confidenceHi()+"]");
   }

}
