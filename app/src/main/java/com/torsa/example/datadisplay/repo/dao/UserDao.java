package com.torsa.example.datadisplay.repo.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.torsa.example.datadisplay.repo.entity.User;

import java.util.List;

@Dao
public interface UserDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(User user);

    @Query("DELETE FROM user")
    void deleteAll();

  /*  @Query("SELECT * from user ORDER BY username ASC")
    List<User> getUserNames();*/


    //This is what you want
    @Query("SELECT * from user ORDER BY username ASC")
    LiveData<List<User>> getUserNames();
}
