package com.hugo.victor.kwego.utils;

public class conversions {
    //    FUNÇÃO PARA CONVERTER UMA STRING KWEGONIAN PARA ROMANO
    public static String convertToRoman(String kwegonian) {
        switch (kwegonian.toLowerCase()) {
            case "kil":
                return "I";
            case "jin":
                return "V";
            case "pol":
                return "X";
            case "kilow":
                return "L";
            case "jij":
                return "C";
            case "jinjin":
                return "D";
            case "polsx":
                return "M";
            default:
                return null;
        }
    }

    //    FUNÇÃO PARA CONVERTER UMA STRING ROMANA EM DECIMAL
    public static int convertRomanToDecimal(String numRomano) {
//        INICIA AS VARIÁVEIS ZERADAS
        int decimal = 0;
        int ultimoNumero = 0;

//        PARA CADA CHAR DO NUMERO ROMANO
        for (int x = numRomano.length() - 1; x >= 0; x--) {
//            CONVERTE A STRING PARA CHAR
            char convertToDecimal = numRomano.charAt(x);

//            PARA CADA CHAR ELE VERIFICA SE É UM "NÚMERO" EXISTENTE E PARA CADA "NÚMERO ENCONTRADO ELE PROCESSA PARA DECIMAL
            switch (convertToDecimal) {
                case 'M':
                    decimal = processDecimal(1000, ultimoNumero, decimal);
                    ultimoNumero = 1000;
                    break;
                case 'D':
                    decimal = processDecimal(500, ultimoNumero, decimal);
                    ultimoNumero = 500;
                    break;
                case 'C':
                    decimal = processDecimal(100, ultimoNumero, decimal);
                    ultimoNumero = 100;
                    break;
                case 'L':
                    decimal = processDecimal(50, ultimoNumero, decimal);
                    ultimoNumero = 50;
                    break;
                case 'X':
                    decimal = processDecimal(10, ultimoNumero, decimal);
                    ultimoNumero = 10;
                    break;
                case 'V':
                    decimal = processDecimal(5, ultimoNumero, decimal);
                    ultimoNumero = 5;
                    break;
                case 'I':
                    decimal = processDecimal(1, ultimoNumero, decimal);
                    ultimoNumero = 1;
                    break;
            }
        }
        return decimal;
    }

    //    FUNÇÃO PARA PROCESSAR O DÍGITO DE ROMANO PARA DECIMAL
    private static int processDecimal(int decimal, int ultimoNumero, int ultimoDecimal) {
        if (ultimoNumero > decimal) {
            return ultimoDecimal - decimal;
        } else {
            return ultimoDecimal + decimal;
        }
    }
}
