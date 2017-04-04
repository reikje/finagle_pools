lazy val root =
  Project("finagle_pools", file("."))
    .settings(
      description := "Finagle Pools Test",
      organization := "com.github.reikje",
      name := "finagle_pools",
      scalaVersion := "2.11.8",
      libraryDependencies ++= Seq(
        "com.twitter" %% "finagle-core" % "6.42.0"
      )
    )

