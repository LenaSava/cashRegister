package controller.commands.impl.util;

import java.util.ResourceBundle;

public class PageResourseManager {

    private static ResourceBundle resourceBundle = ResourceBundle.getBundle("page");

    public static String getProperty(String key) {
        return resourceBundle.getString(key);
    }
}
