package org.musify.services;

public class AutenticacionProxy implements IAutenticacion {

    private Autenticacion autenticacionReal;  // Referencia a la clase real de autenticación
    private boolean autenticacionInicial;     // Indicador para verificar autenticación inicial

    // Constructor que inicializa el proxy con la autenticación real
    public AutenticacionProxy(Autenticacion autenticacionReal) {
        this.autenticacionReal = autenticacionReal;
        this.autenticacionInicial = false;
    }

    @Override
    public boolean autenticar(String email, String contraseña) {
        autenticacionInicial = autenticacionReal.autenticar(email, contraseña);
        if (autenticacionInicial) {
            System.out.println("Proxy: Usuario autenticado exitosamente.");
        } else {
            System.out.println("Proxy: Error en la autenticación.");
        }
        return autenticacionInicial;
    }

    @Override
    public boolean enviarCodigoSMS(String numeroTelefono) {
        if (autenticacionInicial) {
            return autenticacionReal.enviarCodigoSMS(numeroTelefono);
        } else {
            System.out.println("Proxy: Autenticación inicial no completada. No se puede enviar el SMS.");
            return false;
        }
    }

    @Override
    public boolean verificarCodigoSMS(String codigo) {
        if (autenticacionInicial) {
            return autenticacionReal.verificarCodigoSMS(codigo);
        } else {
            System.out.println("Proxy: Autenticación inicial no completada. No se puede verificar el código.");
            return false;
        }
    }
}
