package io.chrisdavenport.sbtmimaversioncheck

import sbt._

object MimaVersionCheckKeys extends MimaVersionCheckKeys

class MimaVersionCheckKeys {
  final val mimaVersionCheckExtraVersions =
    settingKey[Set[String]]("Extra Versions to check Mima compatibility for")
  final val mimaVersionCheckExcludedVersions =
    settingKey[Set[String]]("Versions Normally checked for to exclude from Mima checks")
}