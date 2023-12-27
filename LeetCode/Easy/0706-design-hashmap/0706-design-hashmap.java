class MyHashMap {

    int[] values = new int[1_000_001];
    
    public MyHashMap() {
        Arrays.fill(values, -1);
    }
    
    public void put(int key, int value) {
        values[key] = value;
    }
    
    public int get(int key) {
        return values[key];
    }
    
    public void remove(int key) {
        values[key] = -1;
    }
    
}