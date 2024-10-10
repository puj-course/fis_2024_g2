package org.musify.services;

public interface IAutenticacion {
    boolean autenticar(String email, String contraseña);
    boolean enviarCodigoSMS(String numeroTelefono);
    boolean verificarCodigoSMS(String codigo);
}
