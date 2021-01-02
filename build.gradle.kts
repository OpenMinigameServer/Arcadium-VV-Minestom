plugins {
    kotlin("jvm") version "1.4.21"
}

group = "io.github.openminigameserver"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven { setUrl("https://jitpack.io") }
    maven { setUrl("https://libraries.minecraft.net") }
    maven { setUrl("https://repo.spongepowered.org/maven") }
    maven {
        name = "sonatype-oss"
        setUrl("https://oss.sonatype.org/content/repositories/snapshots/")
    }
}

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation(minestom("82631fc6f8")) {
        exclude(module = "fastutil")
    }

    //ViaVersion
    api("org.yaml:snakeyaml:1.26")
    api("it.unimi.dsi:fastutil:8.3.1")
    implementation("com.github.ViaVersion.ViaVersion:viaversion:127cae80bd")
    implementation("com.github.ViaVersion.ViaBackwards:viabackwards-all:673239108c")
    implementation("com.github.steveice10:opennbt:d50274d")
}

configure<SourceSetContainer> {
    named("main") {
        java.srcDir("src/main/kotlin")
    }
}

fun minestom(commit: String): String {
    return "com.github.Minestom:Minestom:$commit"
}
