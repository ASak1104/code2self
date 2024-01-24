import java.util.Arrays;

class Solution {

    public int trap(int[] height) {
        int n = height.length;
        int[] leftToRight = new int[n];
        int[] rightToLeft = new int[n];

        int leftMax = height[0];

        for (int i = 1; i < n; i++) {
            leftToRight[i] = Math.max(0, leftMax - height[i]);
            leftMax = Math.max(leftMax, height[i]);
        }

        int rightMax = height[n - 1];

        for (int i = n - 2; i >= 0; i--) {
            rightToLeft[i] = Math.max(0, rightMax - height[i]);
            rightMax = Math.max(rightMax, height[i]);
        }

        int result = 0;

        for (int i = 0; i < n; i++) {
            result += Math.min(leftToRight[i], rightToLeft[i]);
        }

        return result;
    }

}
