package hangman

import dictionary.loadDictionary
import scala.util.Random
import com.sun.org.apache.xml.internal.utils.CharKey

object Main extends App {

  type Guess = Char


  Console.println("*~* Hangman *~*")

  Console.print("Loading dictionary...")
  val dictionary = loadDictionary

  Console.println("Dictionary loaded!")

  Console.println("Starting game.  Hit 'Ctrl-C' to exit the application.")

  newGame()

  def newGame(): Nothing = {


    val targetWord: String = randomDictionaryWord
    val neededLetters: Set[Guess] = targetWord.toSet

    case class Guesses(good: Set[Guess], bad: Set[Guess]) {
      def add(guess: Guess): Guesses = {
        val goodGuess = neededLetters.contains(guess)

        if (goodGuess)
          Guesses(good + guess, bad)
        else
          Guesses(good, bad + guess)
      }

      override def toString() = bad.union(good).mkString(", ")
    }


    def startTurns(): Nothing = {

      Console.println(s"********* New Game *********")
      Console.println(s"-- DEBUG: The target word is $targetWord")

      Console.println("Word: " + printTargetWord(targetWord, Set()))

      userTurn(2, Guesses(Set(), Set()).add(userGuess))
    }

    def userTurn(turnCount: Int, guesses: Guesses): Nothing = {

      //this line uses string interpolation.  The 's' in front is its cue
      //http://docs.scala-lang.org/overviews/core/string-interpolation.html for more information
      Console.println(s"********* Turn $turnCount *********")
      Console.println("Letters guessed: " + guesses)

      Console.println("Word: " + printTargetWord(targetWord, guesses.good))

      val updatedGuesses = guesses.add(userGuess)

      if (wordFound(neededLetters, updatedGuesses.good))
        endGame(true)
      else if (updatedGuesses.bad.count(x => true) > 5)
        endGame(false)
      else
        userTurn(turnCount + 1, updatedGuesses)

    }

    def endGame(gameWon: Boolean) = {
      if (gameWon) {
        println("~*~ Congratulations! You found the word! ~*~")
        println(ascii.peace)
      } else
        println(ascii.hungMan)

      println()
      newGame()
    }

    startTurns()

    newGame()
  }

  def randomDictionaryWord: String = {
    //a random number may come back as negative, so we need to take absolute value
    val nextInt = Math.abs(Random.nextInt())

    // take 'mod' of the random number by length of the dictionary to ensure it exists
    val index = nextInt % dictionary.length

    dictionary(index)
  }

  def printTargetWord(targetWord: String, guessedLetters: Set[Guess]): String = {
    //make a new list of Guess. display 'c' if present in guessedLetters, otherwise '_'
    (targetWord map (c => {
      if (guessedLetters.contains(c)) c
      else '_'
    })) mkString " "
  }

  def wordFound(neededLettersSet: Set[Guess], guessedLetters: Set[Guess]): Boolean =
    neededLettersSet.subsetOf(guessedLetters)

  def guessedWrong(neededLettersSet: Set[Guess], guessedLetters: Set[Guess]): Set[Guess] =
    guessedLetters -- neededLettersSet

  def userGuess: Guess = {
    try {
      Console.print("Please enter a Character: ")
      Console.readChar() match {
        case c if c.isLetter => c.toUpper
        case _ => {
          Console.println("That was not a known letter.")
          userGuess
        }
      }
    }
    catch {
      case e: Exception => userGuess
    }

  }
}
