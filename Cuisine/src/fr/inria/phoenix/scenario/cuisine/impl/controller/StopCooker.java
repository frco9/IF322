package fr.inria.phoenix.scenario.cuisine.impl.controller;

import fr.inria.diagen.core.ServiceConfiguration;
import fr.inria.diagen.log.DiaLog;
import fr.inria.phoenix.diasuite.framework.controller.stopcooker.AbstractStopCooker;
import fr.inria.phoenix.diasuite.framework.context.danger.DangerValue;

/* (non-Javadoc)
 * The implementation of the StopCooker context
 * @see fr.inria.phoenix.diasuite.framework.controller.stopcooker.AbstractStopCooker
 */
public class StopCooker extends AbstractStopCooker {

	public StopCooker(ServiceConfiguration serviceConfiguration) {
		super(serviceConfiguration);
	}

	/* (non-Javadoc)
	 * @see fr.inria.phoenix.diasuite.framework.controller.stopcooker.AbstractStopCooker#onDanger(DangerValue, DiscoverForDanger)
	 */
	@Override
	protected void onDanger(DangerValue danger, DiscoverForDanger discover) {

		if(danger.value() == 3){
			discover.smartSwitchs().all().stopCooker();
			DiaLog.info("Stopping !");
			System.out.println("Stopping !");
		}
	}
}
