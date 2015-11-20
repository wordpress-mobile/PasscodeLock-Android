package org.wordpress.passcodelock;

import android.content.Intent;
import android.support.v4.hardware.fingerprint.FingerprintManagerCompat;
import android.support.v4.os.CancellationSignal;
import android.view.View;

public class PasscodeUnlockActivity extends AbstractPasscodeKeyboardActivity {

    @Override
    public void onResume() {
        super.onResume();

        // Show fingerprint scanner if supported
        if (mFingerprintManager.isHardwareDetected()
                && mFingerprintManager.hasEnrolledFingerprints()) {
            mFingerprintManager.authenticate(null, 0, mCancel = new CancellationSignal(), getFingerprintCallback(), null);
            View view = findViewById(R.id.image_fingerprint);
            view.setVisibility(View.VISIBLE);
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
        if (AppLockManager.getInstance().getCurrentAppLock().verifyPassword(passLock)) {
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
                // Must be called to set internal state (lostFocusDate). Without the call to
                // verifyPassword the unlock screen will show multiple times
                AppLockManager.getInstance().getCurrentAppLock().verifyPassword(
                        AbstractAppLock.FINGERPRINT_VERIFICATION_BYPASS);
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
