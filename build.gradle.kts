import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "3.2.4"
	id("io.spring.dependency-management") version "1.1.4"
	id("org.flywaydb.flyway") version "9.22.1"
	kotlin("plugin.serialization") version "1.9.20"

	kotlin("jvm") version "1.9.23"
	kotlin("plugin.spring") version "1.9.23"
}

group = "com.singidunum"
version = "0.0.1-SNAPSHOT"

java {
	sourceCompatibility = JavaVersion.VERSION_17
}

repositories {
	mavenCentral()
}

extra["springCloudVersion"] = "2023.0.0"

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.springframework.cloud:spring-cloud-starter-netflix-eureka-client")
	implementation("org.jetbrains.kotlin:kotlin-reflect")

	implementation("com.squareup.okhttp3:okhttp:4.12.0")
	implementation("com.squareup.okhttp3:logging-interceptor:4.12.0")
	implementation("com.squareup.okhttp3:mockwebserver:4.12.0")
	implementation("com.squareup.retrofit2:retrofit:2.9.0")
	implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.0")
	implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.0")
	implementation("com.jakewharton.retrofit:retrofit2-kotlinx-serialization-converter:1.0.0")

	implementation("org.flywaydb:flyway-core")
	implementation("org.postgresql:postgresql")

	implementation("org.jetbrains.exposed:exposed-core:0.44.1")
	implementation("org.jetbrains.exposed:exposed-dao:0.44.1")
	implementation("org.jetbrains.exposed:exposed-jdbc:0.44.1")
	implementation("org.jetbrains.exposed:exposed-java-time:0.44.1")

	testImplementation("org.springframework.boot:spring-boot-starter-test")
}

dependencyManagement {
	imports {
		mavenBom("org.springframework.cloud:spring-cloud-dependencies:${property("springCloudVersion")}")
	}
}

val jdbcUrl = "jdbc:postgresql://localhost:5432/users_db?sslmode=disable"
val dbUser = "users_admin"
val dbPassword = "users_admin"
val dbSchema = "public"

tasks {
	// flyway
	flyway {
		url = jdbcUrl
		user = dbUser
		password = dbPassword
		schemas = arrayOf(dbSchema)
		baselineOnMigrate = true
		driver = "org.postgresql.Driver"
		table = "flyway_schema_history"
		baselineVersion = "0"
		locations = arrayOf("filesystem:$projectDir/src/main/resources/db/migration")
		placeholders = mapOf("region" to "fra", "environment" to "local")
	}

	val startDb = register<Exec>("dbUp") {
		group = "docker"
		commandLine("$projectDir/db.sh", "start")
	}

	flywayMigrate {
		dependsOn(startDb)
	}
}


tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs += "-Xjsr305=strict"
		jvmTarget = "17"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}
