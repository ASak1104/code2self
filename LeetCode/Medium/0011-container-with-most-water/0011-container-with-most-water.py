class Solution:
    def maxArea(self, height: List[int]) -> int:
        left, right = 0, len(height) - 1
        res = 0
        
        while left < right:
            h = min(height[left], height[right])
            res = max(res, (right - left) * h)
            if height[left] == h:
                left += 1
            else:
                right -= 1
        return res
            