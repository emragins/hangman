package hangman

import dictionary.loadDictionary

object Main extends App
{
	Console.println("*~* Hangman *~*")
	
	Console.print("Loading dictionary...")
	val dictionary = loadDictionary
	
	Console.println("Dictionary loaded! First word is " + dictionary(0))
	
	Console.println("Starting up main loop.  Hit 'Ctrl-C' to exit the application.")
	mainLoop(0)
	
	def mainLoop(turnCount: Int) : Nothing = {
	  
	  //this line uses string interpolation.  The 's' in front is its cue
	  //http://docs.scala-lang.org/overviews/core/string-interpolation.html for more information
	  Console.println(s"********* Turn $turnCount *********")
	  
	  Console.print("Please enter a character: ")
	  val char = Console.readChar
	  Console.println("You entered the character: " + char)
	  
	  mainLoop(turnCount + 1)
	}
}
