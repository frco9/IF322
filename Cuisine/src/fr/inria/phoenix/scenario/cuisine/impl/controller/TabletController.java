package fr.inria.phoenix.scenario.cuisine.impl.controller;

import java.util.ArrayList;

import fr.inria.diagen.core.ServiceConfiguration;
import fr.inria.phoenix.diasuite.framework.context.danger.DangerValue;
import fr.inria.phoenix.diasuite.framework.controller.tabletcontroller.AbstractTabletController;
import fr.inria.phoenix.diasuite.framework.datatype.dangerlevel.DangerLevel;
import fr.inria.phoenix.scenario.cuisine.impl.Configuration;

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
    	if(danger.value().equals(DangerLevel.ALERT)){
    		ArrayList<String> PossibleAnswer = new ArrayList<String>();
    		PossibleAnswer.add("OK");
    		
			discover.prompters().all().askCloseQuestion(null, "", Configuration.NOTIFICATION_CRITICAL_TITLE, Configuration.NOTIFICATION_CRITICAL_CONTENT,PossibleAnswer);
		}
		else if(danger.value().equals(DangerLevel.REMIND)) {
			discover.messengers().all().sendMessage(null, Configuration.NOTIFICATION_WARNING_TITLE, Configuration.NOTIFICATION_WARNING_TITLE, null);
		}
    }
}
