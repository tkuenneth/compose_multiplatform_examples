import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    kotlin("multiplatform")
    id("org.jetbrains.compose")
}

group = "eu.thomaskuenneth.swingpaneldemo"
version = "1.0-SNAPSHOT"

repositories {
    google()
    mavenCentral()
    maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
}

tasks {
    register<Exec>("runSwingDemo") {
        dependsOn("build")
        commandLine(
            "java",
            "-classpath",
            "./build/libs/SwingPanelDemo-jvm-1.0-SNAPSHOT.jar",
            "SwingDemo"
        )
    }
}

kotlin {
    jvm {
        compilations.all {
            kotlinOptions.jvmTarget = "11"
        }
        withJava()
    }
    sourceSets {
        val jvmMain by getting {
            dependencies {
                implementation(compose.desktop.currentOs)
            }
        }
        val jvmTest by getting
    }
}

compose.desktop {
    application {
        mainClass = "MainKt"
        nativeDistributions {
            targetFormats(
                TargetFormat.Dmg,
                TargetFormat.Msi,
                TargetFormat.Deb
            )
            packageName = "SwingPanelDemo"
            packageVersion = "1.0.0"
        }
    }
}
