plugins{
    kotlin("jvm")
    kotlin("kapt")
}

dependencies {
    implementation(project(":Annotation"))
    implementation("com.google.auto.service:auto-service-annotations:1.0.1")
    kapt("com.google.auto.service:auto-service:1.0.1")
}

java{
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}