---
layout: home

---

# sbt-mima-version-check - Plugin to Automate which Mima Versions to Check [![Build Status](https://travis-ci.com/ChristopherDavenport/sbt-mima-version-check.svg?branch=master)](https://travis-ci.com/ChristopherDavenport/sbt-mima-version-check) [![Maven Central](https://maven-badges.herokuapp.com/maven-central/io.chrisdavenport/sbt-mima-version-check_2.12/badge.svg)](https://maven-badges.herokuapp.com/maven-central/io.chrisdavenport/sbt-mima-version-check_2.12)

## Quick Start

To use sbt-mima-version-check in an existing SBT project with version 1.0 or greater, add the following dependencies to your plugins.sbt depending on your needs:

```scala
addSbtPlugin("io.chrisdavenport" % "sbt-mima-version-check" % "<version>")
```

## Explanation

This sets the versions to check your library for binary compatibility with based on semantic versioning.

So lets take some common cases.

* 0.0.x - Will Not Check These Versions Are Generally not considered anything but exploratory
* 0.1.2 - Will check 0.1.1, and 0.1.0
* 0.2.1 - Will check 0.2.0, minor versions with major version zero are not considered compatible
* 1.0.3 - Will check 1.0.2, 1.0.1, 1.0.0 - All patch versions are considered compatible with each other
* 1.2.2 - Will check 1.2.1, 1.2.0, 1.1.0, 1.0.0 - Minor version across a major version should be backwards compatible with each other
* 2.0.1 - Will check 2.0.0 - Major version do not typically carry binary compatiblity guarantees
