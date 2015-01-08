package fr.inria.phoenix.scenario.cuisine.impl.context;

import fr.inria.diagen.core.ServiceConfiguration;
import fr.inria.phoenix.diasuite.framework.context.danger.AbstractDanger;
import fr.inria.phoenix.diasuite.framework.datatype.dangerdata.DangerData;
import fr.inria.phoenix.diasuite.framework.datatype.dangerlevel.DangerLevel;
import fr.inria.phoenix.diasuite.framework.datatype.onoffstatus.OnOffStatus;
import fr.inria.phoenix.diasuite.framework.device.motiondetector.MotionFromMotionDetector;
import fr.inria.phoenix.diasuite.framework.device.prompter.AnswerFromPrompter;
import fr.inria.phoenix.diasuite.framework.device.cooker.StatusFromCooker;
import fr.inria.phoenix.diasuite.framework.device.electricmeter.CurrentElectricConsumptionFromElectricMeter;
import fr.inria.phoenix.diasuite.framework.device.timer.TimerTriggeredFromTimer;
import fr.inria.phoenix.scenario.cuisine.impl.Configuration;


public class Danger extends AbstractDanger {

	public Danger(ServiceConfiguration serviceConfiguration) {
		super(serviceConfiguration);
	}

	private static boolean IS_REMINDED = false;
	private static boolean ALERT_VALIDATED = false;


	private static boolean presence = false;
	private static OnOffStatus currentCookerStatus;
	private static float currentPower = 0;




	@Override
	protected void onCurrentElectricConsumptionFromElectricMeter(
			CurrentElectricConsumptionFromElectricMeter currentElectricConsumptionFromElectricMeter) {
		currentPower = currentElectricConsumptionFromElectricMeter.value();
		Configuration.TIME_INACTIVE = (int) Math.floor(currentPower * 1);
	}

	@Override
	protected DangerValuePublishable onMotionFromMotionDetector(MotionFromMotionDetector motionFromMotionDetector) {
		presence = motionFromMotionDetector.value();
		// Une personne est detectée
		if (presence) {
			// On reinitialise les flags d'alertes précedement définis.
			IS_REMINDED = false;
			ALERT_VALIDATED = false;
			return new DangerValuePublishable(new DangerData(DangerLevel.ZERO, false, ""), false);
		} else { // Personne n'est detecté
			// On publie, pour avertir le controleur qu'il faut initialiser le timer "inactiveTimer"
			return new DangerValuePublishable(new DangerData(null, true, "inactiveTimer"), true);
		}
	}


	@Override
	protected DangerValuePublishable onStatusFromCooker(StatusFromCooker statusFromCooker) {
		currentCookerStatus = statusFromCooker.value();
		if ((currentCookerStatus.equals(OnOffStatus.ON)) && (currentPower > 0)) {
			// On reinitialise les flags d'alertes précedement définis.
			IS_REMINDED = false;
			ALERT_VALIDATED = false;
			// On publie, pour avertir le controleur qu'il faut initialiser le timer "inactiveTimer"
			return new DangerValuePublishable(new DangerData(null, true, "inactiveTimer"), true);
		}
		// Sinon il n'y a pas de danger, la cuisinière est eteinte, on ne publie donc pas de message de Danger.
		return new DangerValuePublishable(new DangerData(DangerLevel.ZERO, false, ""), false);
		
	}

	@Override
	protected DangerData onTimerTriggeredFromTimer(
			TimerTriggeredFromTimer timerTriggeredFromTimer) {
		
		if ((timerTriggeredFromTimer.value().equals("inactiveTimer")) && 
			(!presence) && 
			(currentCookerStatus.equals(OnOffStatus.ON)) &&
			(currentPower > 0) &&
			(!IS_REMINDED) &&
			(!ALERT_VALIDATED)) 
		{
			IS_REMINDED = true;
			return new DangerData(DangerLevel.REMIND, true, "toCookerTimer");
		} else if ( (timerTriggeredFromTimer.value().equals("toCookerTimer")) && 
			    	(!presence) && 
			    	(currentCookerStatus.equals(OnOffStatus.ON)) &&
					(currentPower > 0) && 
			    	(IS_REMINDED)) 
		{
			if (!ALERT_VALIDATED) {
				return new DangerData(DangerLevel.ALERT, true, "validationTimer");
			} else {
				return new DangerData(DangerLevel.STOP, false, "");
			}
		} else if ( (timerTriggeredFromTimer.value().equals("validationTimer")) &&  
		    	(currentCookerStatus.equals(OnOffStatus.ON)) && 
				(currentPower > 0) &&
		    	(IS_REMINDED) &&
		    	(!ALERT_VALIDATED)) 
		{
			return new DangerData(DangerLevel.STOP, false, "");
		}
		
		return new DangerData(DangerLevel.ZERO, false, "");
	}

	@Override
	protected DangerValuePublishable onAnswerFromPrompter(AnswerFromPrompter answerFromPrompter) {
		if (answerFromPrompter.value().equals("OK")) {
			ALERT_VALIDATED = true;
			return new DangerValuePublishable(new DangerData(DangerLevel.ALERT, true, "toCookerTimer"), true);
		} else {
			ALERT_VALIDATED = false;
			return new DangerValuePublishable(new DangerData(null, false, ""), false);
		}
	}

}
