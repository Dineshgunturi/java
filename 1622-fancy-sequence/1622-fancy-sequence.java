import java.util.ArrayList;
import java.util.List;

class Fancy {
    private static final long MOD = 1_000_000_007;
    private List<Long> nums;
    private long mul = 1;
    private long add = 0;

    public Fancy() {
        nums = new ArrayList<>();
    }

    public void append(int val) {
        // To store val so that (val * mul + add) later equals the current val,
        // we transform it: val_stored = (val - add) * inv(mul)
        long newVal = (val - add + MOD) % MOD;
        newVal = (newVal * modInverse(mul, MOD)) % MOD;
        nums.add(newVal);
    }

    public void addAll(int inc) {
        add = (add + inc) % MOD;
    }

    public void multAll(int m) {
        mul = (mul * m) % MOD;
        add = (add * m) % MOD;
    }

    public int getIndex(int idx) {
        if (idx >= nums.size()) return -1;
        // Result = (stored_val * current_mul + current_add) % MOD
        long res = (nums.get(idx) * mul) % MOD;
        res = (res + add) % MOD;
        return (int) res;
    }

    private long modInverse(long n, long mod) {
        return power(n, mod - 2, mod);
    }

    private long power(long x, long y, long mod) {
        long res = 1;
        x %= mod;
        while (y > 0) {
            if (y % 2 == 1) res = (res * x) % mod;
            y = y >> 1;
            x = (x * x) % mod;
        }
        return res;
    }
}
