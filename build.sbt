lazy val `sbt-mima-version-check` = project.in(file("."))
  .disablePlugins(MimaPlugin)
  .enablePlugins(NoPublishPlugin)
  .aggregate(core, docs)

lazy val core = project.in(file("core"))
  .enablePlugins(SbtPlugin)
  .settings(
    name := "sbt-mima-version-check",
    addSbtPlugin("com.typesafe" % "sbt-mima-plugin" % "0.9.2"),

    scriptedLaunchOpts := { scriptedLaunchOpts.value ++
      Seq("-Xmx1024M", "-Dplugin.version=" + version.value)
    },
    scriptedBufferLog := false,
    test := {
      (Test / test).value
      scripted.toTask("").value
    }
  )

lazy val docs = project.in(file("docs"))
  .disablePlugins(MimaPlugin)
  .enablePlugins(MicrositesPlugin)
  .enablePlugins(MdocPlugin)
  .enablePlugins(NoPublishPlugin)
  .settings{
    import microsites._
    List(
      micrositeName := "sbt-mima-version-check",
      micrositeDescription := "Plugin to Automate which Mima Versions to Check",
      micrositeAuthor := "Christopher Davenport",
      micrositeGithubOwner := "ChristopherDavenport",
      micrositeGithubRepo := "sbt-mima-version-check",
      micrositeBaseUrl := "/sbt-mima-version-check",
      micrositeDocumentationUrl := "https://www.javadoc.io/doc/io.chrisdavenport/sbt-mima-version-check_2.12",
      micrositeGitterChannelUrl := "ChristopherDavenport/libraries",
      micrositeFooterText := None,
      micrositeHighlightTheme := "atom-one-light",
      micrositePalette := Map(
        "brand-primary" -> "#3e5b95",
        "brand-secondary" -> "#294066",
        "brand-tertiary" -> "#2d5799",
        "gray-dark" -> "#49494B",
        "gray" -> "#7B7B7E",
        "gray-light" -> "#E5E5E6",
        "gray-lighter" -> "#F4F3F4",
        "white-color" -> "#FFFFFF"
      ),
      Compile / scalacOptions --= Seq(
        "-Xfatal-warnings",
        "-Ywarn-unused-import",
        "-Ywarn-numeric-widen",
        "-Ywarn-dead-code",
        "-Ywarn-unused:imports",
        "-Xlint:-missing-interpolator,_"
      ),
      libraryDependencies += "com.47deg" %% "github4s" % "0.31.0",
      micrositePushSiteWith := GitHub4s,
      micrositeGithubToken := sys.env.get("GITHUB_TOKEN"),
      micrositeExtraMdFiles := Map(
          file("CHANGELOG.md")        -> ExtraMdFileConfig("changelog.md", "page", Map("title" -> "changelog", "section" -> "changelog", "position" -> "100")),
          file("CODE_OF_CONDUCT.md")  -> ExtraMdFileConfig("code-of-conduct.md",   "page", Map("title" -> "code of conduct",   "section" -> "code of conduct",   "position" -> "101")),
          file("LICENSE")             -> ExtraMdFileConfig("license.md",   "page", Map("title" -> "license",   "section" -> "license",   "position" -> "102"))
      )
    )
  }
  .dependsOn(core)

inThisBuild(List(
  scalaVersion := "2.12.15",

  organization := "io.chrisdavenport",
  scalacOptions in (Compile, doc) ++= Seq(
      "-groups",
      "-sourcepath", (baseDirectory in LocalRootProject).value.getAbsolutePath,
      "-doc-source-url", "https://github.com/ChristopherDavenport/sbt-mima-version-check/blob/v" + version.value + "€{FILE_PATH}.scala"
  ),
  scmInfo := Some(
    ScmInfo(
      url("https://github.com/ChristopherDavenport/sbt-mima-version-check"),
      "git@github.com:ChristopherDavenport/sbt-mima-version-check.git"
    )
  ),
  homepage := Some(url("https://github.com/ChristopherDavenport/sbt-mima-version-check")),
  licenses += ("MIT", url("http://opensource.org/licenses/MIT")),
  pomIncludeRepository := { _ => false },
  developers := List(
    Developer("ChristopherDavenport", "Christopher Davenport", "chris@christopherdavenport.tech", url("https://github.com/ChristopherDavenport"))
  ),
))


