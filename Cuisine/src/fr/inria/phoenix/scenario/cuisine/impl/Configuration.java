package fr.inria.phoenix.scenario.cuisine.impl;



public class Configuration {

	// En millisecondes
	public static int TIME_ALERT_HIGH = 24;
	public static int TIME_ALERT_WEAK=30;
	public static int TIME_TO_VALIDATE=30;
	// Valeur dynamiquement mise à jour quand la consomation electrique change.
	public static int TIME_INACTIVE=0;
	
	public static final String NOTIFICATION_WARNING_TITLE="RAPPEL";
	public static final String NOTIFICATION_CRITICAL_TITLE="ALERTE";

	public static final String NOTIFICATION_WARNING_CONTENT="Votre cuisinière est toujours en marche !";
	public static final String NOTIFICATION_CRITICAL_CONTENT=" Attention ! Aucune activité n'a été détectée pour la cuisinière ! Un arrêt d'urgence est sur le point d'être lancé !";
	
	public static final String STOPPED_COOKER_TITLE="Suite à une absence d'activité, un arrêt d'urgence a été effectué sur la cuisinière.";

	public static final String STOPPED_COOKER_CONTENT="Suite à une absence d'activité, un arrêt d'urgence a été effectué sur la cuisinière.";

}
