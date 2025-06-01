/**
 * La classe ApiResponse représente la réponse d'une API HTTP, contenant
 * le code de statut, un message, et le corps de la réponse.
 * 
 * Cette classe fournit des accesseurs pour chaque attribut et permet de 
 * représenter la réponse sous forme de chaîne de caractères.
 */
package com.maville.Model;

public class ApiResponse {
    /**
     * Le code de statut HTTP de la réponse.
     */
    private final int statusCode;

    /**
     * Le message associé à la réponse.
     */
    private final String message;

    /**
     * Le corps de la réponse, contenant les données renvoyées par l'API.
     */
    private final String body;

    /**
     * Constructeur pour créer une instance de ApiResponse avec tous les attributs.
     * 
     * @param statusCode Le code de statut HTTP de la réponse.
     * @param message Le message associé à la réponse.
     * @param body Le corps de la réponse.
     */
    public ApiResponse(int statusCode, String message, String body) {
        this.statusCode = statusCode;
        this.message = message;
        this.body = body;
    }

    /**
     * Retourne le code de statut HTTP de la réponse.
     * 
     * @return Le code de statut HTTP.
     */
    public int getStatusCode() {
        return statusCode;
    }

    /**
     * Retourne le message associé à la réponse.
     * 
     * @return Le message de la réponse.
     */
    public String getMessage() {
        return message;
    }

    /**
     * Retourne le corps de la réponse.
     * 
     * @return Le corps de la réponse.
     */
    public String getBody() {
        return body;
    }

    /**
     * Retourne une représentation sous forme de chaîne de caractères de l'instance ApiResponse.
     * 
     * @return Une chaîne représentant la réponse API.
     */
    @Override
    public String toString() {
        return "Status Code: " + statusCode + ", Message: " + message + ", Body: " + body;
    }
}
