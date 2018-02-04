name := "akka-sandbox"

organization := "com.github.zjiajun"

version := "0.1"

scalaVersion := "2.12.4"

val akkaVersion = "2.5.9"

libraryDependencies ++= Seq(
  "com.typesafe.akka"     %% "akka-actor"          % akkaVersion,
  "com.typesafe.akka"     %% "akka-testkit"        % akkaVersion    % "test",
  "org.scalatest"         %%  "scalatest"          % "3.0.0"        % "test",
)
