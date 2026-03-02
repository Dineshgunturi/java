class Solution {
    public boolean rotateString(String s, String goal) {
        // If lengths are different, rotation is impossible
        if (s.length() != goal.length()) {
            return false;
        }
        
        // Check if goal is substring of s+s
        String doubled = s + s;
        return doubled.contains(goal);
    }
}