lazy val root = (project in file("."))

lazy val zeroZeroZero = project.in(file("zeroZeroZero"))
  .settings(
    version := "0.0.0",
    
    TaskKey[Unit]("check") := {
      val expected: Set[String] = Set()
      testVersion(version.value, expected, mimaPreviousArtifacts.value)
    }
  )

lazy val zeroZeroPatch = project.in(file("zeroZeroPatch"))
  .settings(
    version := "0.0.3",
    
    TaskKey[Unit]("check") := {
      val expected: Set[String] = Set()
      testVersion(version.value, expected, mimaPreviousArtifacts.value)
    }
  )

lazy val zeroMinorZero = project.in(file("zeroMinorZero"))
  .settings(
    version := "0.1.0",
    
    TaskKey[Unit]("check") := {
      val expected: Set[String] = Set()
      testVersion(version.value, expected, mimaPreviousArtifacts.value)
    }
  )

lazy val zeroMinorPatch = project.in(file("zeroMinorPatch"))
  .settings(
    version := "0.1.2",
    
    TaskKey[Unit]("check") := {
      val expected: Set[String] = Set(
        "0.1.1",
        "0.1.0"
      )
      testVersion(version.value, expected, mimaPreviousArtifacts.value)
    }
  )

lazy val majorZeroZero = project.in(file("majorZeroZero"))
  .settings(
    version := "2.0.0",
    
    TaskKey[Unit]("check") := {
      val expected: Set[String] = Set()
      testVersion(version.value, expected, mimaPreviousArtifacts.value)
    }
  )

lazy val majorMinorZero = project.in(file("majorMinorZero"))
  .settings(
    version := "2.1.0",
    
    TaskKey[Unit]("check") := {
      val expected: Set[String] = Set(
        "2.0.0"
      )
      testVersion(version.value, expected, mimaPreviousArtifacts.value)
    }
  )

lazy val majorZeroPatch = project.in(file("majorZeroPatch"))
  .settings(
    version := "3.0.5",
    
    TaskKey[Unit]("check") := {
      val expected: Set[String] = Set(
        "3.0.4",
        "3.0.3",
        "3.0.2",
        "3.0.1",
        "3.0.0"
      )
      testVersion(version.value, expected, mimaPreviousArtifacts.value)
    }
  )

lazy val majorMinorPatch = project.in(file("majorMinorPatch"))
  .settings(
    version := "2.2.3",

    TaskKey[Unit]("check") := {
      val expected: Set[String] = Set(
        "2.0.0",
        "2.1.0",
        "2.2.0",
        "2.2.1",
        "2.2.2"
      )
      testVersion(version.value, expected, mimaPreviousArtifacts.value)
    }
  )

lazy val sbtPluginModule = project.in(file("sbtPluginModule"))
  .settings(
    version := "2.2.3",
    sbtPlugin := true,
    organization := "bar",
    name := "foo",
    TaskKey[Unit]("check") := {
      val moduleName = mimaPreviousArtifacts
        .value
        .head
        .name
      val expected = "foo_2.13_1.0"
      if (moduleName == expected) ()
      else throw new Throwable(s"Module name incorrect - got $moduleName - expected $expected")
    }
  )

import sbt.librarymanagement.ModuleID

def testVersion(versionTested: String, expected: Set[String], mimaPrevArtifacts: Set[ModuleID]) = {
  val revisions = mimaPrevArtifacts
    .map(_.revision)
  if (revisions.forall(s => expected.contains(s)) && expected.forall(s => revisions.contains(s))) ()
  else throw new Throwable(
      "unexpected output: for version $versionTested\n\n" + 
      revisions + "\n\n" +
      "was expecting\n\n" +
      expected
    )
}

inThisBuild(List(
  scalaVersion := "2.13.0",
  organization := "foo",
  name := "bar",
))