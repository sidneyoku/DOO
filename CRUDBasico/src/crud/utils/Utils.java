package crud.utils;

import crud.entidade.Cidade;

/**
 *
 * @author Sidney
 */
public class Utils {
    
    public static boolean isNumber(String str) {
        try {
          Integer.parseInt(str);
          return true;
        } catch (NumberFormatException e) {
          return false;
        }
    }
}
