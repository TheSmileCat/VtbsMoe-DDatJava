plugins {
    kotlin("jvm")
    kotlin("kapt")
    `maven-publish`
}

group = "moe.vtbs"
version = "2.0.0"

dependencies {
    testImplementation(kotlin("test"))
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.2")
    testImplementation("org.junit.jupiter:junit-jupiter-engine:5.8.2")
    testRuntimeOnly("org.jetbrains.kotlinx:kotlinx-coroutines-swing:1.6.2")

    implementation("com.google.code.gson:gson:2.9.0")
    implementation("org.slf4j:slf4j-log4j12:1.7.36")
    implementation("com.typesafe:config:1.4.2")
    implementation("org.java-websocket:Java-WebSocket:1.5.3")
    implementation("com.google.http-client:google-http-client:1.42.0")
    implementation("commons-io:commons-io:2.11.0")

    implementation("org.yaml:snakeyaml:1.30")
    implementation("com.squareup.okhttp3:okhttp:4.10.0")

    //kotlin
    implementation(kotlin("stdlib"))
    implementation(kotlin("reflect"))
    implementation(kotlin("serialization"))
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.3")
    compileOnly(fileTree(projectDir.resolve("lib/compileOnly")))

    implementation(project(":Annotation"))
    kapt(project(":AnnotationProcessor"))
}

tasks.compileKotlin {
    kotlinOptions {
        jvmTarget = "1.8"
        freeCompilerArgs = listOf("-opt-in=kotlin.RequiresOptIn", "-Xjvm-default=all")
    }
    java.sourceCompatibility = JavaVersion.VERSION_1_8
    java.targetCompatibility = JavaVersion.VERSION_1_8
}

tasks.test {
    useJUnitPlatform()
    workingDir = projectDir.resolve("run").apply {
        if (!exists()) mkdirs()
    }
}

tasks.jar {
    archiveFileName.set("${project.name}-${project.version}-api.jar")
    finalizedBy(tasks["fatJar"])
}

task<Jar>("fatJar") {
    manifest {
        from("src/main/resources/META-INF/MANIFEST.MF")
    }
    duplicatesStrategy = DuplicatesStrategy.WARN
    archiveFileName.set("${project.name}-${project.version}.jar")
    from(zipTree(tasks.jar.get().archiveFile.get().asFile))
    from(configurations.runtimeClasspath.get().files.map { if (it.isDirectory) it else zipTree(it) })
}

task<Jar>("sourcesJar") {
    from(sourceSets["main"].allSource)
    archiveClassifier.set("sources")
}
repositories {
    mavenCentral()
}

publishing {
    repositories {
        maven("${rootProject.projectDir}/repo/")
    }

    publications {
        create<MavenPublication>("maven") {
            artifactId = "dd-home-api"
            from(components["java"])
            artifact(tasks["sourcesJar"])
        }
    }
}