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

tasks.register<JavaExec>("run") {
	group = "run"
	mainClass.set("net.luis.Main")
	classpath(sourceSets.main.get().runtimeClasspath)
	standardInput = System.`in`
	args()
	jvmArgs()
}

tasks.register<JavaExec>("runTest") {
	group = "run"
	mainClass.set("net.luis.Main")
	classpath(sourceSets.test.get().runtimeClasspath)
	standardInput = System.`in`
	args()
	jvmArgs()
}
