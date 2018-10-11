package idwall.iddog.data.local.prefs;

import android.content.Context;
import android.content.SharedPreferences;

public class PreferencesHelper {

    public static final String PREF_FILES_NAME = "IDDOG";
    public static final String PREF_KEY_USER_TOKEN = "PREF_KEY_USER_TOKEN";

    public static void setUserToken(Context context, String token) {
        SharedPreferences sp = context.getSharedPreferences(PREF_FILES_NAME, Context.MODE_PRIVATE);
        sp.edit().putString(PREF_KEY_USER_TOKEN, token).apply();
    }

    public static String getUserToken(Context context) {
        SharedPreferences sp = context.getSharedPreferences(PREF_FILES_NAME, Context.MODE_PRIVATE);
        return sp.getString(PREF_KEY_USER_TOKEN, null);
    }

}