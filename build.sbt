name := "SparkLabs"
version := "0.1"
organization := "infobarbosa.github.com"
scalaVersion := "2.11.8"
val sparkVersion = "2.3.2"

libraryDependencies ++= Seq(
  "org.apache.spark" %% "spark-core" % sparkVersion,
  "org.apache.spark" %% "spark-sql" % sparkVersion
)
