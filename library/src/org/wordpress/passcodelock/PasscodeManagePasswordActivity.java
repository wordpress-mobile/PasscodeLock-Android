package org.wordpress.passcodelock;

import android.os.Bundle;
import android.widget.TextView;

public class PasscodeManagePasswordActivity extends AbstractPasscodeKeyboardActivity {
    public static final String  KEY_TYPE = "type";

    private int type = -1;
    private String unverifiedPasscode = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            type = extras.getInt(KEY_TYPE, -1);
        }
    }

    @Override
    protected void onPinLockInserted() {
        String passLock = mPinCodeField.getText().toString();
        mPinCodeField.setText("");

        switch (type) {
            case PasscodePreferenceFragment.DISABLE_PASSLOCK:
                if( AppLockManager.getInstance().getCurrentAppLock().verifyPassword(passLock) ) {
                    setResult(RESULT_OK);
                    AppLockManager.getInstance().getCurrentAppLock().setPassword(null);
                    finish();
                } else {
                    showPasswordError();
                }
                break;
            case PasscodePreferenceFragment.ENABLE_PASSLOCK:
                if( unverifiedPasscode == null ) {
                    ((TextView) findViewById(R.id.passcodelock_prompt)).setText(R.string.passcode_re_enter_passcode);
                    unverifiedPasscode = passLock;
                } else {
                    if( passLock.equals(unverifiedPasscode)) {
                        setResult(RESULT_OK);
                        AppLockManager.getInstance().getCurrentAppLock().setPassword(passLock);
                        finish();
                    } else {
                        unverifiedPasscode = null;
                        topMessage.setText(R.string.passcodelock_prompt_message);
                        showPasswordError();
                    }
                }
                break;
            case PasscodePreferenceFragment.CHANGE_PASSWORD:
                //verify old password
                if( AppLockManager.getInstance().getCurrentAppLock().verifyPassword(passLock) ) {
                    topMessage.setText(R.string.passcodelock_prompt_message);
                    type = PasscodePreferenceFragment.ENABLE_PASSLOCK;
                } else {
                    showPasswordError();
                } 
                break;
            default:
                break;
        }
    }
}
