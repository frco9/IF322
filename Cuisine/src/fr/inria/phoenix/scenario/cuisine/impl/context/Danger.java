package fr.inria.phoenix.scenario.cuisine.impl.context;

import fr.inria.diagen.core.ServiceConfiguration;
import fr.inria.phoenix.diasuite.framework.context.danger.AbstractDanger;
import fr.inria.phoenix.diasuite.framework.datatype.dangerlevel.DangerLevel;
import fr.inria.phoenix.diasuite.framework.device.clock.TickSecondFromClock;
import fr.inria.phoenix.diasuite.framework.device.motiondetector.MotionFromMotionDetector;
import fr.inria.phoenix.diasuite.framework.device.smartswitch.CurrentElectricConsumptionFromSmartSwitch;
import fr.inria.phoenix.diasuite.framework.device.tablet.ValidatedNotificationFromTablet;
import fr.inria.phoenix.scenario.cuisine.impl.Configuration;


public class Danger extends AbstractDanger {

	public Danger(ServiceConfiguration serviceConfiguration) {
		super(serviceConfiguration);
	}

	private static boolean IS_REMINDED = false;
	private static boolean ALERT_VALIDATED = false;

	//Temps d'inactivité
	int t_inactive = 0;
	//Temps pour que la personne valide la notificiation
	int t_to_validate = 0;
	//Temps d'accès à la cuisinière
	int t_to_cooker = 0;

	private static boolean presence = false;
	private static float currentPower = 0;
	private static float remindTime = 0;
	

	@Override
	protected void onCurrentElectricConsumptionFromSmartSwitch(CurrentElectricConsumptionFromSmartSwitch currentElectricConsumptionFromSmartSwitch) {
		currentPower = currentElectricConsumptionFromSmartSwitch.value();
	}

	@Override
	protected void onMotionFromMotionDetector(MotionFromMotionDetector motionFromMotionDetector) {
		presence = motionFromMotionDetector.value();
	}

	@Override
	protected DangerLevel onTickSecondFromClock(TickSecondFromClock tickSecondFromClock) {

		//Détection de consommation électrique
		if(currentPower > 0 ){
			t_inactive = 0;
			t_inactive++;

			if(!presence){

				if(!IS_REMINDED && !ALERT_VALIDATED){
					remindTime = currentPower * 1;//varie en fonction de la présence 
					
					if(t_inactive > remindTime){
						
						IS_REMINDED = true;
						
					}
				}
				else if (t_to_cooker > Configuration.TIME_ALERT_WEAK && IS_REMINDED){
					t_to_validate = 0;
					
					
					return DangerLevel.REMIND;
					
				}
				
				else if (t_to_validate > Configuration.TIME_TO_VALIDATE){
					return null;
				}
				
				
				
				
				

			}
			
			return null;

		}
		return DangerLevel.ZERO;
	}

	@Override
	protected void onValidatedNotificationFromTablet(
			ValidatedNotificationFromTablet validatedNotificationFromTablet) {
		ALERT_VALIDATED=validatedNotificationFromTablet.value();
		
	}


}
