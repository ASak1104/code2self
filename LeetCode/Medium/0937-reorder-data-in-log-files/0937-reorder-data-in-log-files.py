class Solution:
    def reorderLogFiles(self, logs: List[str]) -> List[str]:
        letters, digits = [], []
        for s in logs:
            if s.split()[1].isdigit():
                digits.append(s)
            else:
                letters.append(s)
        letters.sort(key=lambda x: (x.split()[1:], x.split()[0]))
        return letters + digits