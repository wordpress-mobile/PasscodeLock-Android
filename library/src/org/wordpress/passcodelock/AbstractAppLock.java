package org.wordpress.passcodelock;

import android.app.Application;

/**
 * Interface for AppLock implementations.
 *
 * There are situations where the AppLock should not be required within an app. Methods for tracking
 * exempt {@link android.app.Activity}'s are provided and sub-class implementations are expected to
 * comply with requested exemptions.
 * @see #isExemptActivity(String)
 * @see #setExemptActivities(String[])
 * @see #getExemptActivities()
 *
 * Applications can request a one-time delay in locking the app. This can be useful for activities
 * that launch external applications with the expectation that the user will return to the calling
 * application shortly.
 */
public abstract class AbstractAppLock implements Application.ActivityLifecycleCallbacks {
    public static final int DEFAULT_TIMEOUT = 2; //2 seconds
    public static final int EXTENDED_TIMEOUT = 60; //60 seconds

    protected static final String FINGERPRINT_VERIFICATION_BYPASS = "fingerprint-bypass__";

    protected int lockTimeOut = DEFAULT_TIMEOUT;
    protected String[] appLockDisabledActivities = new String[0];

    public void setOneTimeTimeout(int timeout) {
        this.lockTimeOut = timeout;
    }

    public void setDisabledActivities( String[] disabledActs ) {
    	this.appLockDisabledActivities = disabledActs;
    }

    public abstract void enable();
    public abstract void disable();
    public abstract void forcePasswordLock();
    public abstract boolean verifyPassword(String password);
    public abstract boolean isPasswordLocked();
    public abstract boolean setPassword(String password);
}
