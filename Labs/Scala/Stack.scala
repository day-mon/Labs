


/**
  * Simple Stack interface.
  * @author Stephen J. Sarma-Weierman
  * @version 1.0
  */
trait Stack[T] {

  /**
    * Adds something to the top of the stack.
    * @param obj - the object to be added.
    */
  def push(obj: T): T

  /**
    * Returns a refernce to the top of the stack.
    * Does not modify the stack.
    * @return a reference to the top of the stack.
    */
  def peek(): T

  /**
    * Removes the top element from the stack.
    * @return a reference to the element that was on the top of the stack.
    */
  def pop(): T

  /**
    * Determines if the stack is empty.
    * @return true if the stack is empty.
    */
  def empty(): Boolean

}
