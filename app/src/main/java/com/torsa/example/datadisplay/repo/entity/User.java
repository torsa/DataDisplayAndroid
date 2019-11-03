package com.torsa.example.datadisplay.repo.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "user")
public class User {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "username")
    private String userName;

    public User(String userName) {this.userName = userName;}

    public String getUserName(){return this.userName;}


}
