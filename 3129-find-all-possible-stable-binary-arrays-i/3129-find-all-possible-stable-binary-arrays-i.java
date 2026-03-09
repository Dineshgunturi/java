public class Solution {
    public int numberOfStableArrays(int zero, int one, int limit) {
        int MOD = 1_000_000_007;
        // dp[i][j][0] -> ending with 0, dp[i][j][1] -> ending with 1
        long[][][] dp = new long[zero + 1][one + 1][2];

        // Base cases: single blocks of 0s or 1s within limit
        for (int i = 1; i <= Math.min(zero, limit); i++) dp[i][0][0] = 1;
        for (int j = 1; j <= Math.min(one, limit); j++) dp[0][j][1] = 1;

        for (int i = 1; i <= zero; i++) {
            for (int j = 1; j <= one; j++) {
                // To end with 0: sum previous stable arrays ending with 1
                // dp[i][j][0] = sum(dp[i-k][j][1]) for 1 <= k <= limit
                dp[i][j][0] = (dp[i - 1][j][0] + dp[i - 1][j][1]) % MOD;
                if (i > limit) {
                    dp[i][j][0] = (dp[i][j][0] - dp[i - limit - 1][j][1] + MOD) % MOD;
                }

                // To end with 1: sum previous stable arrays ending with 0
                // dp[i][j][1] = sum(dp[i][j-k][0]) for 1 <= k <= limit
                dp[i][j][1] = (dp[i][j - 1][1] + dp[i][j - 1][0]) % MOD;
                if (j > limit) {
                    dp[i][j][1] = (dp[i][j][1] - dp[i][j - limit - 1][0] + MOD) % MOD;
                }
            }
        }

        return (int) ((dp[zero][one][0] + dp[zero][one][1]) % MOD);
    }
}
