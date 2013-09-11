#App passcode library for Android

An app passcode protection implementation, which allows user to protect the app with a four digit code. 

Once enabled a four-digit passcode needs to be entered any time your mobile app is launched. This way your app is safe even if your smartphone or tablet falls into the wrong hands.

*Note: This library doesn't add any extra protection to your data. App data will not be encrypted, the library just adds a pin lock screen that makes the app safe even if your kids put their hands on the device :) * 


#### Version
0.0.1

## Installation
- Add a reference to the library in your project preferences file.
- Open the file `android.manifest` and declare the following activities:

        <activity
            android:name="org.wordpress.passcodelock.PasscodeUnlockActivity"
            android:screenOrientation="portrait"
            android:theme="@style/Theme.Sherlock.Light.NoActionBar"
            android:windowSoftInputMode="stateHidden" >
        </activity>
        <activity
            android:name="org.wordpress.passcodelock.PasscodePreferencesActivity"
            android:configChanges="orientation|keyboardHidden|screenSize"
            android:screenOrientation="portrait"
            android:theme="@style/Theme.Sherlock.Light"
            android:windowSoftInputMode="stateHidden" >
        </activity>
        <activity
            android:name="org.wordpress.passcodelock.PasscodeManagePasswordActivity"
            android:screenOrientation="portrait"
            android:theme="@style/Theme.Sherlock.Light.NoActionBar"
            android:windowSoftInputMode="stateHidden" >
        </activity>

- Add the following line in `onCreate` of your App file 
        AppLockManager.getInstance().enableDefaultAppLockIfAvailable(this);

- In your project you need to call `PasscodePreferencesActivity` to lauch the passcode configurazion screen.

###Apps that use this library
- [WordPress for Android][1]

###License
The MIT License (MIT)

[1]: https://github.com/wordpress-mobile/WordPress-Android