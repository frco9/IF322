package fr.inria.phoenix.scenario.cuisine.impl.controller;

import fr.inria.diagen.core.ServiceConfiguration;
import fr.inria.phoenix.diasuite.framework.context.danger.DangerValue;
import fr.inria.phoenix.diasuite.framework.controller.reminduser.AbstractRemindUser;
import fr.inria.phoenix.diasuite.framework.datatype.dangerlevel.DangerLevel;
import fr.inria.phoenix.scenario.cuisine.impl.Configuration;

/* (non-Javadoc)
 * The implementation of the RemindUser context
 * @see fr.inria.phoenix.diasuite.framework.controller.reminduser.AbstractRemindUser
 */
public class RemindUser extends AbstractRemindUser {

	public RemindUser(ServiceConfiguration serviceConfiguration) {
		super(serviceConfiguration);
	}

	/* (non-Javadoc)
	 * @see fr.inria.phoenix.diasuite.framework.controller.reminduser.AbstractRemindUser#onDanger(DangerValue, DiscoverForDanger)
	 */
	@Override
	protected void onDanger(DangerValue danger, DiscoverForDanger discover) {

		if(danger.value().equals(DangerLevel.ALERT)){
			discover.tablets().all().displayNotification(Configuration.NOTIFICATION_WARNING);
		}
		else if(danger.value().equals(DangerLevel.REMIND)) {
			discover.tablets().all().displayNotification(Configuration.NOTIFICATION_WEAK);
		}
	}
}
