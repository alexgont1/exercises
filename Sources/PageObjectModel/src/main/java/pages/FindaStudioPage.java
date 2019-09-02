package pages;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FindaStudioPage {

	WebDriver driver;

	public FindaStudioPage(WebDriver driver) {
		this.driver = driver;
	}

	public void meetingSearch(String locator, String text) {

		WebElement w = driver.findElement(By.id(locator));
		w.sendKeys(text);
		w.sendKeys(Keys.ENTER);
		System.out.println("Searching...");
	}

	public void printFirstSearhResult(String title, String distance) {

		String name = driver.findElement(By.className(title)).getText();
		String dist = driver.findElement(By.className(distance)).getText();
		System.out.println("1st result title - " + name + ", distance = " + dist);
	}

	public String titleClick(String title) {
		WebElement w = driver.findElement(By.className(title));
		String name = w.getText();
		System.out.println("Click on title " + name);
		w.click();
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.className("meeting-detail")));
		System.out.println("Meeting details are available.");
		return name;
	}

	public void checkTitleClickResult(String title, String clickedName) {
		String name = driver.findElement(By.className(title)).getText();
		boolean b = (clickedName.equals(name));

		if (b) {
			System.out.println("Title matches with the name of the 1st searched result that was clicked");
		} else {
			System.out.println("Error! Clicked on " + clickedName + ", but Title is " + name);
		}
	}

	public void operatingHours(String dayOfWeekLocator, String hoursLocator) {
		// current day:
		String today = LocalDate.now().getDayOfWeek().name().substring(0, 3);
		// list of all days on page:
		List<WebElement> day = driver.findElements(By.className(dayOfWeekLocator));

		int i = 0;
		while (!today.equals(day.get(i).getText())) {
			i++;
			if (i > 7) {
				System.out.println("Error! Day was not found");
				break;
			}
		}

		// list of all hour blocks on page:
		List<WebElement> hours = driver.findElements(By.className(hoursLocator));

		System.out.println("Today is " + day.get(i).getText() + ", hours of operation:\n" + hours.get(i).getText());
	}

	public void printMeetings(String printDay, String dayLocator, String personsLocator) {

		// list of all day blocks on page:
		List<WebElement> day = driver.findElements(By.className(dayLocator));

		int i = 0;
		while (!printDay.equals(day.get(i).getText())) {
			i++;
			if (i > 7) {
				System.out.println("Error! Day was not found");
				break;
			}
		}

		// list of all meeting blocks on page:
		List<WebElement> personsOnPage = driver.findElements(By.className(personsLocator));
		String personsInDay = personsOnPage.get(i).getText();

		// find only persons in list (don't take time)
		Pattern pattern = Pattern.compile("(?m)^[\\D]+$");
		List<String> personsList = new ArrayList<String>();
		Matcher matcher = pattern.matcher(personsInDay);
		while (matcher.find()) {
			personsList.add(matcher.group(0));
		}

		// count occurrence and print
		Set<String> s = new HashSet<String>(personsList);
		System.out.println("Meetings for each person on " + printDay + ":");
		for (String key : s) {
			System.out.println(key + " " + Collections.frequency(personsList, key));
		}

	}

}
