package br.com.gustavo.projetoesqueleto.utils;

import android.app.Activity;

public class ActivityEffectV5 implements ActivityEffect {
    public ActivityEffectV5() {
    }

    public void apply(Activity activity) {
        activity.overridePendingTransition(17432576, 17432577);
    }
}