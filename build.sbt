val checkstyle = taskKey[Unit]("Execute checkstyle")

checkstyle := {
    val v = ("../judgels/scripts/execute-checkstyle.sh" !)
    if (v != 0) {
        sys.error("Failed")
    }
}

lazy val api = (project in file("."))
    .settings(
        name := "api",
        version := IO.read(file("version.properties")).trim,
        scalaVersion := "2.11.7",
        libraryDependencies ++= Seq(
            "com.puppycrawl.tools" % "checkstyle" % "6.8.1",
            "com.google.code.gson" % "gson" % "2.3.1",
            "com.google.guava" % "guava" % "18.0",
            "commons-io" % "commons-io" % "2.4",
            "org.apache.httpcomponents" % "httpclient" % "4.5",
            "org.apache.commons" % "commons-lang3" % "3.3.2"
        )
    )
