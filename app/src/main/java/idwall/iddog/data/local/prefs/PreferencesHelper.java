package idwall.iddog.data.local.prefs;

import android.content.Context;
import android.content.SharedPreferences;

public class PreferencesHelper {

    public static final String PREF_KEY_USER_LOGGED = "PREF_KEY_USER_LOGGED";
    public static final String PREF_FILES_NAME = "IDDOG";

    public static boolean isUserLogged(Context context) {
        SharedPreferences sp = context.getSharedPreferences(PREF_FILES_NAME, Context.MODE_PRIVATE);
        return sp.getBoolean(PREF_KEY_USER_LOGGED, false);
    }

    public static void setUserLogged(Context context) {
        SharedPreferences sp = context.getSharedPreferences(PREF_FILES_NAME, Context.MODE_PRIVATE);
        sp.edit().putBoolean(PREF_KEY_USER_LOGGED, true).apply();
    }

}