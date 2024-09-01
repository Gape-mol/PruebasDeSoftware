package org.example;

public class Main {
    public static void main(String[] args) {
        // 10 Personas, 5 atributos
        //0. Nombre
        //1. Edad
        //2. Entrada (General, VIP, False -> No tiene entrada)
        //3. Invitados (Solo si la entrada es VIP (2 Invitados), en caso contrario es 0)
        //4. Ingresado (True, False)

        //2 Aforos
        //Sala VIP (Exclusiva VIP e invitados)
        //Sala General (Entrada "General")
    }
    public boolean verificarEdad(String entradas[][], int fila) {
        String strEdad = entradas[fila][1];
        int edad = Integer.parseInt(strEdad);
        if (edad >= 18) {
            return true;
        }
        else {
            return false;
        }
        //creo que aca simplemente podia hacer return edad >= 18;
    }

    public String verificarBoleto(String entradas[][], int fila){
    String estado = new String();
    String boleto = entradas[fila][2];
    if (boleto == "VIP"){
        estado = "VIP";
    }
    else if (boleto == "General"){
        estado = "General";
        }
    else{
        estado = "False";
        }
    return estado;
    }

    public boolean validarInvitados(String entradas[][], int fila){
        if (entradas[fila][2] == "VIP"){
            int numeroInvitados = Integer.parseInt(entradas[fila][3]);
            if (numeroInvitados <= 2){
                return true;
            }
            else{
                System.out.println("No se permiten mas de 2 invitados");
                return false;
            }
        }
        else{
            System.out.println("No se permiten invitados sin entrada VIP");
            return false;
        }
    }

    public int aforoDisponible(String entradas[][], String sala){
        int aforoActual = 0;
        int aforoMaximoGeneral = 40;
        int aforoMaximoVIP = 20;
        if (sala == "VIP"){
            for (int i = 0; i < entradas.length; i++){
                if (entradas[i][4] == "True" && entradas[i][2] == "VIP"){
                    aforoActual++;
                }
            }
            return aforoMaximoVIP - aforoActual;
        }
        else{
            for (int i = 0; i < entradas.length; i++){
                if (entradas[i][4] == "True" && entradas[i][2] == "General"){
                    aforoActual++;
                }
            }
            return aforoMaximoGeneral - aforoActual;
        }
    }

    public boolean ingresarPersona(String entradas[][], int fila){
        if (entradas[fila][4] == "False"){
            entradas[fila][4] = "True";
            return true;
        }
        else{
            System.out.println("La persona ya ingreso");
            return false;
        }
    }
    public boolean permitirEntrada(String entradas[][], int fila, String sala){
            if (verificarEdad(entradas, fila)) {
                if (verificarBoleto(entradas, fila) == "VIP") {
                    if (validarInvitados(entradas, fila)) {
                        if (aforoDisponible(entradas, sala) > 0) {
                            if(ingresarPersona(entradas, fila)) {
                                return true;
                            }
                            else{
                                return false;
                            }
                        } else {
                            System.out.println("No hay aforo disponible");
                            return false;
                        }
                    } else {
                        System.out.println("No se permiten mas de 2 invitados");
                        return false;
                    }
                } else if (verificarBoleto(entradas, fila) == "General") {
                    if (aforoDisponible(entradas, sala) > 0) {
                        if(ingresarPersona(entradas, fila)) {
                            return true;
                        }
                        else{
                            return false;
                        }
                    } else {
                        System.out.println("No hay aforo disponible");
                        return false;
                    }
                } else {
                    System.out.println("No se permiten personas sin boleto");
                    return false;
                }
            }
            else{
                System.out.println("No se permiten menores de edad");
                return false;
            }
    }
}