package Week4_HomeAssignment;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.commons.compress.harmony.unpack200.bytecode.ClassFile;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class Amazon {

	public static void main(String[] args) throws IOException {
		ChromeDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		driver.get("https://www.amazon.in/");		
		driver.findElement(By.id("twotabsearchtextbox")).sendKeys("oneplus 9 pro");
		driver.findElement(By.id("nav-search-submit-button")).click();
		WebElement price = driver.findElement(By.xpath("//span[text()='27,999']"));
		String p1 = price.getText();
		System.out.println("Price of Mobile is: " +p1);
		WebElement rating = driver.findElement(By.xpath("//span[@class='a-size-base s-underline-text']"));
		String r1 = rating.getText();
		System.out.println("Rating of Mobile is :" +r1);
		driver.findElement(By.xpath("//a[@class='a-link-normal s-underline-text s-underline-link-text s-link-style a-text-normal']")).click();
		Set<String> nw = driver.getWindowHandles();
		System.out.println(nw);
		List<String>window = new ArrayList<String>(nw);
		String np = driver.switchTo().window(window.get(1)).getTitle();
		System.out.println("child Window is:"+ np);
		File scr = driver.getScreenshotAs(OutputType.FILE);
		File dest=new File("./snapshots/Amazonsnap.png");
		FileUtils.copyFile(scr, dest);
		WebElement atc = driver.findElement(By.id("add-to-cart-button"));
		Actions opt =new Actions(driver);
		opt.moveToElement(atc).perform();
		atc.click();
		WebElement cart = driver.findElement(By.xpath("(//span[text()='₹27,999.00'])[6]"));
		String text = cart.getText();
		System.out.println(text);
		if (text.equalsIgnoreCase("₹27,999.00")) {

			System.out.println("it's Verified");
		}
		else {
			System.out.println("it's Not Verified");
		}


	}

}


