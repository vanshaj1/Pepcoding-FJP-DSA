/*
 * 677. Map Sum Pairs
 * Medium
 * 
 * 1340
 * 
 * 133
 * 
 * Add to List
 * 
 * Share
 * Design a map that allows you to do the following:
 * 
 * Maps a string key to a given value.
 * Returns the sum of the values that have a key with a prefix equal to a given string.
 * Implement the MapSum class:
 * 
 * MapSum() Initializes the MapSum object.
 * void insert(String key, int val) Inserts the key-val pair into the map. If the key already existed, the original key-value pair will be overridden to the new one.
 * int sum(string prefix) Returns the sum of all the pairs' value whose key starts with the prefix.
 *  
 * 
 * Example 1:
 * 
 * Input
 * ["MapSum", "insert", "sum", "insert", "sum"]
 * [[], ["apple", 3], ["ap"], ["app", 2], ["ap"]]
 * Output
 * [null, null, 3, null, 5]
 * 
 * Explanation
 * MapSum mapSum = new MapSum();
 * mapSum.insert("apple", 3);  
 * mapSum.sum("ap");           // return 3 (apple = 3)
 * mapSum.insert("app", 2);    
 * mapSum.sum("ap");           // return 5 (apple + app = 3 + 2 = 5)
 *  
 * 
 * Constraints:
 * 
 * 1 <= key.length, prefix.length <= 50
 * key and prefix consist of only lowercase English letters.
 * 1 <= val <= 1000
 * At most 50 calls will be made to insert and sum.
 */


 //code

 class MapSum {
    class Node{
        boolean eow = false;
        Node[] children = new Node[26];
        int val;
    }
    Node root;
    public MapSum() {
        root = new Node();
    }
    
    public void insert(String key, int val) {
        Node node = root;
        for(char ch : key.toCharArray()){
           if(node.children[ch - 'a'] == null){
               node.children[ch - 'a'] = new Node(); 
           }
            node = node.children[ch - 'a'];
        }
        node.val = val;
        node.eow = true;
    }
    int sum;
    public int sum(String prefix) {
        Node node = root;
        for(char ch : prefix.toCharArray()){
            if(node.children[ch - 'a'] != null){
               node = node.children[ch - 'a'];
           }else{
                return 0;
            }
        }
        sum = 0;
        helper(node);
        return sum;
    }
    void helper(Node node){
        sum += node.val;
        for(Node child: node.children){
            if(child != null){
               helper(child);                
            }
        }
    }
}

/**
 * Your MapSum object will be instantiated and called as such:
 * MapSum obj = new MapSum();
 * obj.insert(key,val);
 * int param_2 = obj.sum(prefix);
 */