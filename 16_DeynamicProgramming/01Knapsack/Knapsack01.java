
public class Knapsack01 {

    int[][] memo;

    /**
     * 01背包问题是经典的动态规划问题,满足最有子结构
     * 状态转移方程
     * f(i,c) = max(f(i-1,c),v(i)+f(i-1,c-v(i)))
     * @param weights
     * @param values
     * @param capacity
     * @return
     */
    public int knapsack(int[] weights, int[] values, int capacity) {

        memo = new int[weights.length][capacity+1];
        return bestValue(weights, values, weights.length - 1, capacity);

    }

    private int bestValue(int[] weights, int[] values, int index, int capacity) {

        if (index < 0 || capacity <= 0) return 0;

        if (memo[index][capacity] != 0) return memo[index][capacity+1];

        int res = bestValue(weights,values,index-1,capacity);

        if (capacity >= weights[index]){
            res = Math.max(res,values[index]+bestValue(weights,values,
                    index-1,capacity-values[index]));
        }

        memo[index][capacity] = res;
        return res;
    }

}
