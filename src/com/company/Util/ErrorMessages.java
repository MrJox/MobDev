package com.company.Util;

public class ErrorMessages {
    public static String getMessageFromCode(int code) {
        switch (code) {
            case StatusCodes.BAD_REQUEST:
                return "Missing parameters or an unknown error";
            case StatusCodes.BAD_CREDENTIALS:
                return "Invalid login or password";
            case StatusCodes.FORBIDDEN:
                return "Resource not allowed";
            case StatusCodes.NOT_FOUND:
                return "Resource not found";
            case StatusCodes.METHOD_NOT_ALLOWED:
                return "Method not allowed";
            case StatusCodes.CONFLICT:
                return "User already exists";
            default:
                return null;
        }
    }
}
