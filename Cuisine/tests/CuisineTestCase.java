import static fr.inria.phoenix.diasuite.framework.mocks.Mock.TIMEOUT;
import static fr.inria.phoenix.diasuite.framework.mocks.Mock.shutdown;
import static fr.inria.phoenix.diasuite.framework.mocks.Mock.underTest;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import fr.inria.diagen.log.DiaLog;
import fr.inria.phoenix.diasuite.framework.datatype.onoffstatus.OnOffStatus;
import fr.inria.phoenix.diasuite.framework.mocks.CookerMock;
import fr.inria.phoenix.diasuite.framework.mocks.ElectricMeterMock;
import fr.inria.phoenix.diasuite.framework.mocks.MessengerMock;
import fr.inria.phoenix.diasuite.framework.mocks.Mock;
import fr.inria.phoenix.diasuite.framework.mocks.MotionDetectorMock;
import fr.inria.phoenix.diasuite.framework.mocks.PrompterMock;
import fr.inria.phoenix.diasuite.framework.mocks.TimerMock;
import fr.inria.phoenix.scenario.cuisine.impl.ComponentBinder;
import fr.inria.phoenix.scenario.cuisine.impl.Configuration;


public class CuisineTestCase {

	private ElectricMeterMock electricMeter;
	private CookerMock cooker;
	private MotionDetectorMock motionDetector;
	private TimerMock timer;
	private PrompterMock prompter;
	private MessengerMock messenger;
	
	private String location1 = "kitchen1";
	private String location2 = "kitchen2";
	private String location3 = "kitchen3";
	private String location4 = "kitchen4";
	private String user = "user";
	
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
		DiaLog.info("TestInactiveTimer");
		electricMeter = Mock.mockElectricMeter("electricMeter", "I001", "Tester");
		cooker = Mock.mockCooker("cookerMock", "I002", "Tester");
		motionDetector = Mock.mockMotionDetector("motionDetector", "I003", "Tester");
		timer = Mock.mockTimer("timer");

		electricMeter.currentElectricConsumption(200f);
		cooker.status(OnOffStatus.ON);
		motionDetector.motion(false);

		assertTrue(timer.expectSchedule("inactiveTimer", 200));
		System.out.println("");
	}
	
	@Test
	public void testUserPresent() {
		DiaLog.info("TestUserPresent");
		
		electricMeter = Mock.mockElectricMeter("E1",location1,user);
		motionDetector= Mock.mockMotionDetector("M1", location1, user);
		cooker = Mock.mockCooker("C1", location1, user);
		timer = Mock.mockTimer("timer");
		prompter = Mock.mockPrompter("P1", location1, user);
		messenger = Mock.mockMessenger("Me1", location1, user);
		

		electricMeter.currentElectricConsumption(200f);
		cooker.status(OnOffStatus.ON);
		motionDetector.motion(true);
		
		assertTrue(timer.expectSchedule("inactiveTimer", 200));
		System.out.println("");
	}
	
	
	@Test
	public void testUserIsReminded() {
		DiaLog.info("TestUserIsReminded");
		
		electricMeter = Mock.mockElectricMeter("E1",location2,user);
		motionDetector= Mock.mockMotionDetector("M1", location2, user);
		cooker = Mock.mockCooker("C1", location2, user);
		timer = Mock.mockTimer("Timer");
		prompter = Mock.mockPrompter("P1", location2, user);
		messenger = Mock.mockMessenger("Me1", location2, user);
		
		electricMeter.currentElectricConsumption(150.0f);
		cooker.status(OnOffStatus.ON);
		motionDetector.motion(false);
		
		assertTrue(messenger.expectSendMessage(null, Configuration.NOTIFICATION_WARNING_TITLE, Configuration.NOTIFICATION_WARNING_TITLE, null));
		System.out.println("");

	}

	@Test
	public void testUserIsAlerted(){
		DiaLog.info("TestUserIsAlerted");
		
		ArrayList<String> PossibleAnswer = new ArrayList<>();
		PossibleAnswer.add("OK");
		
		electricMeter = Mock.mockElectricMeter("E1",location3,user);
		motionDetector= Mock.mockMotionDetector("M1", location3, user);
		cooker = Mock.mockCooker("C1", location3, user);
		timer = Mock.mockTimer("Timer");
		prompter = Mock.mockPrompter("P1", location3, user);
		messenger = Mock.mockMessenger("Me1", location3, user);
		
		electricMeter.currentElectricConsumption(150.0f);
		cooker.status(OnOffStatus.ON);
		motionDetector.motion(false);
		
		assertTrue(messenger.expectSendMessage(null, Configuration.NOTIFICATION_WARNING_TITLE, Configuration.NOTIFICATION_WARNING_TITLE, null));
		assertTrue(prompter.expectAskCloseQuestion(null, "", Configuration.NOTIFICATION_CRITICAL_TITLE, Configuration.NOTIFICATION_CRITICAL_CONTENT,PossibleAnswer));
		System.out.println("");
	}
	
	@Test 
	public void testUserMissing(){
		DiaLog.info("TestUserMissing");
		
		
		electricMeter = Mock.mockElectricMeter("E1",location4,user);
		motionDetector= Mock.mockMotionDetector("M1", location4, user);
		cooker = Mock.mockCooker("C1", location4, user);
		timer = Mock.mockTimer("Timer");
		prompter = Mock.mockPrompter("P1", location4, user);
		messenger = Mock.mockMessenger("Me1", location4, user);

		electricMeter.currentElectricConsumption(200.0f);
		cooker.status(OnOffStatus.OFF);
		motionDetector.motion(false);

		ArrayList<String> PossibleAnswer = new ArrayList<>();
		PossibleAnswer.add("");

		assertTrue(prompter.expectAskCloseQuestion(null, "", Configuration.NOTIFICATION_CRITICAL_TITLE, Configuration.NOTIFICATION_CRITICAL_CONTENT,PossibleAnswer));
		assertTrue(messenger.expectSendMessage(null,Configuration.STOPPED_COOKER_TITLE ,Configuration.STOPPED_COOKER_CONTENT, null));
		assertTrue(cooker.expectOff());
		System.out.println("");
	}

}
