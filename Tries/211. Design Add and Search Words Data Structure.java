/*
 * 211. Design Add and Search Words Data Structure
* Medium
* 
* 5446
* 
* 286
* 
* Add to List
* 
* Share
* Design a data structure that supports adding new words and finding if a string matches any previously added string.
* 
* Implement the WordDictionary class:
* 
* WordDictionary() Initializes the object.
* void addWord(word) Adds word to the data structure, it can be matched later.
* bool search(word) Returns true if there is any string in the data structure that matches word or false otherwise. word may contain dots '.' where dots can be matched with any letter.
*  
* 
* Example:
* 
* Input
* ["WordDictionary","addWord","addWord","addWord","search","search","search","search"]
* [[],["bad"],["dad"],["mad"],["pad"],["bad"],[".ad"],["b.."]]
* Output
* [null,null,null,null,false,true,true,true]
* 
* Explanation
* WordDictionary wordDictionary = new WordDictionary();
* wordDictionary.addWord("bad");
* wordDictionary.addWord("dad");
* wordDictionary.addWord("mad");
* wordDictionary.search("pad"); // return False
* wordDictionary.search("bad"); // return True
* wordDictionary.search(".ad"); // return True
* wordDictionary.search("b.."); // return True
*  
* 
* Constraints:
* 
* 1 <= word.length <= 25
* word in addWord consists of lowercase English letters.
* word in search consist of '.' or lowercase English letters.
* There will be at most 3 dots in word for search queries.
* At most 104 calls will be made to addWord and search.

 */


//  Code



class WordDictionary {
    class Node{
        boolean eow = false;
        Node[] children = new Node[26];
    }
    Node root;
    public WordDictionary() {
        root = new Node();
    }
    
    public void addWord(String word) {
        Node node = root;
        for(char ch : word.toCharArray()){
           if(node.children[ch - 'a'] == null){
               node.children[ch - 'a'] = new Node(); 
           }
            node = node.children[ch - 'a'];
        }
        node.eow = true;
    }
    
    public boolean search(String word) {
        return helper(word,0,root);
    }
    public boolean helper(String word,int idx,Node node){
        if(idx == word.length()){
            return node.eow;
        }
        char ch = word.charAt(idx);
        if(ch != '.'){
            Node child = node.children[ch - 'a'];
            if(child == null){
                return false;
            }else{
               return helper(word,idx+1,child);
            }
        }else{
            for(char nch='a';nch <= 'z';nch++){
                Node child = node.children[nch - 'a'];
                if(child != null){
                    boolean res = helper(word,idx+1,child);
                    if(res){
                        return true;
                    }
                }
            }
        }
        return false;
    }
}

/**
 * Your WordDictionary object will be instantiated and called as such:
 * WordDictionary obj = new WordDictionary();
 * obj.addWord(word);
 * boolean param_2 = obj.search(word);
 */