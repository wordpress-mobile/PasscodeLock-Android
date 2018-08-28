* * *

**Not Maintained!**  
*This project is no longer being actively maintained. Use at your own risk!*

* * *

# App passcode library for Android

An app passcode protection implementation, which allows user to protect the app with a four digit code.

Once enabled a four-digit passcode needs to be entered any time your mobile app is launched. This way your app is safe even if your smartphone or tablet falls into the wrong hands.

**Note: This library doesn't add any extra protection to your data. App data will not be encrypted, the library just adds a pin lock screen that makes the app safe even if your kids put their hands on the device :)**

## Example Usage

Add it as a maven dependency in your build.gradle file. PasscodeLock is hosted on the maven central repository.

* In your build.gradle:
```groovy
dependencies {
    // use the latest 1.x version
    compile 'org.wordpress:passcodelock:1.+'
}
```
* Edit your `AndroidManifest.xml` and declare the following activities:
```xml
<activity
    android:name="org.wordpress.passcodelock.PasscodeUnlockActivity"
    android:theme="@style/Theme.Sherlock.Light.NoActionBar"
    android:windowSoftInputMode="stateHidden" >
</activity>
<activity
    android:name="org.wordpress.passcodelock.PasscodeManagePasswordActivity"
    android:theme="@style/Theme.Sherlock.Light.NoActionBar"
    android:windowSoftInputMode="stateHidden" >
</activity>
```

* Add the following line in `onCreate` of your App file:
```java
AppLockManager.getInstance().enableDefaultAppLockIfAvailable(this);
```

* In your project you need to use `PasscodePreferenceFragment` in your Preference Activity. See [SettingsActivity][2] as a usage reference. Optionally, you may pass an argument that tells it whether or not to inflate its own preferences, this is only needed if you plan on [providing the preferences](https://github.com/wordpress-mobile/WordPress-Android/blob/develop/WordPress/src/main/res/xml/settings.xml#L39) via [PasscodePreferenceFragment#setPreferences](https://github.com/wordpress-mobile/PasscodeLock-Android/blob/develop/library/src/org/wordpress/passcodelock/PasscodePreferenceFragment.java#L50).

## Customization

If you want to customize the pinlock icon, the one available in the unlock screen, override the `passcode_logo.xml` drawable resource.

## Hack the library

* Copy `gradle.properties-example` to `gradle.properties` in the `library/` directory of the project.

Publish it to bintray:

```shell
$ ./gradlew assemble publishToMavenLocal bintrayUpload -PbintrayUser=FIXME -PbintrayKey=FIXME -PdryRun=false
```

## Apps that use this library
- [WordPress for Android][1]

## License
Dual licensed under MIT, and GPL.

[1]: https://github.com/wordpress-mobile/WordPress-Android

[2]: https://github.com/wordpress-mobile/WordPress-Android/blob/develop/WordPress/src/main/java/org/wordpress/android/ui/prefs/SettingsActivity.java
