package org.wordpress.passcodelock;

import android.app.Application;
import android.os.Build;

public class AppLockManager {
    private static AppLockManager instance;
    private AbstractAppLock currentAppLocker;

    public static AppLockManager getInstance() {
        if (instance == null) {
            instance = new AppLockManager();
        }
        return instance;
    }

    public void enableDefaultAppLockIfAvailable(Application currentApp) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.ICE_CREAM_SANDWICH) return;
        currentAppLocker = new DefaultAppLock(currentApp);
        currentAppLocker.enable();
    }

    public boolean isDefaultLock() {
        return getAppLock() != null && getAppLock() instanceof DefaultAppLock;
    }
    
    /**
     * Default App lock is available on Android-v14 or higher.
     * @return True if the Passcode Lock feature is available on the device
     */
    public boolean isAppLockFeatureEnabled() {
        return getAppLock() != null && (!isDefaultLock() || isSupportedApi());
    }
    
    public void setCurrentAppLock(AbstractAppLock newAppLocker) {
    	if( currentAppLocker != null ) {
    		currentAppLocker.disable(); //disable the old applocker if available
    	}
        currentAppLocker = newAppLocker;
    }
    
    public AbstractAppLock getAppLock() {
        return currentAppLocker;
    }
    
    public void setExtendedTimeout(){
        if (getAppLock() == null) return;
        getAppLock().setOneTimeTimeout(AbstractAppLock.EXTENDED_TIMEOUT_S);
    }

    private boolean isSupportedApi() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH;
    }
}
