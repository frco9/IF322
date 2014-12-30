package fr.inria.phoenix.scenario.cuisine.impl;
        
import fr.inria.phoenix.diasuite.framework.context.danger.AbstractDanger;
import fr.inria.phoenix.diasuite.framework.controller.reminduser.AbstractRemindUser;
import fr.inria.phoenix.diasuite.framework.controller.stopcooker.AbstractStopCooker;
import fr.inria.phoenix.diasuite.framework.misc.AppComponentBinder;

/* (non-Javadoc)
 * The binder to provides the various components of the application
 * @see fr.inria.phoenix.diasuite.framework.misc.AppComponentBinder
 */
public class ComponentBinder extends AppComponentBinder {

	@Override
	public Class<? extends AbstractDanger> getDangerClass() {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public Class<? extends AbstractStopCooker> getStopCookerClass() {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public Class<? extends AbstractRemindUser> getRemindUserClass() {
		// TODO Auto-generated method stub
		return null;
	}
}
