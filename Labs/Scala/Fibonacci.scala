object Fibonacci {
    def main(args: Array[String]): Unit = {
        println("Enter the number: " )
        val n = scala.io.StdIn.readInt();
        val fib1 = fib(n+1, 0, 1)
        println(s"Fib($n) = $fib1")
    }

    def fib(n: BigInt, p: BigInt, c: BigInt): BigInt = {
        if (n <= 0)
            p
        else if (n == 1 || n == 2)
            c
        else
            fib(n-1, c, p+c)  
    }
}

