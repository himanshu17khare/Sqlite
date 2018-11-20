package com.example.himanshu.pets.data;

import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;

public final class PetContract {
    public static final String CONTENT_AUTHORITY = "com.example.android.pets";
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);
    public static final String PATH_PETS = "pets";
    public static final class PetEntry implements BaseColumns{
        public final static String TABLE_NAME="pets";
        public final static String _id=BaseColumns._ID;
        public final static String name="name";
        public final static String breed="breed";
        public final static String gender="gender";
        public final static String weight="weight";

        public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_PETS);
        public final static int UNKNOWN=0;
        public final static int MALE=1;
        public final static int FEMALE=2;

        public static final String CONTENT_LIST_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_PETS;

        /**
         * The MIME type of the {@link #CONTENT_URI} for a single pet.
         */
        public static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_PETS;
        public static boolean isValidGender(Integer gender) {
            if (gender == UNKNOWN || gender == MALE || gender == FEMALE) {
                return true;
            }
            return false;
        }
    }

}
