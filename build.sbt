name := "Hangman"

version := "0.1"

scalaVersion := "2.10.1"

scalacOptions ++= Seq("-deprecation", "-feature")

libraryDependencies += "org.scalatest" %% "scalatest" % "1.9.1" % "test"

libraryDependencies += "junit" % "junit" % "4.10" % "test"

// this will put the files into a folder called lib_managed
retrieveManaged := true
