class Solution {
    public long minNumberOfSeconds(int mountainHeight, int[] workerTimes) {
        long low = 0;
        // Upper bound: one worker reducing the entire mountain
        // Using the slowest possible time (max workerTime * sum of 1 to mountainHeight)
        long maxWorkerTime = 0;
        for (int time : workerTimes) maxWorkerTime = Math.max(maxWorkerTime, time);
        long high = maxWorkerTime * (long) mountainHeight * (mountainHeight + 1) / 2;
        
        long ans = high;
        while (low <= high) {
            long mid = low + (high - low) / 2;
            if (canReduce(mid, mountainHeight, workerTimes)) {
                ans = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return ans;
    }

    private boolean canReduce(long timeLimit, int targetHeight, int[] workerTimes) {
        long totalHeightReduced = 0;
        for (int wTime : workerTimes) {
            // Solve: wTime * x * (x + 1) / 2 <= timeLimit
            // x^2 + x - (2 * timeLimit / wTime) <= 0
            long limit = (2 * timeLimit) / wTime;
            long x = (long) ((-1 + Math.sqrt(1 + 4 * limit)) / 2);
            totalHeightReduced += x;
            
            if (totalHeightReduced >= targetHeight) return true;
        }
        return totalHeightReduced >= targetHeight;
    }
}
