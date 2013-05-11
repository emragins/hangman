package hangman

import dictionary.loadDictionary
import scala.util.Random

object Main extends App {
  Console.println("*~* Hangman *~*")

  Console.print("Loading dictionary...")
  val dictionary = loadDictionary

  Console.println("Dictionary loaded! First word is " + dictionary(0))

  Console.println("Starting up main loop.  Hit 'Ctrl-C' to exit the application.")

  newGame()

  def newGame(): Nothing = {

    val targetWord = randomDictionaryWord
    
    def startTurns() : Nothing = {
      
      Console.println(s"********* Starting Game *********")
      Console.println(s"The word you are looking for has ${targetWord.length} letters")
      Console.print("Please enter a character: ")
      val currentGuess = Console.readChar
      
      userTurn(2, List(currentGuess))
    }
    
    def userTurn(turnCount: Int, guessedLetters: List[Char]): Nothing = {

      //this line uses string interpolation.  The 's' in front is its cue
      //http://docs.scala-lang.org/overviews/core/string-interpolation.html for more information
      Console.println(s"********* Turn $turnCount *********")
      Console.println("Letters guessed: " + guessedLetters.mkString(", "))

      Console.print("Please enter a character: ")
      val currentGuess = Console.readChar

      userTurn(turnCount + 1,  guessedLetters :+ currentGuess)
    }

    startTurns()
    
    
    newGame()
  }

  def randomDictionaryWord: String = {
    //a random number may come back as negative, so we need to take absolute value
    val nextInt = Math.abs(Random.nextInt)

    // take 'mod' of the random number by length of the dictionary to ensure it exists
    val index =  nextInt % dictionary.length

    dictionary(index)
  }
}
