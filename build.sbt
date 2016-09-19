name := """scala-tut"""

version := "1.0"

scalaVersion := "2.11.4"

//unmanagedJars in Compile += file("paranamer-2.7.jar")

libraryDependencies ++= Seq(
     "org.json4s"  % "json4s-jackson_2.10" % "3.2.11"
)

//testOptions += Tests.Argument(TestFrameworks.JUnit, "-v")

//fork in run := true