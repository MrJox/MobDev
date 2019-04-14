package com.example.lab3;
import android.app.Application;
import com.example.lab3.DataProviders.RestDataProvider;
import com.example.lab3.DataProviders.IDataProvider;
import com.example.lab3.Models.*;

public class ApplicationController extends Application {
    private static final ApplicationController ourInstance = new ApplicationController();
    private RestDataProvider restDataProvider;
    private UserModel user;

    private ApplicationController() {
        this.restDataProvider = new RestDataProvider();
        this.user = new UserModel();
    }

    public static ApplicationController getInstance() {
        return ourInstance;
    }

    public IDataProvider getDataProvider() {
        return this.restDataProvider;
    }

    public UserModel getUser() {
        return this.user;
    }
}
