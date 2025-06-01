/**
 * La classe HttpClientApi fournit une interface pour effectuer des requêtes HTTP à une API REST.
 * 
 * Cette classe est conçue pour interagir avec des ressources distantes en effectuant
 * des appels GET et en gérant les réponses dans des instances d'ApiResponse.
 */
package com.maville.Model;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

public class HttpClientApi {
    /**
     * URL de base de l'API.
     */
    private static final String BASE_URL = "https://donnees.montreal.ca/api/3/action/datastore_search";

    /**
     * Instance du client HTTP utilisé pour envoyer des requêtes.
     */
    private final HttpClient client;

    /**
     * Constructeur pour initialiser l'instance HttpClient.
     */
    public HttpClientApi() {
        this.client = HttpClient.newHttpClient();
    }

    /**
     * Effectue une requête GET à l'API avec l'ID de ressource spécifié.
     * 
     * @param resourceId L'identifiant de la ressource à récupérer.
     * @return Une instance d'ApiResponse contenant le code de statut, le message,
     *         et le corps de la réponse. Retourne null en cas d'erreur.
     */
    public ApiResponse getData(String resourceId) {
        try {
            // Build URI with encoded query parameters
            String encodedResourceId = URLEncoder.encode(resourceId, StandardCharsets.UTF_8);

            URI uri = new URI(BASE_URL + "?resource_id=" + encodedResourceId);

            // Build the request
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(uri)
                    .header("Accept", "application/json")
                    .GET()
                    .build();

            // Send the request and get the response
            HttpResponse<String> response = this.client.send(request, HttpResponse.BodyHandlers.ofString());

            // Check the response status and print the body if successful
            if (response.statusCode() == 200) {
                return new ApiResponse(response.statusCode(), "OK", response.body());
            } else {
                System.out.println("Error: " + response.statusCode());
                return null;
            }
        } catch (IOException | InterruptedException | java.net.URISyntaxException e) {
            return new ApiResponse(500, "Internal Server Error", "Exception occurred: " + e.getMessage());
        }
    }
}
