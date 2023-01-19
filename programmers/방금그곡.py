def solution(m, musicinfos):
    answer = '(None)'
    threshold = 0
    for musicinfo in musicinfos:
        start, end, name, music = musicinfo.split(',')
        sh, sm = map(int, start.split(':'))
        eh, em = map(int, end.split(':'))
        time = (eh - sh) * 60 + em - sm
        music = convertPound(music)
        while len(music) < time:
            music += music
        music = music[:time]
        if convertPound(m) in music and threshold < time:
            answer = name
            threshold = time
    return answer


def convertPound(m):
    for i, c in enumerate(['G#', 'A#', 'C#', 'D#', 'F#']):
        m = m.replace(c, str(i))
    return m


s1 = solution("ABCDEFG", ["12:00,12:14,HELLO,CDEFGAB", "13:00,13:05,WORLD,ABCDEF"])
print(s1)
s2 = solution("CC#BCC#BCC#BCC#B", ["03:00,03:30,FOO,CC#B", "04:00,04:08,BAR,CC#BCC#BCC#B"])
print(s2)
s3 = solution("ABC", ["12:00,12:14,HELLO,C#DEFGAB", "13:00,13:05,WORLD,ABCDEF"])
print(s3)