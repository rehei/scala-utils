name := "scala-utils"

scalaVersion := "2.11.7"

version := sys.props.getOrElse("tag", default = "0.0.0")
organization := "com.github.rehei"

resolvers += "snapshots" at "https://oss.sonatype.org/content/repositories/snapshots"
resolvers += "staging" at "https://oss.sonatype.org/content/repositories/staging"
resolvers += "releases" at "https://oss.sonatype.org/content/repositories/releases"
resolvers += Resolver.bintrayRepo("rehei", "maven")

libraryDependencies ++= {
  val liftVersion = "2.6.2"
  Seq(
    "com.novocode" % "junit-interface" % "0.11" % "test",
    "org.mockito" % "mockito-core" % "2.0.111-beta",
    "org.specs2" %% "specs2-core" % "3.8.4",
    "org.specs2" %% "specs2-mock" % "3.8.4",
    "org.specs2" %% "specs2-junit" % "3.8.4"
  )
}

EclipseKeys.withSource := true

