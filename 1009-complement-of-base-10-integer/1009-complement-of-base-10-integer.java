class Solution {
    public int bitwiseComplement(int n) {
        // Special case for 0, as its binary is "0" and complement is "1"
        if (n == 0) return 1;
        
        // Find the number of bits in n
        // For n = 5 (101), bitCount will be 3
        int bitCount = (int)(Math.log(n) / Math.log(2)) + 1;
        
        // Create a mask with bitCount number of 1's
        // For bitCount 3, mask = (1 << 3) - 1 => 1000 - 1 = 0111 (binary)
        int mask = (1 << bitCount) - 1;
        
        // XOR n with the mask to flip all bits
        // 101 ^ 111 = 010 (2)
        return n ^ mask;
    }
}
