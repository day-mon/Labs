public class Recursion {

    /**
     * Fibonacci sequence using Memoiztion implementation
     * @author Damon M.
     */


    static long [] memoization = new long[91];
   public static void main(String[] args) {

    for (int i = 0; i < memoization.length + 1; i++)
    {
 
        System.out.println("fib(" + i + ") = " + fib(i));
    }
}
    

    /**
     * Call tree with have 2^n levels
     *              Ex: fib(3)
     *                 fib(3)
     *         /                 \
     *       fib(2)              fib(1)
     *       /     \                   
     *      fib(1)  fib(0)      
     * 
     * fib(1) calls: 2
     * fib(2) calls: 1
     * fib(0) calls: 1 
     * Runtime without Memoization: O(2^n)
     * 
     * This many calls is redundant.
     * 
     * With Memoization we will stil recursively go through at first
     * but as we go down we save the values in case we have to make that calculation again
     * 
     * 
     * How it works: 
     * 
     * if n < 0,  tell user value cant be less then 0
     * if n = 0,  fib(0) return 0.
     * if n <= 2, return 1 fib(1) = 1, fib(2) = fib(0) (0) + fib(1) (1) = 1.
     * Ex: n = 4
     * else, if mem[4] == 0 (is empty)
     *      fill mem[4] with the prevoius value.
     * 
     * New Runtime: O(n)
     */
    public static long fib(long n) {
        if (n < 0) 
            new IllegalArgumentException("Your value cant be less than 0!");
        else if (n == 0)
            return 0;
        else if (n <= 2)
            return 1;
        else 
            if (memoization[(int)n] == 0) {
                memoization[(int)n] = fib(n-1) + fib(n-2);
            }
        return memoization[(int)n];

    }
}