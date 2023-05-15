class Solution:
    def groupAnagrams(self, strs: List[str]) -> List[List[str]]:
        a = dict()
        for str in strs:
            key = tuple(sorted(str))
            if key in a:
                a[key].append(str)
            else:
                a[key] = [str]
        return a.values()