package com.tomek.ujebse;

import com.orm.SugarRecord;

/**
 * Created by tomek on 25.07.15.
 */
public class Links extends SugarRecord <Links> {
    String original;
    String shortcut;

    public Links() {
    }

    public Links(String original, String shortcut) {
        this.original = original;
        this.shortcut = shortcut;
    }
}
