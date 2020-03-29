package com.barry.baseandroidarchitecture.db;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class DataRepository {

    private DataDao mDataDao;

    public DataRepository(Application application) {
        AppDataBase db = AppDataBase.getDatabase(application);
        mDataDao = db.dataDao();
    }

    public LiveData<List<DataModel>> getAllData() {
        return mDataDao.getAll();
    }

    public void insert(DataModel data)
    {
        new insertAsyncTask(mDataDao).execute(data);
    }
    public void insert(List<DataModel> data)
    {
        new insertsAsyncTask(mDataDao).execute(data);
    }

    private static class insertAsyncTask extends AsyncTask<DataModel, Void, Void> {

        private DataDao mAsyncTaskDao;

        insertAsyncTask(DataDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final DataModel... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }

    private static class insertsAsyncTask extends AsyncTask<List<DataModel>, Void, Void> {

        private DataDao mAsyncTaskDao;

        insertsAsyncTask(DataDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final List<DataModel>... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }
    private static class deleteAllAsyncTask extends AsyncTask<Void, Void, Void> {

        private DataDao mAsyncTaskDao;

        deleteAllAsyncTask(DataDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            mAsyncTaskDao.deleteAll();
            return null;
        }
    }
}
