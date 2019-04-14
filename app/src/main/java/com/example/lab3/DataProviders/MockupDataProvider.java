package com.example.lab3.DataProviders;
import com.example.lab3.Models.CredentialsModel;
import com.example.lab3.Models.ResponseModel;

public class MockupDataProvider implements IDataProvider {
    public MockupDataProvider() { }

    @Override
    public ResponseModel authorize(CredentialsModel credentials) {
        return new ResponseModel(200, "success", null, null);
    }

    @Override
    public ResponseModel signup(CredentialsModel credentials) {
        return new ResponseModel(200, "success", null, null);
    }
}
