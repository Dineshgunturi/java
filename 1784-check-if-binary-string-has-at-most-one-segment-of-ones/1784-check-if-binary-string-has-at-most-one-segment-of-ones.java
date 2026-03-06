class Solution {
    public boolean checkOnesSegment(String s) {
        // If "01" appears, and later 1 appears again -> more than one segment
        return !s.contains("01");
    }
}