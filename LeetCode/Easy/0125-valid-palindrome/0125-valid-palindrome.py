class Solution:
    def isPalindrome(self, s: str) -> bool:
        preprocess = ''.join([c for c in s.lower() if c.isalnum()])
        return preprocess == preprocess[::-1]