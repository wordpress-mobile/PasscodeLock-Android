# Android-PasscodeLock Development Repository

Development and release artifacts are published in this repository.

## Example usage in Android Studio

Add the development repository to the `repositories` section of your `build.gradle`.

```groovy
// add the repo to your
repositories {
  mavenCentral()
  maven { url "http://wordpress-mobile.github.io/android-passcodelock"}
}
```

Add the dependency to the `dependencies` section of your `build.gradle`.

```
// add the dependency
dependencies {
  compile 'org.wordpress:android-passcodelock:0.0.1'
}

```