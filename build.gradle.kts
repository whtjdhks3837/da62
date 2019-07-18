
buildscript {
    repositories {
        google()
        jcenter()
        
    }
    dependencies {
        classpath (BuildPlugins.buildGradle)
        classpath (BuildPlugins.kotlinGradlePlugin)
        classpath (BuildPlugins.firebase)
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
