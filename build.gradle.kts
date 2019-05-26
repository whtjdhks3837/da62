
buildscript {
    repositories {
        google()
        jcenter()
        
    }
    dependencies {
        classpath (BuildPlugins.buildGradle)
        classpath (BuildPlugins.kotlinGradlePlugin)
        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:2.1.0-alpha04")
    }
}

allprojects {
    repositories {
        google()
        jcenter()
        
    }
}

task("clean", Delete::class) {
    delete(rootProject.buildDir)
}
