import java.util.Arrays;

class Solution {
    public int solution(int[] A, int[] B) {
        descSort(A);
        descSort(B);

        int answer = 0;
        int j = 0;
        for (int a : A) {
            if (a < B[j]) {
                answer++;
                j++;
            }
        }
        return answer;
    }

    private void descSort(int[] arr) {
        Arrays.sort(arr);
        int temp = 0;
        for (int i = 0; i < arr.length >> 1; i++) {
            temp = arr[i];
            arr[i] = arr[arr.length - i - 1];
            arr[arr.length - i - 1] = temp;
        }
    }
}