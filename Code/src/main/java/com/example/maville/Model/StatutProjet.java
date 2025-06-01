/**
 * L'énumération StatutProjet représente les différents statuts possibles d'un projet dans le système.
 * 
 * Les statuts incluent :
 * <ul>
 *   <li><b>EN_ATTENTE</b> : Le projet est en attente de validation ou de démarrage.</li>
 *   <li><b>PREVU</b> : Le projet a été prévu mais n'a pas encore commencé.</li>
 *   <li><b>EN_COURS</b> : Le projet est actuellement en cours d'exécution.</li>
 *   <li><b>SUSPENDU</b> : Le projet a été temporairement suspendu.</li>
 *   <li><b>TERMINE</b> : Le projet a été complété avec succès.</li>
 * </ul>
 */
package com.example.maville.Model;
public enum StatutProjet {
    EN_ATTENTE,
    PREVU,
    EN_COURS,
    SUSPENDU,
    TERMINE,
}
