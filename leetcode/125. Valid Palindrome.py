import re


class Solution:
    def isPalindrome(self, s: str) -> bool:
        # preprocess = ''.join([c for c in s.lower() if c.isalnum()])
        preprocess = re.sub('[^a-z0-9]', '', s.lower())
        return preprocess == preprocess[::-1]


sol = Solution()
print(sol.isPalindrome("A man, a plan, a canal: Panama"))
