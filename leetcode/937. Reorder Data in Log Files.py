from typing import *


# class Solution:
#     def reorderLogFiles(self, logs: List[str]) -> List[str]:
#         letter_logs = []
#         digit_logs = []
#         for log in logs:
#             log_split = log.split()
#             if log_split[1].isdigit():
#                 digit_logs.append(log_split)
#             else:
#                 letter_logs.append(log_split)
#         letter_logs.sort(key=lambda x: (x[1:], x[0]))
#         return [' '.join(log) for log in letter_logs + digit_logs]


class Solution:
    def reorderLogFiles(self, logs: List[str]) -> List[str]:
        letters, digits = [], []
        for s in logs:
            if s[s.find(' ') + 1].isdigit():
                digits.append(s)
            else:
                letters.append(s)
        letters.sort(key=lambda x: (x[x.find(' '):], x[:x.find(' ')]))
        return letters + digits


# class Solution:
#     def reorderLogFiles(self, logs: List[str]) -> List[str]:
#         letters, digits = [], []
#         for s in logs:
#             if s.split()[1].isdigit():
#                 digits.append(s)
#             else:
#                 letters.append(s)
#         letters.sort(key=lambda x: (x.split()[1:], x.split()[0]))
#         return letters + digits




s = Solution()
print(s.reorderLogFiles(["dig1 8 1 5 1","let1 art can","dig2 3 6","let2 own kit dig","let3 art zero"]))
print(s.reorderLogFiles(["a1 9 2 3 1","g1 act car","zo4 4 7","ab1 off key dog","a8 act zoo"]))