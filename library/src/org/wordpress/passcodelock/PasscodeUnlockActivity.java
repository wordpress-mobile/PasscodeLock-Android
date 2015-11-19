package org.wordpress.passcodelock;

import android.content.Intent;

public class PasscodeUnlockActivity extends AbstractPasscodeKeyboardActivity {
    
    @Override
    public void onBackPressed() {
        AppLockManager.getInstance().getCurrentAppLock().forcePasswordLock();
        Intent i = new Intent();
        i.setAction(Intent.ACTION_MAIN);
        i.addCategory(Intent.CATEGORY_HOME);
        startActivity(i);
        finish();
    }

    @Override
    protected void onPinLockInserted() {
        String passLock = mPinCodeField.getText().toString();
        if( AppLockManager.getInstance().getCurrentAppLock().verifyPassword(passLock) ) {
            authenticationSucceeded();
        } else {
            authenticationFailed();
        }
    }
}