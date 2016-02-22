name := "play-java-websocket-chat"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.11.1"

libraryDependencies ++= Seq(
  javaJdbc,
  javaEbean,
  cache,
  javaWs,
  filters,
  "com.couchbase.client" % "couchbase-client" % "1.4.2",
  "com.google.code.gson" % "gson" % "2.2"
)
