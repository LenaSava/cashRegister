package model.service.impl.util;

public class Validation {

    public static boolean isValidParam(String param) {
        if (param.matches("^[[А-ЩЬЮЯЇІЄҐ а-щьюяїієґ A-Z a-z 0-9]]{1,20}+$")) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isEmailValid(String email) {
        if (email.matches("^(([^<>()\\[\\]\\\\.,;:\\s@\"]+(\\.[^<>()\\[\\]\\\\.,;" +
                ":\\s@\"]+)*)|(\".+\"))@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\." +
                "[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$")) {
            return true;
        } else {
            return false;
        }
    }
}