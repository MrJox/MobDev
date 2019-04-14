package com.example.lab3.DataProviders;
import com.example.lab3.Models.CredentialsModel;
import com.example.lab3.Models.ResponseModel;

public interface IDataProvider {
    ResponseModel authorize(CredentialsModel credentials);
    ResponseModel signup(CredentialsModel credentials);
}
