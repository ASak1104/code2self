from math import ceil


def solution(fees, records):
    basicTime, basicFee, timeUnit, unitPerFee = fees
    parkDict = dict()
    for record in records:
        time, serial, content = record.split()
        hour, minute = map(int, time.split(':'))
        if len(content) < 3:
            if serial in parkDict:
                parkDict[serial][1] = hour * 60 + minute
            else:
                parkDict[serial] = [0, hour * 60 + minute]
        else:
            parkDict[serial][0] += hour * 60 + minute - parkDict[serial][1]
            parkDict[serial][1] = -1
    
    for serial, [totalTime, lastTime] in parkDict.items():
        parkDict[serial] = basicFee
        if lastTime > -1:
            totalTime += 24 * 60 - lastTime - 1
        if totalTime > basicTime:
            parkDict[serial] += ceil((totalTime - basicTime) / timeUnit) * unitPerFee
    return [value for _, value in sorted(parkDict.items())]