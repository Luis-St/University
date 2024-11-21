plugins {
    id("java")
}

repositories {
    mavenCentral()
}

dependencies {}

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
