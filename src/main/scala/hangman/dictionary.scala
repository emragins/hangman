package hangman

//This file compliments of coursera

object dictionary {

  //looks in the src/main/resources/hangman folder for a file called linuxwords.txt
  val dictionaryPath = List("hangman", "linuxwords.txt")

  def loadDictionary = {
    val wordstream = Option {
      getClass.getClassLoader.getResourceAsStream(dictionaryPath.mkString("/"))
    } orElse {
      common.resourceAsStreamFromSrc(dictionaryPath)
    } getOrElse {
      sys.error("Could not load word list, dictionary file not found")
    }
    try {
      val s = io.Source.fromInputStream(wordstream)
      s.getLines.toList
    } catch {
      case e: Exception =>
        println("Could not load word list: " + e)
        throw e
    } finally {
      wordstream.close()
    }
  }

}
