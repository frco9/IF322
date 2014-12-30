package fr.inria.phoenix.scenario.cuisine.impl;

public class Configuration {

	// En secondes
	public static int TIME_ALERT_HIGH = 24;
	public static int TIME_ALERT_WEAK=30;
	public static int TIME_TO_VALIDATE=30;
	
	public static final String NOTIFICATION_WARNING="Attention ! Votre cuisinière est toujours en marche !";
	public static final String NOTIFICATION_CRITICAL=" Attention ! Aucune activité n'a été détectée pour la cuisinière ! Un arrêt d'urgence est sur le point d'être lancé !";
	
}
