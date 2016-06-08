package org.wordpress.passcodelock.sample;

import android.app.Application;

import org.wordpress.passcodelock.AppLockManager;

public class SampleApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        AppLockManager.getInstance().enableDefaultAppLockIfAvailable(this);
    }
}
