import java.util.Vector
import java.util.EmptyStackException


class VectorStack[T] extends Stack[T] {

  private var stack: Vector[T] = new Vector[T]()

  /**
    * Adds something to the top of the stack.
    * @param obj - the object to be added.
    */
  def push(obj: T): T = {
    stack.add(obj)
    obj
  }

  /**
    * Returns a refernce to the top of the stack.
    * Does not modify the stack.
    * @return a reference to the top of the stack.
    */
  def peek(): T = {
    if (empty()) throw new EmptyStackException()
    stack.get(stack.size - 1)
  }

  /**
    * Removes the top element from the stack.
    * @return a reference to the element that was on the top of the stack.
    */
  def pop(): T = {
    if (empty()) {
      throw new EmptyStackException()
    }
    val rem: T = stack.get(stack.size - 1)
    stack.remove(stack.size - 1)
    rem
  }

  /**
    * Determines if the stack is empty.
    * @return true if the stack is empty.
    */
  def empty(): Boolean = stack.isEmpty

  /**
    * Uses Vectors lastIndexOf method to find the last index of T.
    * Due to the nature of the data structure to find the index,
    * we will need to subtract the index and the size of the stack
    * @param obj
    * @return -1 if isnt found; index if found.
    */
  def search(obj: T): Int = {
    val index: Int = stack.lastIndexOf(obj)
    if (index < 0) -1 else stack.size - index
  }

}
