package org.wordpress.passcodelock.sample;

import android.os.Bundle;
import android.preference.PreferenceFragment;

public class SamplePreferenceFragment extends PreferenceFragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        addPreferencesFromResource(R.xml.preferences);
    }
}
