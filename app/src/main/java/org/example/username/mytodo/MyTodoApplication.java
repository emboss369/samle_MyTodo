package org.example.username.mytodo;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Add android:name="org.example.username.mytodo.MyTodoApplication" in application tag.
 */

public class MyTodoApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        Realm.init(this);
        RealmConfiguration realmConfig
                = new RealmConfiguration.Builder().build();
        // テスト時にDBの構造を変更した時やデータを一度クリアしたい時などは一旦DBを削除すると良い。
        // Realm.deleteRealm(realmConfig);
        Realm.setDefaultConfiguration(realmConfig);
    }
}
