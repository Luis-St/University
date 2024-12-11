plugins {
    id("java")
}

repositories {
    mavenCentral()
}

dependencies {
	// Google
	implementation("com.google.guava:guava:${project.properties["GoogleGuava"].toString()}") {
		exclude(group = "org.checkerframework")
		exclude(group = "com.google.code.findbugs")
		exclude(group = "com.google.errorprone")
		exclude(group = "com.google.j2objc")
	} // Utility
	// Apache
	implementation("org.apache.logging.log4j:log4j-api:${project.properties["Log4jAPI"].toString()}") // Logging
	implementation("org.apache.logging.log4j:log4j-core:${project.properties["Log4jCore"].toString()}") // Logging
	implementation("org.apache.commons:commons-lang3:${project.properties["ApacheLang"].toString()}") // Utility
	// Other
	implementation("org.jetbrains:annotations:${project.properties["JetBrainsAnnotations"].toString()}") // Annotations
	
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}

tasks.register<JavaExec>("runLibraryTest") {
	group = "run"
	mainClass.set("net.luis.library.Main")
	classpath(sourceSets.test.get().runtimeClasspath)
	standardInput = System.`in`
	enableAssertions = true
	args()
	jvmArgs()
}

tasks.register<JavaExec>("runMessengerTest") {
	group = "run"
	mainClass.set("net.luis.messenger.Main")
	classpath(sourceSets.test.get().runtimeClasspath)
	standardInput = System.`in`
	enableAssertions = true
	args()
	jvmArgs()
}

tasks.register<JavaExec>("runCompositeTest") {
	group = "run"
	mainClass.set("net.luis.composite.Main")
	classpath(sourceSets.test.get().runtimeClasspath)
	standardInput = System.`in`
	enableAssertions = true
	args()
	jvmArgs()
}
