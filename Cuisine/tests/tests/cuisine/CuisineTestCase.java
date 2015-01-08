package tests.cuisine;
import static fr.inria.phoenix.diasuite.framework.mocks.Mock.TIMEOUT;
import static fr.inria.phoenix.diasuite.framework.mocks.Mock.shutdown;
import static fr.inria.phoenix.diasuite.framework.mocks.Mock.underTest;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

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
		electricMeter = Mock.mockElectricMeter("electricMeter", "I001", "Tester");
		cooker = Mock.mockCooker("cookerMock", "I002", "Tester");
		motionDetector = Mock.mockMotionDetector("motionDetector", "I003", "Tester");
		timer = Mock.mockTimer("timer");

		electricMeter.setCurrentElectricConsumption(200f);
		cooker.setStatus(OnOffStatus.ON);
		motionDetector.setMotion(false);
		timer.setTimerTriggered("", "inactiveTimer");
		timer.timerTriggered("", "inactiveTimer");
		
		assertTrue(timer.expectSchedule("inactiveTimer", 200));
	}
	

//	@Test
//	public void testUserPresent() {
//		electricMeter = Mock.mockElectricMeter("E1",location1,user);
//		motionDetector= Mock.mockMotionDetector("M1", location1, user);
//		cooker = Mock.mockCooker("C1", location1, user);
//		timer = Mock.mockTimer("T1");
//		prompter = Mock.mockPrompter("P1", location1, user);
//		messenger = Mock.mockMessenger("Me1", location1, user);
//		
//
//		electricMeter.setCurrentElectricConsumption(150.0f);
//		cooker.setStatus(OnOffStatus.ON);
//		motionDetector.setMotion(true);
//
//	}
//
//
//	@Test
//	public void testUserIsReminded() {
//		electricMeter = Mock.mockElectricMeter("E1",location2,user);
//		motionDetector= Mock.mockMotionDetector("M1", location2, user);
//		cooker = Mock.mockCooker("C1", location2, user);
//		timer = Mock.mockTimer("T1");
//		prompter = Mock.mockPrompter("P1", location2, user);
//		messenger = Mock.mockMessenger("Me1", location2, user);
//		
//		electricMeter.setCurrentElectricConsumption(150.0f);
//		cooker.setStatus(OnOffStatus.ON);
//		motionDetector.setMotion(false);
//		
//		assertTrue(messenger.expectSendMessage(null, Configuration.NOTIFICATION_WARNING_TITLE, Configuration.NOTIFICATION_WARNING_TITLE, null));
//
//
//	}
//
//	@Test
//	public void testUserIsAlerted(){
//		ArrayList<String> PossibleAnswer = new ArrayList<>();
//		PossibleAnswer.add("OK");
//		
//		electricMeter = Mock.mockElectricMeter("E1",location3,user);
//		motionDetector= Mock.mockMotionDetector("M1", location3, user);
//		cooker = Mock.mockCooker("C1", location3, user);
//		timer = Mock.mockTimer("T1");
//		prompter = Mock.mockPrompter("P1", location3, user);
//		messenger = Mock.mockMessenger("Me1", location3, user);
//		
//		electricMeter.setCurrentElectricConsumption(150.0f);
//		cooker.setStatus(OnOffStatus.ON);
//		motionDetector.setMotion(false);
//		
//		assertTrue(messenger.expectSendMessage(null, Configuration.NOTIFICATION_WARNING_TITLE, Configuration.NOTIFICATION_WARNING_TITLE, null));
//		assertTrue(prompter.expectAskCloseQuestion(null, "", Configuration.NOTIFICATION_CRITICAL_TITLE, Configuration.NOTIFICATION_CRITICAL_CONTENT,PossibleAnswer));
//		
//	}
//	
//	@Test void testUserMissing(){
//		electricMeter = Mock.mockElectricMeter("E1",location4,user);
//		motionDetector= Mock.mockMotionDetector("M1", location4, user);
//		cooker = Mock.mockCooker("C1", location4, user);
//		timer = Mock.mockTimer("T1");
//		prompter = Mock.mockPrompter("P1", location4, user);
//		messenger = Mock.mockMessenger("Me1", location4, user);
//
//		electricMeter.setCurrentElectricConsumption(150.0f);
//		cooker.setStatus(OnOffStatus.ON);
//		motionDetector.setMotion(false);
//
//		ArrayList<String> PossibleAnswer = new ArrayList<>();
//		PossibleAnswer.add("");
//
//		//timer.setTimerTriggered(newTimerTriggeredValue, timerId)
//		assertTrue(prompter.expectAskCloseQuestion(null, "", Configuration.NOTIFICATION_CRITICAL_TITLE, Configuration.NOTIFICATION_CRITICAL_CONTENT,PossibleAnswer));
//		assertTrue(messenger.expectSendMessage(null,Configuration.STOPPED_COOKER_TITLE ,Configuration.STOPPED_COOKER_CONTENT, null));
//		assertTrue(cooker.expectOff());
//	}

	
	

}
