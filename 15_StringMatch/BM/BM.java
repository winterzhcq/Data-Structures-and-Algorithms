
/**
 * @author winter
 * @date 2019/11/3 15:01
 */
public class BM {

    private static final int SIZE = 256;

    // a,b表示主串和模式串；n，m表示主串和模式串的长度。
    public int bm(char[] a, int n, char[] b, int m) {
        int[] bc = new int[SIZE]; // 记录模式串中每个字符最后出现的位置
        generateBC(b, m, bc); // 构建坏字符哈希表
        int[] suffix = new int[m];
        boolean[] prefix = new boolean[m];
        generateGS(b, m, suffix, prefix);
        int i = 0; // j表示主串与模式串匹配的第一个字符
        while (i <= n - m) {
            int j;
            for (j = m - 1; j >= 0; --j) { // 模式串从后往前匹配
                if (a[i+j] != b[j]) break; // 坏字符对应模式串中的下标是j
            }
            if (j < 0) {
                return i; // 匹配成功，返回主串与模式串第一个匹配的字符的位置
            }
            int x = j - bc[(int)a[i+j]];
            int y = 0;
            if (j < m-1) { // 如果有好后缀的话
                y = moveByGS(j, m, suffix, prefix);
            }
            i = i + Math.max(x, y);
        }
        return -1;
    }

    // 构造坏字符匹配规则
    private int moveByGS(int end, int m, int[] suffix, boolean[] prefix) {
        int k = m - 1 - end; // 好后缀长度
         if (suffix[k] != -1)
             return end - suffix[k] +1;
         for (int r = end+2; r <= m-1; ++r) {
             if (prefix[m-r] == true) {
                 return r;
             }
         }
         return m;
    }

    // 构造好好后缀规则
    private void generateGS(char[] b, int m, int[] suffix, boolean[] prefix) {
        for (int i = 0; i < m; i++) {
            suffix[i] = -1;
            prefix[i] = false;
        }
        for (int i = 0; i < m-1 ; i++) {
            int j = i;
            int k = 0;
            while (j >= 0 && b[j] == b[m-1-k]){
                j--;
                k++;
                suffix[k] = j+1;
            }
            if ( j == -1) prefix[k] = true;
        }
    }

    private void generateBC(char[] b, int m, int[] bc) {
        for (int i = 0; i < SIZE ; i++) {
            bc[i] = -1;
        }
        for (int i = 0; i < m; i++) {
            int ascii = (int)b[i];
            bc[ascii] = i;
        }
    }
}
