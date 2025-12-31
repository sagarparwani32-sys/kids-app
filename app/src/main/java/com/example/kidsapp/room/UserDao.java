package com.example.kidsapp.room;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface UserDao {

    @Insert
    void insertUser(UserEntity user);

    @Update
    void updateUser(UserEntity user);

    @Delete
    void deleteUser(UserEntity user);

    @Query("SELECT * FROM user_table")
    List<UserEntity> getAllUsers();

    @Query("SELECT * FROM user_table WHERE id = :userId")
    UserEntity getUserById(int userId);
}