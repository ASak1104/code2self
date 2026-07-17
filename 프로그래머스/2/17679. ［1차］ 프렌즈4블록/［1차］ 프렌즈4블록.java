import java.util.*;

class Solution {
    
    int rows, cols;
    
    char[][] lists;
    Set<Point> points;

    public int solution(int m, int n, String[] board) {
        init(m, n, board);
        
        int answer = 0;
        
        do {
            answer += points.size();
            points = new HashSet<>();
            
            for (int r = 0; r < rows - 1; r++) {
                for (int c = 0; c < cols - 1; c++) {
                    if (lists[r][c] == '\0') {
                        break;
                    }
                    mark(r, c);
                }
            }
            compact();
        } while (!points.isEmpty());
        
        return answer;
    }
    
    void init(int n, int m, String[] strings) {
        rows = m;
        cols = n;
        lists = new char[rows][cols];
        points = new HashSet<>();
        
        for (int r = 0; r < rows; r++) {
            lists[r] = new char[cols];
        }
        
        for (int c = 0; c < cols; c++) {
            var s = strings[cols - c - 1];
            
            for (int r = 0; r < rows; r++) {
                lists[r][c] = s.charAt(r);
            }
        }
    }
    
    void mark(int r, int c) {
        if (lists[r][c] != lists[r][c + 1] ||
            lists[r][c] != lists[r + 1][c] ||
            lists[r][c] != lists[r + 1][c + 1]) {
            return;
        }
        
        for (int ro = 0; ro < 2; ro++) {
            for (int co = 0; co < 2; co++) {
                points.add(new Point(r + ro, c + co));
            }
        }
    }
    
    void compact() {
        for (var p : points) {
            lists[p.r][p.c] = '\0';
        }
        
        for (var list : lists) {
            int w = 0;

            for (int i = 0; i < list.length; i++) {
                if (list[i] != '\0') {
                    list[w++] = list[i];
                }
            }

            while (w < list.length) {
                list[w++] = '\0';
            }
        }
    }
    
    private static record Point(int r, int c) {}
}