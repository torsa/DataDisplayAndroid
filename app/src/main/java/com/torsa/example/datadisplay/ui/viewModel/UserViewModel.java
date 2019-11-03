package com.torsa.example.datadisplay.ui.viewModel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.torsa.example.datadisplay.repo.UserRepository;
import com.torsa.example.datadisplay.repo.entity.User;

import java.util.List;

public class UserViewModel extends AndroidViewModel {

    private UserRepository userRepository;

    private LiveData<List<User>> users;

    public UserViewModel (Application application) {
        super(application);
        userRepository = new UserRepository(application);
        users = userRepository.getUsers();
    }

    public LiveData<List<User>> getUsers() { return users; }

    public void insert(User user) { userRepository.insert(user); }
}
