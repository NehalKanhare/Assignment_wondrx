package tests;

import base.BaseTest;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.AboutPage;
import utils.CommonMethods;

public class AboutPageTest extends BaseTest {

	@Test(priority = 1, description = "Validate About page displays the correct page title")
	public void verifyAboutPageTitle() {
		String actualTitle = driver.getTitle();
		Assert.assertEquals(actualTitle, AboutPage.EXPECTED_TITLE, "Page title should match expected title.");
	}

	@Test(priority = 2, description = "Verify Wondrx logo is visible and functional on About page")
	public void verifyWondrxLogoIsDisplayed() {

		WebElement logo = CommonMethods.waitForElement(driver, AboutPage.LOGO);
		Assert.assertTrue(logo.isDisplayed(), "Logo should be visible on About page");

		// Optional: Check if logo is rendered correctly (not broken)
		Long naturalWidth = (Long) ((JavascriptExecutor) driver).executeScript("return arguments[0].naturalWidth;",
				logo);
		Assert.assertTrue(naturalWidth > 0, "Logo image should be rendered correctly");
	}

	@Test(priority = 3, description = "Verify that the Header Navigation Bar is visible and rendered properly on About page")
	public void verifyHeaderNavigationBarVisibility() {
		driver.get("https://www.wondrx.com/about");

		// Wait for the navigation bar
		WebElement navBar = CommonMethods.waitForElement(driver, AboutPage.HEADER_NAV_BAR);

		// Assertion: Header nav bar should be visible
		Assert.assertTrue(navBar.isDisplayed(), "Header Navigation Bar should be visible on About page");
	}

	@Test(priority = 4, description = "Verify Header Navigation Bar and Its Options on About Page")
	public void verifyHeaderNavigationBar() {

		// Verify header nav bar is visible and styled properly
		WebElement headerBar = CommonMethods.waitForElement(driver, AboutPage.HEADER_NAV_BAR);
		Assert.assertTrue(headerBar.isDisplayed(), "Header navigation bar should be visible");

		// Validate all header nav options are visible and rendered
		WebElement about = CommonMethods.waitForElement(driver, AboutPage.NAV_ABOUT);
		WebElement doctors = CommonMethods.waitForElement(driver, AboutPage.NAV_DOCTORS);
		WebElement consumers = CommonMethods.waitForElement(driver, AboutPage.NAV_CONSUMERS);
		WebElement providers = CommonMethods.waitForElement(driver, AboutPage.NAV_PROVIDERS);
		WebElement blogs = CommonMethods.waitForElement(driver, AboutPage.NAV_BLOGS);
		WebElement faqs = CommonMethods.waitForElement(driver, AboutPage.NAV_FAQS);
		

		// Assert individually
		Assert.assertTrue(about.isDisplayed(), "About link should be visible");
		Assert.assertTrue(doctors.isDisplayed(), "Doctors link should be visible");
		Assert.assertTrue(consumers.isDisplayed(), "Consumers link should be visible");
		Assert.assertTrue(providers.isDisplayed(), "Providers link should be visible");
		Assert.assertTrue(blogs.isDisplayed(), "Blogs link should be visible");
		Assert.assertTrue(faqs.isDisplayed(), "FAQs link should be visible");
		
	}

	@Test(priority = 5, description = "Verify clicking 'About' on About page reloads the same page")
	public void verifyAboutNavReloadsAboutPage() {

		// Capture current page load time (as a reference)
		long beforeClick = System.currentTimeMillis();

		// Locate and highlight the 'About' nav link
		WebElement aboutLink = CommonMethods.waitForElement(driver, AboutPage.NAV_ABOUT);

		// Click the 'About' link
		aboutLink.click();

		// Wait until page reloads — we’ll check via title
		CommonMethods.waitForElement(driver, AboutPage.HEADER_NAV_BAR);

		// Capture time again to verify reload duration
		long afterClick = System.currentTimeMillis();
		long duration = afterClick - beforeClick;

		// Assertion: Page should still have About page title after click
		String actualTitle = driver.getTitle();
		Assert.assertEquals(actualTitle, AboutPage.EXPECTED_TITLE, "Page title should remain the same after reload");

		// Assertion: Reload must take some noticeable time (basic check)
		Assert.assertTrue(duration > 1000, "Clicking About should reload and take time (non-instant)");
	}

