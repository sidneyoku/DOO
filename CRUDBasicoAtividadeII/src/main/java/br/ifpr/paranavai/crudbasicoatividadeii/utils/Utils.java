package br.ifpr.paranavai.crudbasicoatividadeii.utils;


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
