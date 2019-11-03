package com.torsa.example.datadisplay.repo;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.torsa.example.datadisplay.repo.entity.User;
import com.torsa.example.datadisplay.infra.DataDisplayDatabase;
import com.torsa.example.datadisplay.repo.dao.UserDao;

import java.util.List;

public class UserRepository {
    private UserDao userDao;
    private LiveData<List<User>> users;

    public UserRepository(Application application) {
        DataDisplayDatabase db = DataDisplayDatabase.getDatabase(application);
        userDao = db.userDao();
        users = userDao.getUserNames();
    }

    public LiveData<List<User>> getUsers() {
        return users;
    }


    public void insert (User word) {
        new insertAsyncTask(userDao).execute(word);
    }

    private static class insertAsyncTask extends AsyncTask<User, Void, Void> {

        private UserDao mAsyncTaskDao;

        insertAsyncTask(UserDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final User... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }
}
