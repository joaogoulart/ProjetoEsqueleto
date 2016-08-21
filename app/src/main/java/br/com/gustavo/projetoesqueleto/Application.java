package br.com.gustavo.projetoesqueleto;

import android.content.Context;
import android.support.multidex.MultiDex;

/**
 * Created by joao_ on 21/08/2016.
 */
public class Application extends android.app.Application {

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
}
