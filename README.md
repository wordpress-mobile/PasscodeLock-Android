# App passcode library for Android

An app passcode protection implementation, which allows user to protect the app with a four digit code.

Once enabled a four-digit passcode needs to be entered any time your mobile app is launched. This way your app is safe even if your smartphone or tablet falls into the wrong hands.

Note: This library doesn't add any extra protection to your data. App data will not be encrypted, the library just adds a pin lock screen that makes the app safe even if your kids put their hands on the device :)

## Example Usage

Add it as a maven dependency in your build.gradle file. PasscodeLock is hosted on the maven central repository.

* In your build.gradle:
```
dependencies {
    // use the latest 1.x version
    compile 'org.wordpress:passcodelock:1.+'
}
```
* Edit your `AndroidManifest.xml` and declare the following activities:
```
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
```
AppLockManager.getInstance().enableDefaultAppLockIfAvailable(this);
```

* In your project you need to use `PasscodePreferenceFragment` in your `SettingsActivity`. See [WordPress for Android][1] on how to inflate preferences
  in the parent app.

## Customization
If you want to customize the pinlock icon, the one available in the unlock screen, override the `passcode_logo.xml` drawable resource.

## Hack the library

* Copy `gradle.properties-example` to `gradle.properties` in the `library/` directory of the project.

## Apps that use this library
- [WordPress for Android][1]

## License
Dual licensed under MIT, and GPL.

[1]: https://github.com/wordpress-mobile/WordPress-Android
