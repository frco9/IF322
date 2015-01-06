package fr.inria.phoenix.scenario.cuisine.impl.controller;

import fr.inria.diagen.core.ServiceConfiguration;
import fr.inria.diagen.log.DiaLog;
import fr.inria.phoenix.diasuite.framework.context.danger.DangerValue;
import fr.inria.phoenix.diasuite.framework.controller.cookercontroller.AbstractCookerController;
import fr.inria.phoenix.diasuite.framework.datatype.dangerlevel.DangerLevel;

/* (non-Javadoc)
 * The implementation of the CookerController context
 * @see fr.inria.phoenix.diasuite.framework.controller.cookercontroller.AbstractCookerController
 */
public class CookerController extends AbstractCookerController {
    
    public CookerController(ServiceConfiguration serviceConfiguration) {
        super(serviceConfiguration);
    }

    /* (non-Javadoc)
     * @see fr.inria.phoenix.diasuite.framework.controller.cookercontroller.AbstractCookerController#onDanger(DangerValue, DiscoverForDanger)
     */
    @Override
    protected void onDanger(DangerValue danger, DiscoverForDanger discover) {
    	if(danger.value().equals(DangerLevel.STOP)){
			discover.cookers().all().off();
			DiaLog.info("Stopping !");
			System.out.println("Stopping !");
		}
    }
}
