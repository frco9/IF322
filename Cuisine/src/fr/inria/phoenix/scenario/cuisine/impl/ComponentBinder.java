package fr.inria.phoenix.scenario.cuisine.impl;
        
import fr.inria.phoenix.diasuite.framework.context.danger.AbstractDanger;
import fr.inria.phoenix.diasuite.framework.controller.cookercontroller.AbstractCookerController;
import fr.inria.phoenix.diasuite.framework.controller.tabletcontroller.AbstractTabletController;
import fr.inria.phoenix.diasuite.framework.controller.timercontroller.AbstractTimerController;
import fr.inria.phoenix.diasuite.framework.misc.AppComponentBinder;

/* (non-Javadoc)
 * The binder to provides the various components of the application
 * @see fr.inria.phoenix.diasuite.framework.misc.AppComponentBinder
 */
public class ComponentBinder extends AppComponentBinder {

	@Override
	public Class<? extends AbstractDanger> getDangerClass() {
		return AbstractDanger.class;
	}

	@Override
	public Class<? extends AbstractCookerController> getCookerControllerClass() {
		return AbstractCookerController.class;
	}

	@Override
	public Class<? extends AbstractTabletController> getTabletControllerClass() {
		return AbstractTabletController.class;
	}

	@Override
	public Class<? extends AbstractTimerController> getTimerControllerClass() {
		return AbstractTimerController.class;
	}


}
