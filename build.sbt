name := """credit-report"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.11.7"

libraryDependencies ++= Seq(
  javaJdbc,
  cache,
  javaWs,
   "mysql" % "mysql-connector-java" % "5.1.18"
)
// Java project. Don't expect Scala IDE
 EclipseKeys.projectFlavor := EclipseProjectFlavor.Java           
 // Use .class files instead of generated .scala files for views and routes
 EclipseKeys.createSrc := EclipseCreateSrc.ValueSet(EclipseCreateSrc.ManagedClasses, EclipseCreateSrc.ManagedResources)
 // Compile the project before generating Eclipse files, so that .class files for views and routes are present   
 EclipseKeys.preTasks := Seq(compile in Compile)                  

fork in run := true