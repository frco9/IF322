package fr.inria.phoenix.scenario.cuisine.impl.controller;

import fr.inria.diagen.core.ServiceConfiguration;
import fr.inria.phoenix.diasuite.framework.controller.tabletcontroller.AbstractTabletController;
import fr.inria.phoenix.diasuite.framework.context.danger.DangerValue;

/* (non-Javadoc)
 * The implementation of the TabletController context
 * @see fr.inria.phoenix.diasuite.framework.controller.tabletcontroller.AbstractTabletController
 */
public class TabletController extends AbstractTabletController {
    
    public TabletController(ServiceConfiguration serviceConfiguration) {
        super(serviceConfiguration);
    }

    /* (non-Javadoc)
     * @see fr.inria.phoenix.diasuite.framework.controller.tabletcontroller.AbstractTabletController#onDanger(DangerValue, DiscoverForDanger)
     */
    @Override
    protected void onDanger(DangerValue danger, DiscoverForDanger discover) {
        // TODO Auto-generated method stub
    }
}
