package org.musify.services;

import org.musify.model.Usuario;
import java.util.*;

public class Autenticacion implements IAutenticacion{
    private ArrayList <Usuario> usuariosRegistrados;
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
    public boolean autenticar(String nickname, String contraseña) {
        Usuario usuario = usuariosRegistrados.get(Integer.parseInt(nickname));
        if (usuario != null && usuario.getContraseña().equals(contraseña)) {
            System.out.println("Autenticación exitosa para el usuario: " + nickname);
            return true;
        } else {
            System.out.println("Credenciales incorrectas para el usuario: " + nickname);
            return false;
        }
    }

    @Override
    public boolean enviarCodigoSMS(String numeroTelefono) {
        this.codigoVerificacionActual = generarCodigoVerificacion();
        String mensaje = "Tu código de verificación es: " + codigoVerificacionActual;
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
