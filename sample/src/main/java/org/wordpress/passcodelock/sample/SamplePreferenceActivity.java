package org.wordpress.passcodelock.sample;

import android.app.FragmentManager;
import android.os.Bundle;
import android.preference.Preference;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import org.wordpress.passcodelock.PasscodePreferenceFragment;

public class SamplePreferenceActivity extends AppCompatActivity {
    private static final String KEY_PASSCODE_FRAGMENT = "passcode-fragment";
    private static final String KEY_PREFERENCE_FRAGMENT = "preference-fragment";

    private PasscodePreferenceFragment mPasscodePreferenceFragment;
    private SamplePreferenceFragment mSamplePreferenceFragment;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();

        if (actionBar != null) {
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        FragmentManager fragmentManager = getFragmentManager();
        mSamplePreferenceFragment = (SamplePreferenceFragment) fragmentManager.findFragmentByTag(KEY_PREFERENCE_FRAGMENT);
        mPasscodePreferenceFragment = (PasscodePreferenceFragment) fragmentManager.findFragmentByTag(KEY_PASSCODE_FRAGMENT);

        if (mSamplePreferenceFragment == null || mPasscodePreferenceFragment == null) {
            Bundle passcodeArgs = new Bundle();
            passcodeArgs.putBoolean(PasscodePreferenceFragment.KEY_SHOULD_INFLATE, false);
            mSamplePreferenceFragment = new SamplePreferenceFragment();
            mPasscodePreferenceFragment = new PasscodePreferenceFragment();
            mPasscodePreferenceFragment.setArguments(passcodeArgs);

            fragmentManager.beginTransaction()
                    .replace(android.R.id.content, mPasscodePreferenceFragment, KEY_PASSCODE_FRAGMENT)
                    .add(android.R.id.content, mSamplePreferenceFragment, KEY_PREFERENCE_FRAGMENT)
                    .commit();
        }
    }

    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onStart() {
        super.onStart();

        Preference togglePref =
                mSamplePreferenceFragment.findPreference(getString(org.wordpress.passcodelock.R.string
                        .pref_key_passcode_toggle));
        Preference changePref =
                mSamplePreferenceFragment.findPreference(getString(org.wordpress.passcodelock.R.string
                        .pref_key_change_passcode));

        if (togglePref != null && changePref != null) {
            mPasscodePreferenceFragment.setPreferences(togglePref, changePref);
        }
    }
}
