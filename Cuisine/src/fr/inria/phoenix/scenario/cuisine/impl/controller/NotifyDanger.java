package fr.inria.phoenix.scenario.cuisine.impl.controller;

import fr.inria.diagen.core.ServiceConfiguration;
import fr.inria.phoenix.diasuite.framework.controller.notifydanger.AbstractNotifyDanger;
import fr.inria.phoenix.diasuite.framework.context.danger.DangerValue;

/* (non-Javadoc)
 * The implementation of the NotifyDanger context
 * @see fr.inria.phoenix.diasuite.framework.controller.notifydanger.AbstractNotifyDanger
 */
public class NotifyDanger extends AbstractNotifyDanger {
    
    public NotifyDanger(ServiceConfiguration serviceConfiguration) {
        super(serviceConfiguration);
    }

    /* (non-Javadoc)
     * @see fr.inria.phoenix.diasuite.framework.controller.notifydanger.AbstractNotifyDanger#onDanger(DangerValue, DiscoverForDanger)
     */
    @Override
    protected void onDanger(DangerValue danger, DiscoverForDanger discover) {
        // TODO Auto-generated method stub
    }
}
