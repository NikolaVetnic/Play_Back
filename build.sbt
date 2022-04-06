name := """playapp"""
organization := "com.example"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.13.8"

libraryDependencies ++= Seq(
  guice,
  "com.h2database" % "h2" % "2.1.210",
  evolutions,
  jdbc,
  javaJpa,
  "org.hibernate" % "hibernate-core" % "5.4.33"
)

fork := true
javaOptions += "-Dplay.editor=http://localhost:63342/api/file/?file=%s&line=%s"

PlayKeys.externalizeResourcesExcludes += baseDirectory.value / "conf" / "META-INF" / "persistence.xml"