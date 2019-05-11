package model.service.impl.util;

public class Validation {

    public static boolean isValidParam(String param){
        if (param.matches("^[[А-ЩЬЮЯЇІЄҐ а-щьюяїієґ A-Z a-z 0-9]]{1,20}+$")){
            return true;
        } else {
            return false;
        }
    }
}
