package com.tomek.ujebse;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by tomek on 30.07.15.
 */
public class Utils {
    public static void makeLongToast(Context context, String string) {
        Toast toast = Toast.makeText(context, string, Toast.LENGTH_LONG);
        toast.show();
    }

    public static void makeShortToast(Context context, String string) {
        Toast toast = Toast.makeText(context, string, Toast.LENGTH_SHORT);
        toast.show();
    }
}
