def solution(m, n, board):
    transBoard = [[] for _ in range(n)]
    for r in range(m - 1, -1, -1):
        for tr, col in enumerate(board[r]):
            transBoard[tr].append(col)
    cleared = clearBlocks(transBoard)
    while cleared:
        cleared = clearBlocks(transBoard)
    return sum(m - len(row) for row in transBoard)


def clearBlocks(board):
    cleared = False
    targetRowSet = set()
    targetList = []
    for row in range(len(board) - 1):
        for col in range(len(board[row]) - 1):
            if not board[row][col]:
                break
            if isClear(row, col, board):
                targetRowSet |= {row, row + 1}
                targetList.append((row, col))
                cleared = True
    for row, col in targetList:
        board[row][col] = None
        board[row][col + 1] = None
        board[row + 1][col] = None
        board[row + 1][col + 1] = None
    for row in targetRowSet:
        board[row] = [col for col in board[row] if col]
    return cleared


def isClear(r, c, b):
    if len(b[r]) <= c + 1 or len(b[r + 1]) <= c + 1:
        return False
    if b[r][c] != b[r][c + 1]:
        return False
    if b[r][c] != b[r + 1][c]:
        return False
    return b[r][c] == b[r + 1][c + 1]