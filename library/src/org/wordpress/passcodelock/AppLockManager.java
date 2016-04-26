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
    
    /**
     * Default App lock is available on Android-v14 or higher.
     * @return True if the Passcode Lock feature is available on the device
     */
    public boolean isAppLockFeatureEnabled(){
    	if( currentAppLocker == null )
    		return false;
    	if( currentAppLocker instanceof DefaultAppLock)
    		return (android.os.Build.VERSION.SDK_INT >= 14);
    	else 
    		return true;
    }
    
    public void setCurrentAppLock(AbstractAppLock newAppLocker) {
    	if( currentAppLocker != null ) {
    		currentAppLocker.disable(); //disable the old applocker if available
    	}
        currentAppLocker = newAppLocker;
    }
    
    public AbstractAppLock getCurrentAppLock() {
        return currentAppLocker;
    }
    
    public void setExtendedTimeout(){
        if ( currentAppLocker == null )
            return;
        currentAppLocker.setOneTimeTimeout(AbstractAppLock.EXTENDED_TIMEOUT);
    }
}
