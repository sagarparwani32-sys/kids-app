package com.example.kidsapp.room;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class UserViewModel extends AndroidViewModel {

    private UserRepository repository;
    private LiveData<List<UserEntity>> allUsers;

    public UserViewModel(Application application) {
        super(application);
        repository = new UserRepository(application);
    }

    public LiveData<List<UserEntity>> getAllUsers() {
        return allUsers;
    }

    public void insert(UserEntity user) {
        repository.insert(user);
    }

    public void update(UserEntity user) {
        repository.update(user);
    }

    public void delete(UserEntity user) {
        repository.delete(user);
    }
}