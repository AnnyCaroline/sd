import org.apache.commons.math3.distribution.TDistribution;
import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.stat.descriptive.SummaryStatistics;

public class ConfidenceInterval {

    private double mean;
    private double lower;
    private double upper;

    public double getMean(){
        return mean;
    }

    public double getLower(){
        return lower;
    }

    public double getUpper(){
        return upper;
    }

    public ConfidenceInterval(long data[], double confidenceInterval) {
        // Build summary statistics of the dataset "data"
        SummaryStatistics stats = new SummaryStatistics();
        for (double val : data) {
            stats.addValue(val);
        }

        double ci = calcMeanCI(stats, confidenceInterval);
        this.mean = stats.getMean();
        this.lower = stats.getMean() - ci;
        this.upper = stats.getMean() + ci;
    }

    private double calcMeanCI(SummaryStatistics stats, double level) {
        try {
            // Create T Distribution with N-1 degrees of freedom
            TDistribution tDist = new TDistribution(stats.getN() - 1);
            // Calculate critical value
            double critVal = tDist.inverseCumulativeProbability(1.0 - (1 - level) / 2);
            // Calculate confidence interval
            return critVal * stats.getStandardDeviation() / Math.sqrt(stats.getN());
        } catch (MathIllegalArgumentException e) {
            return Double.NaN;
        }
    }
}
