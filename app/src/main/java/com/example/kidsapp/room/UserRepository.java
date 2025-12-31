package com.example.kidsapp.room;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class UserRepository {

    private UserDao userDao;
    private LiveData<List<UserEntity>> allUsers;
    private ExecutorService executorService;

    public UserRepository(Application application) {
        AppDatabase database = AppDatabase.getDatabase(application);
        userDao = database.userDao();
        executorService = Executors.newSingleThreadExecutor();
    }

    public LiveData<List<UserEntity>> getAllUsers() {
        return allUsers;
    }

    public void insert(UserEntity user) {
        executorService.execute(() -> userDao.insertUser(user));
    }

    public void update(UserEntity user) {
        executorService.execute(() -> userDao.updateUser(user));
    }

    public void delete(UserEntity user) {
        executorService.execute(() -> userDao.deleteUser(user));
    }
}