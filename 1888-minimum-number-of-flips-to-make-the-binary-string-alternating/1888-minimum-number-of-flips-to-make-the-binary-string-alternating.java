class Solution {
    public int minFlips(String s) {
        int n = s.length();
        // Concatenate s with itself to handle rotation
        String t = s + s;
        int minFlips = n;
        int mismatch01 = 0; // Target: 0101...
        int mismatch10 = 0; // Target: 1010...

        // Count initial mismatches for the first window of size n
        for (int i = 0; i < n; i++) {
            if (t.charAt(i) != (i % 2 == 0 ? '0' : '1')) mismatch01++;
            if (t.charAt(i) != (i % 2 == 0 ? '1' : '0')) mismatch10++;
        }

        minFlips = Math.min(mismatch01, mismatch10);

        // Sliding window: move the window one character at a time
        for (int i = 0; i < n; i++) {
            // Remove character at i, add character at i+n
            if (t.charAt(i) != (i % 2 == 0 ? '0' : '1')) mismatch01--;
            if (t.charAt(i) != (i % 2 == 0 ? '1' : '0')) mismatch10--;

            // New character added to window is t[i+n]
            // Its position in the new window is (i+n), which is equivalent to
            // (i+n) % 2 relative to the start of the window.
            // But since we just need to know if it matches the pattern based on its index
            // in the doubled string, we adjust the pattern check.
            if (t.charAt(i + n) != ((i + n) % 2 == 0 ? '0' : '1')) mismatch01++;
            if (t.charAt(i + n) != ((i + n) % 2 == 0 ? '1' : '0')) mismatch10++;

            minFlips = Math.min(minFlips, Math.min(mismatch01, mismatch10));
        }

        return minFlips;
    }
}
