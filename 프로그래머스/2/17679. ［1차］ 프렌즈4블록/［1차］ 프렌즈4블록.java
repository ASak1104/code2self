import java.util.*;

class Solution {
    
    int rows, cols;
    
    List<List<Character>> lists;
    Set<Point> points;

    public int solution(int m, int n, String[] board) {
        init(m, n, board);
        
        int answer = 0;
        
        do {
            answer += points.size();
            points = new HashSet<>();
            
            for (int r = 0; r < rows - 1; r++) {
                for (int c = 0; c < cols - 1; c++) {
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
        lists = new ArrayList<>(rows);
        points = new HashSet<>();
        
        for (int r = 0; r < rows; r++) {
            lists.add(new ArrayList<>(cols));
        }
        
        for (int c = 0; c < cols; c++) {
            var s = strings[cols - c - 1];
            
            for (int r = 0; r < rows; r++) {
                lists.get(r).add(s.charAt(r));
            }
        }
    }
    
    void mark(int r, int c) {
        if (lists.get(r).get(c) == '\0' 
            || lists.get(r).get(c) != lists.get(r).get(c)
            || lists.get(r).get(c) != lists.get(r).get(c + 1)
            || lists.get(r).get(c) != lists.get(r + 1).get(c)
            || lists.get(r).get(c) != lists.get(r + 1).get(c + 1)) {
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
            lists.get(p.r).set(p.c, null);
        }
        
        for (var list : lists) {
            list.removeIf(Objects::isNull);
            
            while (list.size() < cols) {
                list.add('\0');
            }
        }
    }
    
    private static record Point(int r, int c) {}
}