	@Test(priority = 6, description = "Verify clicking 'Doctors' in header redirects to the Doctors page")
	public void verifyDoctorsNavRedirectsToDoctorsPage() {

		// Step 1: Wait and highlight 'Doctors' link
		WebElement doctorsLink = CommonMethods.waitForElement(driver, AboutPage.NAV_DOCTORS);

		// Step 2: Click on it
		doctorsLink.click();

		// Step 3: Wait for new page load
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
		}

		// Step 4: Validate URL or title
		String currentUrl = driver.getCurrentUrl();
		Assert.assertTrue(currentUrl.toLowerCase().contains("doctors"),
				"URL should contain 'doctors' after clicking the Doctors link");

	}

	@Test(priority = 7, description = "Verify clicking 'Consumers' in header redirects to the Consumers page")
	public void verifyConsumersNavRedirectsToConsumersPage() {

		// Step 1: Wait for and highlight 'Consumers' nav link
		WebElement consumersLink = CommonMethods.waitForElement(driver, AboutPage.NAV_CONSUMERS);

		// Step 2: Click it
		consumersLink.click();

		// Step 3: Wait briefly for page to load
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
		}

		// Step 4: Get new URL and title
		String currentUrl = driver.getCurrentUrl();
		String title = driver.getTitle();

		System.out.println("Current URL: " + currentUrl);
		System.out.println("Page Title: " + title);

		// Step 5: Assert the URL or title shows user is on Consumers page
		Assert.assertTrue(currentUrl.toLowerCase().contains("consumer") || title.toLowerCase().contains("consumer"),
				"Navigation to Consumers page failed. URL or title should contain 'consumer'.");
	}

	@Test(priority = 8, description = "Verify clicking 'Providers' in header redirects to the Providers page")
	public void verifyProvidersNavRedirectsToProvidersPage() {

		// Step 1: Wait for and highlight 'Providers' nav link
		WebElement providersLink = CommonMethods.waitForElement(driver, AboutPage.NAV_PROVIDERS);

		// Step 2: Click the link
		providersLink.click();

		// Step 3: Wait briefly for page to load
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
		}

		// Step 4: Capture URL and title
		String currentUrl = driver.getCurrentUrl();
		String title = driver.getTitle();

		System.out.println("Current URL: " + currentUrl);
		System.out.println("Page Title: " + title);

		// Step 5: Validate Providers page loaded
		Assert.assertTrue(currentUrl.toLowerCase().contains("provider") || title.toLowerCase().contains("provider"),
				"Navigation to Providers page failed. URL or title should contain 'provider'.");
	}

	@Test(priority = 9, description = "Verify clicking 'Blogs' in header redirects to the Blogs page")
	public void verifyBlogsNavRedirectsToBlogsPage() {

		// Step 1: Wait for and highlight 'Blogs' nav link
		WebElement blogsLink = CommonMethods.waitForElement(driver, AboutPage.NAV_BLOGS);

		// Step 2: Click it
		blogsLink.click();

		// Step 3: Wait for redirection
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
		}

		// Step 4: Capture redirected URL and title
		String currentUrl = driver.getCurrentUrl();
		String title = driver.getTitle();

		System.out.println("Current URL: " + currentUrl);
		System.out.println("Page Title: " + title);

		// Step 5: Validate Blogs page loaded
		Assert.assertTrue(currentUrl.toLowerCase().contains("blog") || title.toLowerCase().contains("blog"),
				"Navigation to Blogs page failed. URL or title should contain 'blog'.");
	}

	@Test(priority = 10, description = "Verify clicking 'FAQs' in header redirects to the FAQs page")
	public void verifyFAQsNavRedirectsToFAQsPage() {

		// Step 1: Wait for and highlight 'FAQs' nav link
		WebElement faqsLink = CommonMethods.waitForElement(driver, AboutPage.NAV_FAQS);

		// Step 2: Click it
		faqsLink.click();

		// Step 3: Wait for navigation
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
		}

		// Step 4: Capture current URL and page title
		String currentUrl = driver.getCurrentUrl();
		String title = driver.getTitle();

		System.out.println("Current URL: " + currentUrl);
		System.out.println("Page Title: " + title);

		// Step 5: Assert redirected to FAQs page
		Assert.assertTrue(currentUrl.toLowerCase().contains("faq") || title.toLowerCase().contains("faq"),
				"Navigation to FAQs page failed. URL or title should contain 'faq'.");
	}

	@Test(priority = 11, description = "Verify Contact Us button is visible and redirects correctly to Contact page")
	public void verifyContactUsButtonRedirectsCorrectly() {

		// Step 1: Wait for and highlight the 'Contact Us' button
		WebElement contactUsBtn = CommonMethods.waitForElement(driver, AboutPage.CONTACT_US_BUTTON);

		// Step 2: Verify it's visible and clickable
		Assert.assertTrue(contactUsBtn.isDisplayed(), "'Contact Us' button should be visible");
		Assert.assertTrue(contactUsBtn.isEnabled(), "'Contact Us' button should be clickable");

		// Step 3: Click it
		contactUsBtn.click();

		// Step 4: Wait for redirection
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
		}

		// Step 5: Validate URL or Contact Us section presence
		String currentUrl = driver.getCurrentUrl();
		String title = driver.getTitle();

		System.out.println("Redirected URL: " + currentUrl);
		System.out.println("Page Title: " + title);

		// Basic validation based on URL or Title
		Assert.assertTrue(currentUrl.toLowerCase().contains("contact") || title.toLowerCase().contains("contact"),
				"User should be redirected to the Contact Us page");

		// Optional: Validate presence of form/contact details
		// Example locator (update as needed)
		By contactFormLocator = By.xpath("//form | //h1[contains(text(),'Contact')]");
		WebElement contactForm = CommonMethods.waitForElement(driver, contactFormLocator);
		Assert.assertTrue(contactForm.isDisplayed(), "Contact form or section should be visible");
	}

	@Test(priority = 12, description = "Verify 'About Us by Wondrx' image is displayed properly on the About page")
	public void verifyAboutUsImageIsDisplayed() {

		// Step 1: Wait and highlight image
		WebElement aboutUsImage = CommonMethods.waitForElement(driver, AboutPage.ABOUT_US_IMAGE);

		// Step 2: Validate visibility
		Assert.assertTrue(aboutUsImage.isDisplayed(), "'About Us by Wondrx' image should be visible on About page");

		// Step 3: Optional - validate image is loaded (naturalWidth > 0)
		Long naturalWidth = (Long) ((JavascriptExecutor) driver).executeScript("return arguments[0].naturalWidth;",
				aboutUsImage);

		Assert.assertTrue(naturalWidth > 0, "The 'About Us' image should be fully loaded and rendered");
	}

	@Test(priority = 13, description = "Verify 'Who Are We?' title is displayed on the About page")
	public void verifyWhoAreWeTitleIsDisplayed() {

		// Scroll down to where the section should appear
		((JavascriptExecutor) driver).executeScript("window.scrollBy(0, 1000)");

		// Wait and highlight the title
		WebElement whoAreWeTitle = CommonMethods.waitForElement(driver, AboutPage.WHO_ARE_WE_TITLE);

		// Validate it’s visible
		Assert.assertTrue(whoAreWeTitle.isDisplayed(), "'Who Are We?' title should be visible on the About page");
	}

	@Test(priority = 14, description = "Verify 'Who Are We?' section content is displayed correctly on About page")
	public void verifyWhoAreWeContentIsDisplayedCorrectly() {

		// Scroll down to the section
		((JavascriptExecutor) driver).executeScript("window.scrollBy(0, 1000)");

		// Wait and highlight the paragraph
		WebElement whoAreWePara = CommonMethods.waitForElement(driver, AboutPage.WHO_ARE_WE_PARAGRAPH);

		// Validate visibility
		Assert.assertTrue(whoAreWePara.isDisplayed(), "'Who Are We?' paragraph should be visible under the title");

		// Validate content (or a major substring)
		String actualText = whoAreWePara.getText().trim();
		String expectedTextStart = "WONDRx (Pronounced as Wonder Rx) is an innovation-based Health-Tech start-up";

		Assert.assertTrue(actualText.startsWith(expectedTextStart),
				"Paragraph content under 'Who Are We?' does not match expected start. Found: " + actualText);
	}

	@Test(priority = 15, description = "Verify 'Vision We Have' section displays correct heading and paragraph")
	public void verifyVisionWeHaveSectionContent() {

		// Scroll to the Vision section
		((JavascriptExecutor) driver).executeScript("window.scrollBy(0, 1300)");

		// Wait for and highlight the heading
		WebElement heading = CommonMethods.waitForElement(driver, AboutPage.VISION_HEADING);
		Assert.assertTrue(heading.isDisplayed(), "'Vision We Have' heading should be visible");

		// Wait for and highlight the paragraph
		WebElement paragraph = CommonMethods.waitForElement(driver, AboutPage.VISION_PARAGRAPH);
		Assert.assertTrue(paragraph.isDisplayed(), "'Vision We Have' paragraph should be visible");

		// Validate paragraph content
		String actualText = paragraph.getText().trim();
		String expectedText = "Establish credible and positive change in the Health Ecosystem through technology and innovation integrated into the Smart Rx Kit; thereby improving the lives of patients while positively impacting caregivers, healthcare providers, and payers.";

		Assert.assertEquals(actualText, expectedText, "'Vision We Have' paragraph content does not match expected.");
	}

	@Test(priority = 16, description = "Verify 'Mission We Are On' image is displayed properly on About page")
	public void verifyMissionImageIsDisplayed() {

		// Scroll to Mission section
		((JavascriptExecutor) driver).executeScript("window.scrollBy(0, 1600)");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
		}

		// Wait for and highlight the image
		WebElement missionImage = CommonMethods.waitForElement(driver, AboutPage.MISSION_IMAGE);
		Assert.assertTrue(missionImage.isDisplayed(), "'Mission We Are On' image should be visible");

		// Check if image is loaded (naturalWidth > 0)
		Long naturalWidth = (Long) ((JavascriptExecutor) driver).executeScript("return arguments[0].naturalWidth;",
				missionImage);

		Assert.assertTrue(naturalWidth > 0, "'Mission We Are On' image should be fully loaded and rendered");
	}

	@Test(priority = 17, description = "Verify 'WONDRx Values' section and all value icons/images are displayed correctly")
	public void verifyWondrxValuesSectionAndIconsDisplayed() {

		// Scroll to the section
		((JavascriptExecutor) driver).executeScript("window.scrollBy(0, 2000)");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
		}

		WebElement heading = CommonMethods.waitForElement(driver, AboutPage.VALUES_SECTION_IMAGE);
		Assert.assertTrue(heading.isDisplayed(), "'WONDRx Values' section heading should be visible");

	}

	@Test(priority = 18, description = "Verify Wondrx logo and footer text are displayed correctly")
	public void verifyFooterLogoAndTextDisplayed() {

		// Scroll to footer
		((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
		}

		// Verify Wondrx logo
		WebElement logo = CommonMethods.waitForElement(driver, AboutPage.FOOTER_LOGO);
		Assert.assertTrue(logo.isDisplayed(), "Footer logo should be visible");

		// Verify footer text
		WebElement footerText = CommonMethods.waitForElement(driver, AboutPage.FOOTER_TEXT);
		Assert.assertTrue(footerText.isDisplayed(), "Footer text should be visible");

		String actualFooterText = footerText.getText().trim();
		String expectedFooterText = "Enabling Healthcare access to every nook and corner of India Digitally";
		Assert.assertEquals(actualFooterText, expectedFooterText, "Footer text does not match expected.");
	}

	@Test(priority = 19, description = "Verify all expected footer options are visible on the About page")
	public void verifyAllFooterLinksAreDisplayed() {

		// Scroll to footer section
		((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
		}

		// Wait and verify each footer link
		Assert.assertTrue(CommonMethods.waitForElement(driver, AboutPage.FOOTER_LINK_ABOUT).isDisplayed(),
				"'About' link should be visible");
		Assert.assertTrue(CommonMethods.waitForElement(driver, AboutPage.FOOTER_LINK_DOCTORS).isDisplayed(),
				"'Doctors' link should be visible");
		Assert.assertTrue(CommonMethods.waitForElement(driver, AboutPage.FOOTER_LINK_CONSUMERS).isDisplayed(),
				"'Consumers' link should be visible");
		Assert.assertTrue(CommonMethods.waitForElement(driver, AboutPage.FOOTER_LINK_PROVIDERS).isDisplayed(),
				"'Providers' link should be visible");
		Assert.assertTrue(CommonMethods.waitForElement(driver, AboutPage.FOOTER_LINK_BLOGS).isDisplayed(),
				"'Blogs' link should be visible");
		Assert.assertTrue(CommonMethods.waitForElement(driver, AboutPage.FOOTER_LINK_FAQS).isDisplayed(),
				"'FAQs' link should be visible");
		Assert.assertTrue(CommonMethods.waitForElement(driver, AboutPage.FOOTER_LINK_PRIVACY_POLICY).isDisplayed(),
				"'Privacy Policy' link should be visible");
		Assert.assertTrue(CommonMethods.waitForElement(driver, AboutPage.FOOTER_LINK_TERMS).isDisplayed(),
				"'Terms & Conditions' link should be visible");
	}

	@Test(priority = 20, description = "Verify all footer links are clickable on the About page")
	public void verifyAllFooterLinksAreClickable() {
		((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
		}

		// Validate each footer link is clickable
		verifyClickable(AboutPage.FOOTER_LINK_ABOUT, "About");
		verifyClickable(AboutPage.FOOTER_LINK_DOCTORS, "Doctors");
		verifyClickable(AboutPage.FOOTER_LINK_CONSUMERS, "Consumers");
		verifyClickable(AboutPage.FOOTER_LINK_PROVIDERS, "Providers");
		verifyClickable(AboutPage.FOOTER_LINK_BLOGS, "Blogs");
		verifyClickable(AboutPage.FOOTER_LINK_FAQS, "FAQs");
		verifyClickable(AboutPage.FOOTER_LINK_PRIVACY_POLICY, "Privacy Policy");
		verifyClickable(AboutPage.FOOTER_LINK_TERMS, "Terms & Conditions");
	}

	// Reusable method
	public void verifyClickable(By locator, String linkName) {
		WebElement link = CommonMethods.waitForElement(driver, locator);
		Assert.assertTrue(link.isDisplayed(), linkName + " link should be visible");
		Assert.assertTrue(link.isEnabled(), linkName + " link should be enabled/clickable");
		System.out.println(linkName + " link is clickable.");
	}

	@Test(priority = 21, description = "Verify footer links redirect to the correct pages")
	public void verifyFooterLinksRedirectToCorrectPages() {
		checkFooterRedirection(AboutPage.FOOTER_LINK_ABOUT, "https://www.wondrx.com/about");
		checkFooterRedirection(AboutPage.FOOTER_LINK_DOCTORS, "https://www.wondrx.com/doctors");
		checkFooterRedirection(AboutPage.FOOTER_LINK_CONSUMERS, "https://www.wondrx.com/consumer");
		checkFooterRedirection(AboutPage.FOOTER_LINK_PROVIDERS, "https://www.wondrx.com/providers");
		checkFooterRedirection(AboutPage.FOOTER_LINK_BLOGS, "https://www.wondrx.com/blogs");
		checkFooterRedirection(AboutPage.FOOTER_LINK_FAQS, "https://www.wondrx.com/faqs");
		checkFooterRedirection(AboutPage.FOOTER_LINK_PRIVACY_POLICY, "https://www.wondrx.com/privacypolicy");
		checkFooterRedirection(AboutPage.FOOTER_LINK_TERMS, "https://www.wondrx.com/termsconditions");
	}

	// Reusable redirection check
	public void checkFooterRedirection(By locator, String expectedUrl) {
		((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
		}

		WebElement link = CommonMethods.waitForElement(driver, locator);
		Assert.assertTrue(link.isDisplayed(), "Footer link should be visible");
		link.click();

		// Wait for redirect
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
		}
		String actualUrl = driver.getCurrentUrl().split("\\?")[0]; // remove tracking params if any
		Assert.assertEquals(actualUrl, expectedUrl, "Redirection URL mismatch for: " + expectedUrl);
	}

	@Test(priority = 22, description = "Verify copyright notice is displayed")
	public void verifyCopyrightNoticeIsDisplayed() {

		((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
		}

		WebElement copyright = CommonMethods.waitForElement(driver, AboutPage.COPYRIGHT_NOTICE);

		Assert.assertTrue(copyright.isDisplayed(), "Copyright notice should be visible");

		String actualText = copyright.getText().trim();
		String expectedText = "© 2025, WONDRx";
		Assert.assertEquals(actualText, expectedText, "Mismatch in copyright text.");
	}

	@Test(priority = 23, description = "Verify 'Follow us' label and social media icons are visible")
	public void verifyFollowUsLabelAndSocialIcons() {

		((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
		}

		// Verify 'Follow us' label
		WebElement followUsLabel = CommonMethods.waitForElement(driver, AboutPage.FOLLOW_US_TEXT);
		Assert.assertTrue(followUsLabel.isDisplayed(), "'Follow us' text should be visible");

		// Verify each icon
		Assert.assertTrue(CommonMethods.waitForElement(driver, AboutPage.ICON_LINKEDIN).isDisplayed(),
				"LinkedIn icon should be visible");
		Assert.assertTrue(CommonMethods.waitForElement(driver, AboutPage.ICON_FACEBOOK).isDisplayed(),
				"Facebook icon should be visible");
		Assert.assertTrue(CommonMethods.waitForElement(driver, AboutPage.ICON_YOUTUBE).isDisplayed(),
				"YouTube icon should be visible");
		Assert.assertTrue(CommonMethods.waitForElement(driver, AboutPage.ICON_INSTAGRAM).isDisplayed(),
				"Instagram icon should be visible");
	}

	@Test(priority = 24, description = "Verify social icons are clickable and open correct social media pages")
	public void verifySocialIconsAreClickableAndOpenCorrectPages() {
		((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
		}

		verifyIconOpensNewTab(AboutPage.ICON_LINKEDIN, "linkedin.com");
		verifyIconOpensNewTab(AboutPage.ICON_FACEBOOK, "facebook.com");
		verifyIconOpensNewTab(AboutPage.ICON_YOUTUBE, "youtube.com");
		verifyIconOpensNewTab(AboutPage.ICON_INSTAGRAM, "instagram.com");
	}

	// Helper method to click icon and check new tab/window
	public void verifyIconOpensNewTab(By iconLocator, String expectedDomain) {
		String originalWindow = driver.getWindowHandle();
		WebElement icon = CommonMethods.waitForElement(driver, iconLocator);
		Assert.assertTrue(icon.isDisplayed(), expectedDomain + " icon should be visible");

		icon.click();

		// Wait and switch to new window
		new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.numberOfWindowsToBe(2));

		Set<String> allWindows = driver.getWindowHandles();
		for (String window : allWindows) {
			if (!window.equals(originalWindow)) {
				driver.switchTo().window(window);
				break;
			}
		}

		String currentUrl = driver.getCurrentUrl();
		Assert.assertTrue(currentUrl.contains(expectedDomain),
				"Expected to be on " + expectedDomain + " but found: " + currentUrl);

		// Close new tab and return to original
		driver.close();
		driver.switchTo().window(originalWindow);
	}

	@Test(priority = 25, description = "Verify social icons redirect to the correct URLs")
	public void verifySocialIconsRedirectToCorrectURLs() {
		((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
		}

		verifyIconRedirect(AboutPage.ICON_LINKEDIN, "https://www.linkedin.com/company/wondrx/");
		verifyIconRedirect(AboutPage.ICON_FACEBOOK, "https://www.facebook.com/wondrx");
		verifyIconRedirect(AboutPage.ICON_YOUTUBE, "https://www.youtube.com/channel/UC1B1kBI38nOUWpAHhOknxLw");
		verifyIconRedirect(AboutPage.ICON_INSTAGRAM, "https://www.instagram.com/wondrx_india/");
	}

	// Generic method for checking redirection
	public void verifyIconRedirect(By iconLocator, String expectedUrl) {
		String originalWindow = driver.getWindowHandle();
		WebElement icon = CommonMethods.waitForElement(driver, iconLocator);
		Assert.assertTrue(icon.isDisplayed(), "Icon should be visible for " + expectedUrl);
		icon.click();

		// Wait and switch to new tab
		new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.numberOfWindowsToBe(2));
		Set<String> allWindows = driver.getWindowHandles();
		for (String window : allWindows) {
			if (!window.equals(originalWindow)) {
				driver.switchTo().window(window);
				break;
			}
		}

		// Wait for redirection and fetch current URL
		new WebDriverWait(driver, Duration.ofSeconds(10))
				.until(ExpectedConditions.urlContains(expectedUrl.split("//")[1].split("/")[0]));

		String actualUrl = driver.getCurrentUrl();
		Assert.assertTrue(actualUrl.startsWith(expectedUrl),
				"Expected URL to start with: " + expectedUrl + " but found: " + actualUrl);

		driver.close();
		driver.switchTo().window(originalWindow);
	}

	@Test(priority = 26, description = "Verify social media icons are aligned horizontally in the footer")
	public void verifySocialMediaIconsAreHorizontallyAligned() {
		((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
		}

		List<WebElement> icons = driver.findElements(AboutPage.ALL_SOCIAL_ICONS);
		Assert.assertEquals(icons.size(), 4, "There should be exactly 4 social media icons");

		int firstIconY = icons.get(0).getLocation().getY();
		for (WebElement icon : icons) {
			int y = icon.getLocation().getY();
			CommonMethods.highlightElement(driver, icon);
			Assert.assertEquals(y, firstIconY,
					"All social media icons should be aligned horizontally (same Y position)");
		}

		//  Check consistent horizontal spacing (X-axis gap)
		for (int i = 0; i < icons.size() - 1; i++) {
			int currentX = icons.get(i).getLocation().getX();
			int nextX = icons.get(i + 1).getLocation().getX();
			int gap = nextX - currentX;
			Assert.assertTrue(gap >= 10, "Gap between icons should be reasonable: found " + gap + "px");
		}
	}

	@Test(priority = 27, description = "Verify FAQs and Contact Us button are horizontally aligned in header")
	public void verifyFAQsAndContactUsAlignment() {

		WebElement faqsLink = CommonMethods.waitForElement(driver, AboutPage.NAV_FAQS);
		WebElement contactUsBtn = CommonMethods.waitForElement(driver, AboutPage.CONTACT_US_BUTTON);

		// Highlight both elements
		CommonMethods.highlightElement(driver, faqsLink);
		CommonMethods.highlightElement(driver, contactUsBtn);

		int faqsY = faqsLink.getLocation().getY();
		int contactY = contactUsBtn.getLocation().getY();

		Assert.assertEquals(contactY, faqsY,
				"'Contact Us' button should be horizontally aligned with 'FAQs' link (Y mismatch: " + contactY + " vs "
						+ faqsY + ")");
	}

	@Test(priority = 28, description = "Verify Header Navigation Bar and Its Options on About Page")
	public void verifyHeaderNavigationBar1() {

		// Step 1: Verify the main header nav bar is displayed
		WebElement headerBar = CommonMethods.waitForElement(driver, AboutPage.HEADER_NAV_BAR);
		Assert.assertTrue(headerBar.isDisplayed(), "Header navigation bar should be visible");

		// Step 2: Fetch all nav links
		WebElement about = CommonMethods.waitForElement(driver, AboutPage.NAV_ABOUT);
		WebElement doctors = CommonMethods.waitForElement(driver, AboutPage.NAV_DOCTORS);
		WebElement consumers = CommonMethods.waitForElement(driver, AboutPage.NAV_CONSUMERS);
		WebElement providers = CommonMethods.waitForElement(driver, AboutPage.NAV_PROVIDERS);
		WebElement blogs = CommonMethods.waitForElement(driver, AboutPage.NAV_BLOGS);
		WebElement faqs = CommonMethods.waitForElement(driver, AboutPage.NAV_FAQS);

		// Step 3: Visibility Checks
		Assert.assertTrue(about.isDisplayed(), "About link should be visible");
		Assert.assertTrue(doctors.isDisplayed(), "Doctors link should be visible");
		Assert.assertTrue(consumers.isDisplayed(), "Consumers link should be visible");
		Assert.assertTrue(providers.isDisplayed(), "Providers link should be visible");
		Assert.assertTrue(blogs.isDisplayed(), "Blogs link should be visible");
		Assert.assertTrue(faqs.isDisplayed(), "FAQs link should be visible");

		// Step 4: Alignment Check — All should share the same Y position
		int yBase = about.getLocation().getY();
		Assert.assertEquals(doctors.getLocation().getY(), yBase, "Doctors link not aligned");
		Assert.assertEquals(consumers.getLocation().getY(), yBase, "Consumers link not aligned");
		Assert.assertEquals(providers.getLocation().getY(), yBase, "Providers link not aligned");
		Assert.assertEquals(blogs.getLocation().getY(), yBase, "Blogs link not aligned");
		Assert.assertEquals(faqs.getLocation().getY(), yBase, "FAQs link not aligned");

		// Step 5: Optional - Highlight for visual debugging
		CommonMethods.highlightElement(driver, about);
		CommonMethods.highlightElement(driver, doctors);
		CommonMethods.highlightElement(driver, consumers);
		CommonMethods.highlightElement(driver, providers);
		CommonMethods.highlightElement(driver, blogs);
		CommonMethods.highlightElement(driver, faqs);
	}

	@Test(priority = 29, description = "Verify footer links are aligned horizontally on About Page")
	public void verifyFooterLinksAlignment() {
		((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
		}

		// Manually collect all footer link elements
		List<WebElement> footerLinks = new ArrayList<>();
		footerLinks.add(CommonMethods.waitForElement(driver, AboutPage.FOOTER_LINK_ABOUT));
		footerLinks.add(CommonMethods.waitForElement(driver, AboutPage.FOOTER_LINK_DOCTORS));
		footerLinks.add(CommonMethods.waitForElement(driver, AboutPage.FOOTER_LINK_CONSUMERS));
		footerLinks.add(CommonMethods.waitForElement(driver, AboutPage.FOOTER_LINK_PROVIDERS));
		footerLinks.add(CommonMethods.waitForElement(driver, AboutPage.FOOTER_LINK_BLOGS));
		footerLinks.add(CommonMethods.waitForElement(driver, AboutPage.FOOTER_LINK_FAQS));
		footerLinks.add(CommonMethods.waitForElement(driver, AboutPage.FOOTER_LINK_PRIVACY_POLICY));
		footerLinks.add(CommonMethods.waitForElement(driver, AboutPage.FOOTER_LINK_TERMS));

		// Validate all are aligned (same Y-coordinate)
		for (int i = 0; i < footerLinks.size(); i++) {
			WebElement link1 = footerLinks.get(i);
			int y1 = link1.getLocation().getY();
			String label1 = link1.getText().trim();
			CommonMethods.highlightElement(driver, link1);

			for (int j = i + 1; j < footerLinks.size(); j++) {
				WebElement link2 = footerLinks.get(j);
				int y2 = link2.getLocation().getY();
				String label2 = link2.getText().trim();

				Assert.assertEquals(y1, y2,
						"Alignment mismatch: '" + label1 + "' (Y=" + y1 + ") vs '" + label2 + "' (Y=" + y2 + ")");
			}
		}
	}

	@Test(priority = 30, description = "Verify proper spacing and alignment between 'Who Are We?' and 'Vision We Have' headings and their paragraphs")
	public void verifySpacingBetweenSections() {

		// Wait and locate both heading and paragraph for "Who Are We?"
		WebElement whoHeading = CommonMethods.waitForElement(driver, AboutPage.WHO_ARE_WE_TITLE);
		WebElement whoPara = CommonMethods.waitForElement(driver, AboutPage.WHO_ARE_WE_PARAGRAPH);

		// Scroll into view and highlight
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", whoHeading);
		CommonMethods.highlightElement(driver, whoHeading);
		CommonMethods.highlightElement(driver, whoPara);

		int whoHeadingY = whoHeading.getLocation().getY();
		int whoParaY = whoPara.getLocation().getY();

		int whoSpacing = whoParaY - (whoHeadingY + whoHeading.getSize().getHeight());
		Assert.assertTrue(whoSpacing >= 10,
				"Spacing too tight between 'Who Are We?' heading and paragraph. Found spacing: " + whoSpacing + "px");

		// Repeat for "Vision We Have"
		WebElement visionHeading = CommonMethods.waitForElement(driver, AboutPage.VISION_HEADING);
		WebElement visionPara = CommonMethods.waitForElement(driver, AboutPage.VISION_PARAGRAPH);

		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", visionHeading);
		CommonMethods.highlightElement(driver, visionHeading);
		CommonMethods.highlightElement(driver, visionPara);

		int visionHeadingY = visionHeading.getLocation().getY();
		int visionParaY = visionPara.getLocation().getY();

		int visionSpacing = visionParaY - (visionHeadingY + visionHeading.getSize().getHeight());
		Assert.assertTrue(visionSpacing >= 10,
				"Spacing too tight between 'Vision We Have' heading and paragraph. Found spacing: " + visionSpacing
						+ "px");
	}

}