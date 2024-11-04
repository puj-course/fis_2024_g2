package org.musify.service;

import org.musify.model.usuario.Usuario;


import java.util.ArrayList;

public class Autenticacion implements IAutenticacion{
    private ArrayList<Usuario> usuariosRegistrados;
    private String codigoVerificacionActual;
    VonageSmsService vonageSmsService;

    public Autenticacion(ArrayList<Usuario> usuariosRegistrados, String codigoVerificacionActual, VonageSmsService vonageSMSService) {
        this.usuariosRegistrados = usuariosRegistrados;
        this.vonageSmsService = vonageSMSService;
    }

    public ArrayList<Usuario> getUsuariosRegistrados() {
        return usuariosRegistrados;
    }

    public void setUsuariosRegistrados(ArrayList<Usuario> usuariosRegistrados) {
        this.usuariosRegistrados = usuariosRegistrados;
    }

    public String getCodigoVerificacionActual() {
        return codigoVerificacionActual;
    }

    public void setCodigoVerificacionActual(String codigoVerificacionActual) {
        this.codigoVerificacionActual = codigoVerificacionActual;
    }

    public VonageSmsService getVonageSMSService() {
        return vonageSmsService;
    }

    public void setVonageSMSService(VonageSmsService vonageSMSService) {
        this.vonageSmsService = vonageSMSService;
    }

    @Override
    public boolean autenticar(String nickname, String contrasenia) {
        for (Usuario usuario : usuariosRegistrados) {
            if (usuario.getNickname().equals(nickname) && usuario.getContra().equals(contrasenia)) {
                System.out.println("Autenticación exitosa para el usuario: " + nickname);
                return true;
            }
        }
        System.out.println("Credenciales incorrectas para el usuario: " + nickname);
        return false;
    }

    @Override
    public boolean enviarCodigoSMS(String numeroTelefono) {
        this.codigoVerificacionActual = generarCodigoVerificacion();
        String mensaje = "Tu codigo de verificacion es: " + codigoVerificacionActual;
        return vonageSmsService.enviarSMS(numeroTelefono, mensaje);
    }

    @Override
    public boolean verificarCodigoSMS(String codigo) {
        if (codigoVerificacionActual != null && codigoVerificacionActual.equals(codigo)) {
            return true;
        } else {
            return false;
        }
    }

    private String generarCodigoVerificacion() {
        int codigo = (int) (Math.random() * 900000) + 100000;  // Genera un número aleatorio de 6 dígitos
        return String.valueOf(codigo);
    }
}
