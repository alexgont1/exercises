package testcases;

import java.io.File;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import pages.FindaStudioPage;
import pages.HomePage;

public class FindaStudioTest {

	public static void main(String[] args) {

		// path to chrome driver = folder with jar
		System.setProperty("webdriver.chrome.driver",
				(Paths.get(".").normalize().toAbsolutePath().toString() + File.separator + "chromedriver.exe"));
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		HomePage home = new HomePage(driver);
		home.goToHome();
		home.goToFindaStudio();

		FindaStudioPage studio = new FindaStudioPage(driver);
		studio.meetingSearch("meetingSearch", "10011");
		studio.printFirstSearhResult("location__name", "location__distance");

		String clickedName = studio.titleClick("location__name");
		studio.checkTitleClickResult("location__name", clickedName);

		studio.operatingHours("hours-list-item-day", "hours-list-item-hours");
		studio.printMeetings("TUE", "schedule-detailed-day-label", "schedule-detailed-day-meetings");

		driver.quit();

	}
}
