/**
 * Classe abstraite Controller, servant de base pour tous les contrôleurs du projet.
 * 
 * Cette classe impose une méthode abstraite {@code isAuthorized()},
 * que les classes dérivées doivent implémenter pour gérer les autorisations d'accès.
 */
package com.maville.Controller;

public abstract class Controller {
    /**
     * Vérifie si l'utilisateur est autorisé à effectuer une action.
     * 
     * @return True si l'utilisateur est autorisé, false sinon.
     */
    abstract boolean isAuthorized();
}
