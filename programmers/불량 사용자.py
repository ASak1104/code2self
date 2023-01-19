import re
from itertools import permutations as pm

def solution(user_id, banned_id):
    match_set = set()
    bid_list = [bid.replace('*', '.') for bid in banned_id]
    for uid_list in pm(user_id, len(banned_id)):
        if all(re.match(bid, uid) for uid, bid in zip(uid_list, bid_list)):
            match_set.add(set(uid_list))
    return match_set