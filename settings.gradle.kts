rootProject.name = "DDatJava"

pluginManagement {
    repositories {
        maven("https://maven.aliyun.com/repository/gradle-plugin")
    }
}

dependencyResolutionManagement {
    repositories {
        maven { url = uri("https://maven.aliyun.com/repository/central") }
        maven { url = uri("https://maven.aliyun.com/repository/public") }
        mavenCentral()
        google()
    }
}

include(":Core")
include(":Annotation")
include(":AnnotationProcessor")