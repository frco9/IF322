package fr.inria.phoenix.scenario.cuisine.impl.context;

import fr.inria.diagen.core.ServiceConfiguration;
import fr.inria.phoenix.diasuite.framework.context.danger.AbstractDanger;
import fr.inria.phoenix.diasuite.framework.datatype.dangerlevel.DangerLevel;
import fr.inria.phoenix.diasuite.framework.datatype.onoffstatus.OnOffStatus;
import fr.inria.phoenix.diasuite.framework.device.clock.TickSecondFromClock;
import fr.inria.phoenix.diasuite.framework.device.motiondetector.MotionFromMotionDetector;
import fr.inria.phoenix.diasuite.framework.device.cooker.StatusFromCooker;
import fr.inria.phoenix.diasuite.framework.device.electricmeter.CurrentElectricConsumptionFromElectricMeter;
import fr.inria.phoenix.diasuite.framework.device.tablet.NotificationValidatedFromTablet;
import fr.inria.phoenix.diasuite.framework.device.timer.TimerTriggeredFromTimer;
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
	private static OnOffStatus currentCookerStatus;
	private static float currentPower = 0;
	private static float remindTime = 0;




	@Override
	protected void onCurrentElectricConsumptionFromElectricMeter(
			CurrentElectricConsumptionFromElectricMeter currentElectricConsumptionFromElectricMeter) {
		currentPower = currentElectricConsumptionFromElectricMeter.value();
		
	}

	@Override
	protected void onMotionFromMotionDetector(MotionFromMotionDetector motionFromMotionDetector) {
		presence = motionFromMotionDetector.value();
		if (presence) {
			IS_REMINDED = false;
			ALERT_VALIDATED = false;
			// timers().all().schedule("inactiveTimer", Configuration.Coeff * currentPower);  // Je sais pas comment acceder à la méthode schedule d'ici
		}
	}

	@Override
	protected void onNotificationValidatedFromTablet(
			NotificationValidatedFromTablet notificationValidatedFromTablet) {
		ALERT_VALIDATED=notificationValidatedFromTablet.value();
		
	}

	@Override
	protected void onStatusFromCooker(StatusFromCooker statusFromCooker) {
		currentCookerStatus = statusFromCooker.value();
		if (currentCookerStatus.equals(OnOffStatus.ON)) {
			IS_REMINDED = false;
			ALERT_VALIDATED = false;
			// timers().all().schedule("inactiveTimer", Configuration.Coeff * currentPower);  // Je sais pas comment acceder à la méthode schedule d'ici
		}
		
	}

	@Override
	protected DangerLevel onTimerTriggeredFromTimer(
			TimerTriggeredFromTimer timerTriggeredFromTimer) {
		
		if ((timerTriggeredFromTimer.value().equals("inactiveTimer")) && 
			(!presence) && 
			(currentCookerStatus.equals(OnOffStatus.ON)) && 
			(!IS_REMINDED) &&
			(!ALERT_VALIDATED)) 
		{
			IS_REMINDED = true;
			return DangerLevel.REMIND;
		} else if ( (timerTriggeredFromTimer.value().equals("toCookerTimer")) && 
			    	(!presence) && 
			    	(currentCookerStatus.equals(OnOffStatus.ON)) && 
			    	(IS_REMINDED)) 
		{
			if (!ALERT_VALIDATED) {
				return DangerLevel.ALERT;
			} else {
				return DangerLevel.STOP;
			}
		} else if ( (timerTriggeredFromTimer.value().equals("validationTimer")) && 
		    	(!presence) && 
		    	(currentCookerStatus.equals(OnOffStatus.ON)) && 
		    	(IS_REMINDED) &&
		    	(!ALERT_VALIDATED)) 
		{
			return DangerLevel.STOP;
		}
		
		return DangerLevel.ZERO;
	}

}
