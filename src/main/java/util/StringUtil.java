package util;

public class StringUtil {

    private StringUtil(){}
    public static final boolean isEmpty(final String value) {
        if (value == null) return true;
        if (value.trim().length() ==0) return true;
        if ("".equals(value)) return true;
        return false;
    }
}
