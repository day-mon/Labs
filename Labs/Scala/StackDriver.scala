import java.util.EmptyStackException




object StackDriver {

  def main(args: Array[String]): Unit = {
    println("Attempting to create a new stack of integers...")
    val START_TIME: Long = System.currentTimeMillis()
    val beforeUsedMem: Long = Runtime.getRuntime
        .totalMemory() - Runtime.getRuntime.freeMemory()
    val stack: VectorStack[Integer] = new VectorStack[Integer]()
    System.out.print("Check to see if the stack is empty: ")
    if (stack.empty()) println("PASS") else println("FAIL")
    System.out.print(
      "Attempting to remove from empty stack (should throw EmptyStackException): ")
    try {
      println(stack.pop())
      println("FAIL")
    } catch {
      case e: EmptyStackException => println("PASS")

    }
    stack.push(1)
    stack.push(2)
    stack.push(3)
    stack.push(4)
    stack.push(5)
    stack.push(null)
    stack.push(6)
    stack.push(7)
    stack.push(7)
    println("Should print 7: " + stack.peek())
//  removes 7
    stack.pop()
    println("Should print 1: " + stack.search(7))
    println("Should print 7: " + stack.peek())
//  remove 7
    stack.pop()
    println("Should return 2: " + stack.search(null))
    println("Should return 1: " + stack.search(6))
    println("Should return 7: " + stack.search(1))
// remove 6
    stack.pop()
// remove null
    stack.pop()
// remove 5
    stack.pop()
    println("Should print 4: " + stack.peek())
    println("Should print -1: " + stack.search(null))
// remove 4
    stack.pop()
// remove 3
    stack.pop()
// remove 2
    stack.pop()
// remove 1
    stack.pop()
    println("Should print true: " + stack.empty())
    val DELTA_TIME: Long = System.currentTimeMillis() - START_TIME
    val afterUsedMem: Double = (Runtime.getRuntime
        .totalMemory() - Runtime.getRuntime.freeMemory()) /
        1048576
    val usage: String = if (afterUsedMem > 1) " bytes" else " byte"
    println("Memory usage (bytes): " + afterUsedMem + usage)
    println("Run-time (ms): " + DELTA_TIME + " ms")
  }
  /**
    *
    *
    *
    *
    *
    *
    */
//TODO: Finish wiriting this driver
  /**
    *
    *
    *
    *
    *
    *
    */
//TODO: Finish wiriting this driver

}
