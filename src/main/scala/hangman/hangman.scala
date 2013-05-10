package hangman

import dictionary.loadDictionary

object Main extends App
{
	Console.println("*~* Hangman *~*")
	
	Console.print("Loading dictionary...")
	val dictionary = loadDictionary
	
	Console.println("Dictionary loaded! First word is " + dictionary(0))
	
	Console.println("Starting up main loop.  Hit 'Ctrl-C' to exit the application.")
	mainLoop()
	
	def mainLoop() : Nothing = {
	  Console.print("Please enter a character: ")
	  val char = Console.readChar
	  Console.println("You entered the character: " + char)
	  mainLoop
	}
}
