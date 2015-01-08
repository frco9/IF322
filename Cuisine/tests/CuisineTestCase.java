import static fr.inria.phoenix.diasuite.framework.mocks.Mock.TIMEOUT;
import static fr.inria.phoenix.diasuite.framework.mocks.Mock.shutdown;
import static fr.inria.phoenix.diasuite.framework.mocks.Mock.underTest;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import fr.inria.phoenix.diasuite.framework.datatype.onoffstatus.OnOffStatus;
import fr.inria.phoenix.diasuite.framework.mocks.CookerMock;
import fr.inria.phoenix.diasuite.framework.mocks.ElectricMeterMock;
import fr.inria.phoenix.diasuite.framework.mocks.MessengerMock;
import fr.inria.phoenix.diasuite.framework.mocks.Mock;
import fr.inria.phoenix.diasuite.framework.mocks.MotionDetectorMock;
import fr.inria.phoenix.diasuite.framework.mocks.PrompterMock;
import fr.inria.phoenix.diasuite.framework.mocks.TimerMock;
import fr.inria.phoenix.scenario.cuisine.impl.ComponentBinder;


public class CuisineTestCase {

	private ElectricMeterMock electricMeter;
	private CookerMock cooker;
	private MotionDetectorMock motionDetector;
	private TimerMock timer;
	private PrompterMock prompter;
	private MessengerMock messenger;
	
	@Before
	public void setUp() throws Exception{
		TIMEOUT = 1000;
		underTest(ComponentBinder.class);
	}
	
	@After
	public void tearDown() throws Exception{
		shutdown();
	}
	
	@Test
	public void testInactiveTimerTriggered() {
		electricMeter = Mock.mockElectricMeter("electricMeter", "I001", "Tester");
		cooker = Mock.mockCooker("cookerMock", "I002", "Tester");
		motionDetector = Mock.mockMotionDetector("motionDetector", "I003", "Tester");
		timer = Mock.mockTimer("timer");

		electricMeter.setCurrentElectricConsumption(200f);
		cooker.setStatus(OnOffStatus.ON);
		motionDetector.setMotion(false);
		timer.setTimerTriggered("", "inactiveTimer");
		
		assertTrue(timer.expectSchedule("inactiveTimer", 200));
	}

}
