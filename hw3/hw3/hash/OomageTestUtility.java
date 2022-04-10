package hw3.hash;

import java.util.List;

public class OomageTestUtility {
    public static boolean haveNiceHashCodeSpread(List<Oomage> oomages, int M) {
        int[] bucketNum = new int[M];
        int N = oomages.size();
        for (Oomage x : oomages) {
            int index = (x.hashCode() & 0x7FFFFFFF) % M;
            bucketNum[index]++;
        }
        for (int i = 0; i < M; i++) {
            if (bucketNum[i] < N / 50 || bucketNum[i] > N / 2.5) {
                return false;
            }
        }
        return true;
    }
}
