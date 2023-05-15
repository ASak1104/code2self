from collections import deque


def solution(cacheSize, cities):
    if not cacheSize:
        return len(cities) * 5

    runningTime = 0
    cache = deque(maxlen=cacheSize)
    
    for city in cities:
        city = city.lower()
        # cache hit
        if city in cache:
            cache.remove(city)
            runningTime += 1
        # cache miss
        else:
            runningTime += 5
        cache.append(city)
    return runningTime