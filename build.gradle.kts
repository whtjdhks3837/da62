
buildscript {
    repositories {
        google()
        jcenter()
        
    }
    dependencies {
        classpath (BuildPlugins.buildGradle)
        classpath (BuildPlugins.kotlinGradlePlugin)
    }
}

allprojects {
    repositories {
        google()
        jcenter()
        maven(Url.Maven.kakao)
    }
}

task("clean", Delete::class) {
    delete(rootProject.buildDir)
}
