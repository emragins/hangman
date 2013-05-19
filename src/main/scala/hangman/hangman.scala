package hangman

import dictionary.loadDictionary
import scala.util.Random

object Main extends App {
  Console.println("*~* Hangman *~*")

  Console.print("Loading dictionary...")
  val dictionary = loadDictionary

  Console.println("Dictionary loaded!")

  Console.println("Starting game.  Hit 'Ctrl-C' to exit the application.")

  newGame()

  def newGame(): Nothing = {

    val targetWord : String = randomDictionaryWord
    val neededLettersSet : Set[Char] = targetWord.toSet
    
    def startTurns() : Nothing = {
      
      Console.println(s"********* New Game *********")
      Console.println(s"-- DEBUG: The target word is $targetWord")
      Console.println(s"The word you are looking for has ${targetWord.length} letters")
      Console.print("Please enter a character: ")
      val currentGuess = Console.readChar
      
      userTurn(2, Set(currentGuess))
    }
    
    def userTurn(turnCount: Int, guessedLetters: Set[Char]): Nothing = {

      //this line uses string interpolation.  The 's' in front is its cue
      //http://docs.scala-lang.org/overviews/core/string-interpolation.html for more information
      Console.println(s"********* Turn $turnCount *********")
      Console.println("Letters guessed: " + guessedLetters.mkString(", "))

      Console.println("Word: " + printTargetWord(targetWord, guessedLetters))
      
      Console.print("Please enter a character: ")
      val currentGuess = Console.readChar

      val updatedGuesses = guessedLetters + currentGuess
      if(wordFound(neededLettersSet, updatedGuesses))
        endGame(true)
      else  //this else is needed for it to understand the implicit 'end of function'
        userTurn(turnCount + 1, updatedGuesses)
    }

    def endGame(gameWon: Boolean) = 
    {
      if(gameWon)
        println("~*~ Congratulations! You found the word! ~*~")
      else 
        println(ascii.hungMan)
        println()
        newGame()
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
  
  def printTargetWord(targetWord: String, guessedLetters : Set[Char]) : String = {
    //make a new list of Char. display 'c' if present in guessedLetters, otherwise '_'
    (targetWord map (c =>
      {
        if(guessedLetters.contains(c)) c
        else '_'
      
    })) mkString " "
  }
  
  def wordFound(neededLettersSet: Set[Char], guessedLetters : Set[Char]) : Boolean = 
    neededLettersSet.subsetOf(guessedLetters)
}
