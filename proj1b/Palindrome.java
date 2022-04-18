public class Palindrome {
    public Deque<Character> wordToDeque(String word) {
        Deque<Character> res = new LinkedListDeque<>();
        char[] s = word.toCharArray();
        for (int i = 0; i < s.length; i++) {
            res.addLast(s[i]);
        }
        return res;
    }

    public boolean isPalindrome(String word) {
        Deque<Character> a = wordToDeque(word);
        while (a.size() > 1) {
            if (a.removeFirst() != a.removeLast()) {
                return false;
            }
        }
        return true;
    }

    public boolean isPalindrome(String word, CharacterComparator cc) {
        Deque<Character> a = wordToDeque(word);
        while (a.size() > 1) {
            if (!cc.equalChars(a.removeFirst(), a.removeLast())) {
                return false;
            }
        }
        return true;
    }
}
