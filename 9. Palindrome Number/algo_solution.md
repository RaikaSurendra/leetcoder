# 9. Palindrome Number - Solution Notes

## Problem Summary
Given an integer `x`, return `true` if `x` is a palindrome, and `false` otherwise.

Constraints: `-2^31 <= x <= 2^31 - 1`

Follow-up: Solve it without converting the integer to a string.

---

## Approaches

### 1) Brute Force (String Two-Pointer)
- Convert `x` to string.
- Use two pointers (`i`, `j`) from both ends to compare characters.
- Return false on first mismatch; true if all pairs match.

Complexity:
- Time: O(n), n = number of digits
- Space: O(n) due to string storage

Notes:
- Negative numbers are immediately not palindromes (leading `-`).
- Simple and readable but uses extra space and avoids the follow-up constraint.

---

### 2) Optimal (Reverse Half Without String)
- Early reject: `x < 0` or `(x % 10 == 0 && x != 0)`.
- Iteratively build `reversedHalf` from the last digits of `x` until `reversedHalf >= x`.
- For even digits: check `x == reversedHalf`.
- For odd digits: drop the middle digit from `reversedHalf` (`reversedHalf / 10`) and compare to `x`.

Example: `x = 12321`
- Steps will end with `x = 12`, `reversedHalf = 123`. Compare `12 == 123/10` → true.

Complexity:
- Time: O(log10 x) — processes half the digits
- Space: O(1)

Why only half?
- Prevents 32-bit overflow and saves work. Full reverse risks overflow if you used `int`.

---

## Edge Cases
- `x < 0` → false
- Trailing zero but not zero itself (e.g., `10`) → false
- Single digit (e.g., `0`, `7`) → true
- Very large positive within 32-bit limit → handled safely by half-reversal

---

## Common Pitfalls
- Reversing the full number with `int` can overflow.
- Forgetting to handle numbers ending with `0`.
- Treating negative numbers as palindromes after stripping `-` (not allowed).

---

## Recommended Solution
Use the "Reverse Half" approach for O(1) space and to satisfy the follow-up. Keep the string-based solution as a simple baseline.

---

## Quick Pseudocode (Optimal)
```
if x < 0 or (x % 10 == 0 and x != 0):
    return false
reversed = 0
while x > reversed:
    reversed = reversed * 10 + x % 10
    x = x // 10
return x == reversed or x == reversed // 10
```

---

## Worked Examples (Step-by-step)

- **1221 (even length, palindrome)**
  - Start: `x=1221`, `reversed=0`
  - Iter1: take 1 → `reversed=1`, `x=122`
  - Iter2: take 2 → `reversed=12`, `x=12`
  - Stop: `x > reversed` is `12 > 12` → false
  - Compare: `x == reversed` → `12 == 12` → true

- **12321 (odd length, palindrome)**
  - Start: `x=12321`, `reversed=0`
  - Iter1: take 1 → `reversed=1`, `x=1232`
  - Iter2: take 2 → `reversed=12`, `x=123`
  - Iter3: take 3 → `reversed=123`, `x=12`
  - Stop: `12 > 123` → false
  - Compare odd case: `x == reversed/10` → `12 == 123/10` → `12 == 12` → true

- **10 (trailing zero, not palindrome)**
  - Early check: `x % 10 == 0 && x != 0` → `true` → return false (no loop needed)

- **-121 (negative, not palindrome)**
  - Early check: `x < 0` → `true` → return false

- **123456 (not palindrome)**
  - Start: `x=123456`, `reversed=0`
  - Iter1: take 6 → `reversed=6`, `x=12345`
  - Iter2: take 5 → `reversed=65`, `x=1234`
  - Iter3: take 4 → `reversed=654`, `x=123`
  - Stop: `123 > 654` → false
  - Compare: `x == reversed` (123==654) or `x == reversed/10` (123==65) → both false → not palindrome

- **0 (single digit, palindrome)**
  - Early checks pass; loop condition `x > reversed` is `0 > 0` → false
  - Compare: `x == reversed` → `0 == 0` → true
