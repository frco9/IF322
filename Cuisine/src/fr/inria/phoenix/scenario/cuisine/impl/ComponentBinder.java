package fr.inria.phoenix.scenario.cuisine.impl;
        
import fr.inria.phoenix.diasuite.framework.context.danger.AbstractDanger;
import fr.inria.phoenix.diasuite.framework.controller.cookercontroller.AbstractCookerController;
import fr.inria.phoenix.diasuite.framework.controller.tabletcontroller.AbstractTabletController;
import fr.inria.phoenix.diasuite.framework.controller.timercontroller.AbstractTimerController;
import fr.inria.phoenix.scenario.cuisine.impl.controller.CookerController;
import fr.inria.phoenix.scenario.cuisine.impl.controller.TabletController;
import fr.inria.phoenix.scenario.cuisine.impl.controller.TimerController;
import fr.inria.phoenix.scenario.cuisine.impl.context.Danger;
import fr.inria.phoenix.diasuite.framework.misc.AppComponentBinder;

/* (non-Javadoc)
 * The binder to provides the various components of the application
 * @see fr.inria.phoenix.diasuite.framework.misc.AppComponentBinder
 */
public class ComponentBinder extends AppComponentBinder {

	@Override
	public Class<? extends AbstractDanger> getDangerClass() {
		return Danger.class;
	}

	@Override
	public Class<? extends AbstractCookerController> getCookerControllerClass() {
		return CookerController.class;
	}

	@Override
	public Class<? extends AbstractTabletController> getTabletControllerClass() {
		return TabletController.class;
	}

	@Override
	public Class<? extends AbstractTimerController> getTimerControllerClass() {
		return TimerController.class;
	}
	
	/*public static void main(String[] args) {
		ComponentBinder binder = new ComponentBinder();
		binder.deployAll();

//		ElectricMeter electricMeter = new ElectricMeter();
//		cooker;
//		motionDetector;
//		timer;
//		prompter;
//		messenger;
//		
//		ControlPanel controlPanel = new ControlPanel(
//				binder.getServiceConfiguration("ControlPanel_1"),
//				"ControlPanel", "A29", "phoenix");
//
//		HVACSystem hvacSystem = new HVACSystem(new LocalServiceConfiguration(
//				"HvacSystem_1"), "HvacSystem", "A29", "phoenix");
//
//		TemperatureSensor temperatureSensor = new TemperatureSensor(
//				new LocalServiceConfiguration("TemperatureSensor_1"),
//				"TemperatureSensor", "A29", "phoenix");
//
//		binder.deploy(controlPanel);
//		binder.deploy(hvacSystem);
//		binder.deploy(temperatureSensor);
//
//		temperatureSensor.show();
//		controlPanel.show();
//		hvacSystem.show();

	}*/

}
