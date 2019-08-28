package whatsapp.app.freewhats.utils;

public class Utils {

    public static boolean isNull(String word){
        return word.isEmpty() || word==null;
    }

    public static boolean isNumeric(String number){
        try {
            double d = Double.parseDouble(number);
        } catch (NumberFormatException | NullPointerException nfe) {
            return false;
        }
        return true;
    }
}
