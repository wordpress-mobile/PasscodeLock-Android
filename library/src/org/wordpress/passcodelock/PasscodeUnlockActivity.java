package org.wordpress.passcodelock;

import android.content.Intent;
import android.support.v4.hardware.fingerprint.FingerprintManagerCompat;
import android.support.v4.os.CancellationSignal;
import android.view.View;

public class PasscodeUnlockActivity extends AbstractPasscodeKeyboardActivity {

    @Override
    public void onResume() {
        super.onResume();

        // Hide fingerprint notification if the hardware doesn't support it
        if (!mFingerprintManager.isHardwareDetected() ||
            !mFingerprintManager.hasEnrolledFingerprints()) {
            findViewById(R.id.image_fingerprint).setVisibility(View.GONE);
        } else {
            mFingerprintManager.authenticate(null, 0, mCancel = new CancellationSignal(), getFingerprintCallback(), null);
        }
    }

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

    @Override
    protected FingerprintManagerCompat.AuthenticationCallback getFingerprintCallback() {
        return new FingerprintManagerCompat.AuthenticationCallback() {
            @Override
            public void onAuthenticationError(int errMsgId, CharSequence errString) {
                super.onAuthenticationError(errMsgId, errString);
            }

            @Override
            public void onAuthenticationHelp(int helpMsgId, CharSequence helpString) {
                super.onAuthenticationHelp(helpMsgId, helpString);
            }

            @Override
            public void onAuthenticationSucceeded(FingerprintManagerCompat.AuthenticationResult result) {
                super.onAuthenticationSucceeded(result);
                authenticationSucceeded();
            }

            @Override
            public void onAuthenticationFailed() {
                super.onAuthenticationFailed();
                authenticationFailed();
            }
        };
    }
}
