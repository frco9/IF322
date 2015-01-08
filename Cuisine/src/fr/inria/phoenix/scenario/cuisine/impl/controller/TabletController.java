package fr.inria.phoenix.scenario.cuisine.impl.controller;

import java.util.ArrayList;

import fr.inria.diagen.core.ServiceConfiguration;
import fr.inria.diagen.log.DiaLog;
import fr.inria.phoenix.diasuite.framework.context.danger.DangerValue;
import fr.inria.phoenix.diasuite.framework.controller.tabletcontroller.AbstractTabletController;
import fr.inria.phoenix.diasuite.framework.datatype.contact.Contact;
import fr.inria.phoenix.diasuite.framework.datatype.dangerlevel.DangerLevel;
import fr.inria.phoenix.diasuite.framework.datatype.file.File;
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
    	ArrayList<File> files = new ArrayList<File>();
    	DiaLog.info("[TabletController] DangerValue :"+danger.value().getDangerLevel());
    	
    	if(danger.value().getDangerLevel().equals(DangerLevel.ALERT)){
    		ArrayList<String> PossibleAnswer = new ArrayList<String>();
    		PossibleAnswer.add("OK");
    		
			discover.prompters().all().askCloseQuestion(new Contact(), "", Configuration.NOTIFICATION_CRITICAL_TITLE, Configuration.NOTIFICATION_CRITICAL_CONTENT,PossibleAnswer);
		}
		else if(danger.value().getDangerLevel().equals(DangerLevel.REMIND)) {
			discover.messengers().all().sendMessage(new Contact(), Configuration.NOTIFICATION_WARNING_TITLE, Configuration.NOTIFICATION_WARNING_TITLE, files);
		}
		else if(danger.value().getDangerLevel().equals(DangerLevel.STOP)) {
			discover.messengers().all().sendMessage(new Contact(),Configuration.STOPPED_COOKER_TITLE ,Configuration.STOPPED_COOKER_CONTENT, files);
		}
    }
}
