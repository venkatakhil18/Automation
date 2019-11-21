package TestCases;

import org.testng.annotations.Test;

import Reuse.BusinessFunctions;
import base.LaunchBrowser;

public class TestCase1 extends LaunchBrowser {
	
	@Test
	public void testHomePage() throws Exception {
		
		BusinessFunctions bfs = new BusinessFunctions();
		
		bfs.goToApplication();
	}
	
	

}
