package fr.inria.phoenix.scenario.cuisine.impl.context;

import fr.inria.diagen.core.ServiceConfiguration;
import fr.inria.phoenix.diasuite.framework.context.danger.AbstractDanger;
import fr.inria.phoenix.diasuite.framework.device.clock.TickSecondFromClock;
import fr.inria.phoenix.diasuite.framework.device.motiondetector.MotionFromMotionDetector;
import fr.inria.phoenix.diasuite.framework.device.smartswitch.CurrentElectricConsumptionFromSmartSwitch;

/* (non-Javadoc)
 * The implementation of the Danger context
 * @see fr.inria.phoenix.diasuite.framework.context.danger.AbstractDanger
 */
public class Danger extends AbstractDanger {
    
    public Danger(ServiceConfiguration serviceConfiguration) {
        super(serviceConfiguration);
    }
    
    /* (non-Javadoc)
     * @see fr.inria.phoenix.diasuite.framework.context.danger.AbstractDanger#onCurrentElectricConsumptionFromSmartSwitch(CurrentElectricConsumptionFromSmartSwitch)
     */
    @Override
    protected void onCurrentElectricConsumptionFromSmartSwitch(CurrentElectricConsumptionFromSmartSwitch currentElectricConsumptionFromSmartSwitch) {
        // TODO Auto-generated method stub
    }
    
    /* (non-Javadoc)
     * @see fr.inria.phoenix.diasuite.framework.context.danger.AbstractDanger#onMotionFromMotionDetector(MotionFromMotionDetector)
     */
    @Override
    protected void onMotionFromMotionDetector(MotionFromMotionDetector motionFromMotionDetector) {
        // TODO Auto-generated method stub
    }
    
    /* (non-Javadoc)
     * @see fr.inria.phoenix.diasuite.framework.context.danger.AbstractDanger#onTickSecondFromClock(TickSecondFromClock)
     */
    @Override
    protected void onTickSecondFromClock(TickSecondFromClock tickSecondFromClock) {
        // TODO Auto-generated method stub
    }
}
