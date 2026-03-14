import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Solution {

    /**
     * Finds the k-th lexicographically smallest happy string of length n.
     * A happy string consists only of 'a', 'b', 'c' and has no consecutive identical characters.
     *
     * @param n The desired length of the happy string (1 <= n <= 10).
     * @param k The desired rank (1 <= k <= 100).
     * @return The k-th happy string, or an empty string if it doesn't exist.
     */
    public String getHappyString(int n, int k) {
        // Step 1: Generate all possible happy strings using a helper function (e.g., backtracking).
        List<String> happyStrings = generateAllHappyStrings(n);

        // Step 2: Sort the generated strings in lexicographical order (they should already be in order if generated correctly).
        // Collections.sort(happyStrings); // Not strictly necessary if generation is careful, but safe to include.

        // Step 3: Check if the list contains at least k strings.
        if (k > happyStrings.size()) {
            return "";
        }

        // Step 4: Return the k-th string (using 0-based indexing for the list).
        return happyStrings.get(k - 1);
    }

    /**
     * A helper function to generate all happy strings of a given length 'n'.
     *
     * @param n The target length.
     * @return A list of all happy strings.
     */
    private List<String> generateAllHappyStrings(int n) {
        List<String> result = new ArrayList<>();
        backtrack(n, new StringBuilder(), result);
        return result;
    }

    /**
     * Backtracking function to build happy strings recursively.
     *
     * @param n      The target length.
     * @param current The string being built so far.
     * @param result  The list to add completed happy strings to.
     */
    private void backtrack(int n, StringBuilder current, List<String> result) {
        if (current.length() == n) {
            result.add(current.toString());
            return;
        }

        char[] chars = {'a', 'b', 'c'};
        for (char nextChar : chars) {
            // Check the happy string condition: the current character must not be the same as the last one added.
            if (current.length() == 0 || current.charAt(current.length() - 1) != nextChar) {
                current.append(nextChar); // Append the valid character
                backtrack(n, current, result); // Recurse
                current.deleteCharAt(current.length() - 1); // Backtrack (remove the last character for next iterations)
            }
        }
    }
}
