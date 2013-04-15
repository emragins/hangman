package hangman

import org.scalatest.FunSuite

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner


@RunWith(classOf[JUnitRunner])
class HangmanSuite extends FunSuite {

  test("always true") {
    val message = "hello, world"
    assert(true)
  }
}