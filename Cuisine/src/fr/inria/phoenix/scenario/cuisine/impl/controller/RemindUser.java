package fr.inria.phoenix.scenario.cuisine.impl.controller;

import fr.inria.diagen.core.ServiceConfiguration;
import fr.inria.phoenix.diasuite.framework.context.danger.DangerValue;
import fr.inria.phoenix.diasuite.framework.controller.reminduser.AbstractRemindUser;
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

		if(danger.value() == 2){
			discover.tablets().all().displayNotification(Configuration.NOTIFICATION_CRITICAL);
		}
		else if(danger.value() == 1) {
			discover.tablets().all().displayNotification(Configuration.NOTIFICATION_WARNING);
		}
	}
}
