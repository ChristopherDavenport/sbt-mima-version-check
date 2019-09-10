---
layout: home

---

# sbt-mima-version-check - Plugin to Automate which Mima Versions to Check [![Build Status](https://travis-ci.com/ChristopherDavenport/sbt-mima-version-check.svg?branch=master)](https://travis-ci.com/ChristopherDavenport/sbt-mima-version-check) [![Maven Central](https://maven-badges.herokuapp.com/maven-central/io.chrisdavenport/sbt-mima-version-check_2.12/badge.svg)](https://maven-badges.herokuapp.com/maven-central/io.chrisdavenport/sbt-mima-version-check_2.12)

## Quick Start

To use sbt-mima-version-check in an existing SBT project with Scala 2.11 or a later version, add the following dependencies to your
`build.sbt` depending on your needs:

```scala
libraryDependencies ++= Seq(
  "io.chrisdavenport" %% "sbt-mima-version-check" % "<version>"
)
```