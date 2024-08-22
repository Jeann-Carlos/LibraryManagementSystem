plugins {
    application
    java
}

group = "Book-Manager"
version = "0.0.1-A"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.xerial:sqlite-jdbc:3.41.2.2")
}

application {
    mainClass.set("BookManager.BookMan")

}

tasks.jar {
    manifest {
        attributes["Main-Class"] = application.mainClass.get()
    }
}

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}
