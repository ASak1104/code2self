class Solution {
    public int[] solution(int[] numbers) {
        int[] answer = new int[numbers.length];
        int localMax = 0;
        for (int i = answer.length - 1; i > -1; i--) {
            if (numbers[i] >= localMax) {
                localMax = numbers[i];
                answer[i] = -1;
                continue;
            }
            for (int j = i + 1; answer[i] == 0; j++) {
                if (numbers[i] < numbers[j]) {
                    answer[i] = numbers[j];
                } else if (numbers[i] < answer[j]) {
                    answer[i] = answer[j];
                }
            }
        }
        return answer;
    }
}