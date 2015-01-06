package fr.inria.phoenix.scenario.cuisine.impl.controller;

import fr.inria.diagen.core.ServiceConfiguration;
import fr.inria.phoenix.diasuite.framework.controller.timercontroller.AbstractTimerController;
import fr.inria.phoenix.diasuite.framework.context.danger.DangerValue;

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
        // TODO Auto-generated method stub
    }
}
