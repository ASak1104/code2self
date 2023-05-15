class Solution:
    def groupAnagrams(self, strs: List[str]) -> List[List[str]]:
        anagrams = defaultdict(list)
        for word in strs:
            word_sorted = tuple(sorted(word))
            anagrams[word_sorted].append(word)
        return anagrams.values()