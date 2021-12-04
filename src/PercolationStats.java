import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
	private final double[] trialsArray;

	// perform independent trials on an n-by-n grid
	public PercolationStats(int n, int trials) {

		if (n <= 0 || trials <= 0)
			throw new IllegalArgumentException();

		trialsArray = new double[trials];

		// random not repetitive number
		int[] arr = new int[n * n];
		for (int x = 0; x < arr.length; x++) {
			arr[x] = x;
		}

		// By number of trials
		for (int i = 0; i < trials; i++) {

			int totalOpen = 0;
			double result = 0;

			Percolation perc = new Percolation(n);

			StdRandom.shuffle(arr);

			boolean isPercolates = false;
			int y = 0;
			while (!isPercolates && y < arr.length) {
				int iSquareRand = arr[y] / n;
				int jSquareRand = arr[y] % n;

				perc.open(iSquareRand + 1, jSquareRand + 1);

				isPercolates = perc.percolates();
				y++;
			}
			totalOpen = perc.numberOfOpenSites();
			result = (double) totalOpen / (n * n);
			trialsArray[i] = result;

			perc = null;
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
		// value for 95% confidence interval, source:
		double confidenceLevel = 1.96;
		double temp = confidenceLevel * standardDeviation / Math.sqrt(this.trialsArray.length);
		return mean - temp;
	}

	// high endpoint of 95% confidence interval
	public double confidenceHi() {
		double mean = this.mean();
		// calculate standard deviation
		double standardDeviation = this.stddev();
		// value for 95% confidence interval, source:
		double confidenceLevel = 1.96;
		double temp = confidenceLevel * standardDeviation / Math.sqrt(this.trialsArray.length);
		return mean + temp;
	}

	// test client (see below)
	public static void main(String[] args) {
		// TEST uniform distribution
		try {
			int n1 = Integer.parseInt(args[0]);
			int ntrials = Integer.parseInt(args[1]);

			if (n1 <= 0 || ntrials <= 0)
				throw new IllegalArgumentException();

			PercolationStats percStats = new PercolationStats(n1, ntrials);
			double media = percStats.mean();
			double dev = percStats.stddev();
			System.out.println("mean                    = " + media);
			System.out.println("stddev                  = " + dev);
			System.out.println(
					"95% confidence interval = [" + percStats.confidenceLo() + ", " + percStats.confidenceHi() + "]");

		} catch (NumberFormatException ex) {
			System.out.println("Error: Not a number in args");
		}
	}
}
