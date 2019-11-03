package com.torsa.example.datadisplay.infra;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.torsa.example.datadisplay.repo.entity.User;
import com.torsa.example.datadisplay.repo.dao.UserDao;

@Database(entities = {User.class}, version = 1,exportSchema = false)
public abstract class DataDisplayDatabase extends RoomDatabase {
    public abstract UserDao userDao();

    private static volatile DataDisplayDatabase INSTANCE;

    public static DataDisplayDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (DataDisplayDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            DataDisplayDatabase.class, "dataDisplay_database")
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }
    private static RoomDatabase.Callback sRoomDatabaseCallback =
            new RoomDatabase.Callback(){

                @Override
                public void onOpen (@NonNull SupportSQLiteDatabase db){
                    super.onOpen(db);
                    new PopulateDbAsync(INSTANCE).execute();
                }
            };

    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final UserDao mDao;

        PopulateDbAsync(DataDisplayDatabase db) {
            mDao = db.userDao();
        }

        @Override
        protected Void doInBackground(final Void... params) {
            mDao.deleteAll();
            User user = new User("harry");
            mDao.insert(user);
            user = new User("hermione");
            mDao.insert(user);
            return null;
        }
    }

}
