package fr.inria.phoenix.scenario.cuisine.impl.controller;

import fr.inria.diagen.core.ServiceConfiguration;
import fr.inria.phoenix.diasuite.framework.context.danger.DangerValue;
import fr.inria.phoenix.diasuite.framework.controller.timercontroller.AbstractTimerController;
import fr.inria.phoenix.diasuite.framework.datatype.dangerlevel.DangerLevel;
import fr.inria.phoenix.scenario.cuisine.impl.Configuration;

/* (non-Javadoc)
 * The implementation of the TimerController context
 * @see fr.inria.phoenix.diasuite.framework.controller.timercontroller.AbstractTimerController
 */
public class TimerController extends AbstractTimerController {
    
    public TimerController(ServiceConfiguration serviceConfiguration) {
        super(serviceConfiguration);
    }

    /* (non-Javadoc)
     * @see fr.inria.phoenix.diasuite.framework.controller.timercontroller.AbstractTimerController#onDanger(DangerValue, DiscoverForDanger)
     */
    @Override
    protected void onDanger(DangerValue danger, DiscoverForDanger discover) {
    	if(danger.value().equals(DangerLevel.ALERT)){
			discover.timers().all().schedule("toCookerTimer", Configuration.TIME_ALERT_HIGH);
			discover.timers().all().schedule("validationTimer", Configuration.TIME_TO_VALIDATE);
		}
		else if(danger.value().equals(DangerLevel.REMIND)) {
			discover.timers().all().schedule("toCookerTimer", Configuration.TIME_ALERT_WEAK);
		}
    }
}
