package org.example.practica_final_hibernate.Util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {
    public static boolean validar(String tipo, String cadena) {
        Pattern pattern = null;
        switch(tipo) {
            case "nombre":
                //valida que el nombre no contenga números o caracteres especiales
                pattern = Pattern.compile("^([A-Za-zÁáÉéÍíÓóÚú]+ *)+$");
                break;
            case "numero":
                //comprueba que el numero solo contiene numeros
                pattern = Pattern.compile("^\\d+$");
                break;
            case "pass":
                //comprueba que la contraseña tenga al menos 4 caracteres
                pattern = Pattern.compile("^.{4,}$");
                break;
        }
        assert pattern != null;
        Matcher matcher = pattern.matcher(cadena);
        return matcher.find();
    }
}
