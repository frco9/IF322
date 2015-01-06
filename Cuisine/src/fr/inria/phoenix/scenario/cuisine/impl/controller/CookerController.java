package fr.inria.phoenix.scenario.cuisine.impl.controller;

import fr.inria.diagen.core.ServiceConfiguration;
import fr.inria.phoenix.diasuite.framework.controller.cookercontroller.AbstractCookerController;
import fr.inria.phoenix.diasuite.framework.context.danger.DangerValue;

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
        // TODO Auto-generated method stub
    }
}
