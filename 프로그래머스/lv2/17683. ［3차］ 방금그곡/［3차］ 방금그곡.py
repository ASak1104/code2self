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