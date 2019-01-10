buildscript {
    var kotlinVersion: String by extra
    kotlinVersion = "1.3.11"

    repositories {
        jcenter()
        google()
    }
    dependencies {
        //用于构建Android工程的插件
        //https://developer.android.com/studio/build/gradle-plugin-3-0-0-migration.html
        classpath("com.android.tools.build:gradle:3.2.1")

        //Kotlin编译的插件
        //http://kotlinlang.org/docs/reference/using-gradle.html
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion")
    }
}

allprojects {
    repositories {
        jcenter()
        google()
    }
}

task("clean", Delete::class) {
    delete(rootProject.buildDir)
}
