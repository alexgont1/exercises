package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {

	WebDriver driver;

	public HomePage(WebDriver driver) {
		this.driver = driver;
	}

	public void goToHome() {

		driver.get("https://www.weightwatchers.com/us/");
		System.out.println("\tOpen Home Page");

		boolean b = driver.getTitle().equals("WW (Weight Watchers): Weight Loss & Wellness Help");

		if (b) {
			System.out.println("You are on Home Page");
		} else {
			System.out.println("Error! Home Page is not available!");
		}
	}

	public void goToFindaStudio() {

		driver.findElement(By.className("find-a-meeting")).click();
		String expTitle = "Find WW Studios & Meetings Near You | WW USA";
		System.out.println("\tOpen Find a Studio Page");

		// Title html code contains '&nbsp;' but for user all seems OK
		// So I replace this code, and then compare to expected text
		String actTitle = driver.getTitle().replace(String.valueOf((char) 160), " ");

		boolean b = actTitle.contains(expTitle);

		if (b) {
			System.out.println("You are on Find a Studio Page");
		} else {
			System.out.println("***Error in page title!***\nExpected: " + expTitle + "\n  Actual: " + actTitle);
		}
	}

}
