lazy val root = (project in file(".")).
  settings(
    version := "2.2.3",
    scalaVersion := "2.13.0",
    organization := "askdfjakadjf",
    name := "fasdajhfdjah",
    
    TaskKey[Unit]("check") := {
      val expected = Set(
        "2.0.0",
        "2.1.0",
        "2.2.0",
        "2.2.1",
        "2.2.2"
      )
      val revisions = mimaPreviousArtifacts.value
        .map(_.revision)
      if (revisions.forall(s => expected.contains(s))) ()
      else sys.error(
        "unexpected output: \n\n" + 
        revisions + "\n\n" +
        "was expecting\n\n" +
        expected
      )
    }

  )