
public class Knapsack02 {

    public int knapsack(int[] weights, int[] values, int capacity) {
        int n = weights.length;
        if (n == 0) return 0;

        int[][] dp = new int[n][capacity + 1];

        for (int j = 0; j <= capacity; j++) {
            dp[0][j] = (j >= weights[0] ? values[0] : 0);
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; i <= capacity; j++) {
                dp[i][j] = dp[i - 1][j];
                if (j >= weights[i]) {
                    dp[i][j] = Math.max(dp[i][j], values[i] + dp[i][j - weights[i]]);
                }
            }
        }

        return dp[n][capacity];
    }
}
