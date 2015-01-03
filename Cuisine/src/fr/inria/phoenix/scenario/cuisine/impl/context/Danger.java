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
	//Temps pour que la personne valide la notification
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
		//Detection Consommation électrique
		if (currentPower > 0) {
			//Detection Presence : Presence non detectee
			if (!presence) {
				t_inactive++;
				t_to_cooker++;

				remindTime = currentPower*1; //remindTime depend de currentPower;

				//Rappel cuisiniere ON
				if (t_inactive > remindTime && !IS_REMINDED && !ALERT_VALIDATED) {
					t_to_validate = 0;
					IS_REMINDED = true;
					t_to_cooker = 0;
					
					return DangerLevel.REMIND;
				}
				//Alerte cuisiniere ON
				else if (t_to_cooker > Configuration.TIME_ALERT_WEAK && IS_REMINDED) {
					t_to_validate=0;

					//Notification Alerte validee
					if (ALERT_VALIDATED && t_to_validate < Configuration.TIME_TO_VALIDATE) {
						t_to_cooker = 0;
						return DangerLevel.ZERO;
					}
					//Notification Alerte non validee -> arret cuisiniere
					else if (!ALERT_VALIDATED && t_to_validate > Configuration.TIME_TO_VALIDATE) {
						return DangerLevel.STOP;
					}

					return DangerLevel.ALERT;
				}
				//Notification Alerte validee mais Personne non presente -> arret cuisiniere
				else if (t_to_cooker > Configuration.TIME_ALERT_HIGH && ALERT_VALIDATED) {
					t_to_cooker = 0;
					return DangerLevel.STOP;
				}
			//Presence detectee
			}else {
				t_inactive = 0;
				IS_REMINDED = false;
				ALERT_VALIDATED = false;
			}
		}
		return DangerLevel.ZERO;
	}

	@Override
	protected void onValidatedNotificationFromTablet(
			ValidatedNotificationFromTablet validatedNotificationFromTablet) {
		ALERT_VALIDATED=validatedNotificationFromTablet.value();

	}


}
