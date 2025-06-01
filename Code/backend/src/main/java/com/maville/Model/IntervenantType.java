/**
 * L'énumération IntervenantType définit les différents types d'intervenants pouvant être
 * impliqués dans une requête ou un projet.
 * 
 * Les types incluent :
 * <ul>
 *   <li><b>PUBLIC</b> : Représente un intervenant d'une organisation publique.</li>
 *   <li><b>PRIVATE</b> : Représente un intervenant d'une entreprise privée.</li>
 *   <li><b>INDIVIDUAL</b> : Représente un intervenant agissant en tant qu'individu.</li>
 * </ul>
 */
package com.maville.Model;

public enum IntervenantType {

    /**
     * Intervenant appartenant à une organisation publique.
     */
    PUBLIC,

    /**
     * Intervenant appartenant à une organisation privée.
     */
    PRIVATE,

    /**
     * Intervenant agissant en tant qu'individu.
     */
    INDIVIDUAL
}
