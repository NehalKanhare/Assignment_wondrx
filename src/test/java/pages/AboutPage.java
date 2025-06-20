package pages;

import org.openqa.selenium.By;

	public class AboutPage {
	    public static final String EXPECTED_TITLE = "WONDRx | Smart Rx Kit that digitizes your Rx in No Time!";

	    public static final By LOGO = By.cssSelector("a.navbar-brand img");

	    public static final By HEADER_NAV_BAR = By.cssSelector("nav.navbar"); // Adjust if needed
	    public static final By NAV_ABOUT = By.linkText("About");
	    public static final By NAV_DOCTORS = By.linkText("Doctors");
	    public static final By NAV_CONSUMERS = By.linkText("Consumers");
	    public static final By NAV_PROVIDERS = By.linkText("Providers");
	    public static final By NAV_BLOGS = By.linkText("Blogs");
	    public static final By NAV_FAQS = By.linkText("FAQs");
	    public static final By NAV_PRIVACY = By.linkText("Privacy Policy");
	    public static final By NAV_TERMS = By.linkText("Terms & Conditions");
	    public static final By CONTACT_US_BUTTON = By.xpath("//a[contains(text(),'Contact Us') or contains(text(),'CONTACT US')]");
	    public static final By ABOUT_US_IMAGE = By.xpath("//img[@src='assets/images/about.webp']");
	    public static final By WHO_ARE_WE_TITLE = By.xpath("//*[text()='Who Are We?']");
	    public static final By WHO_ARE_WE_PARAGRAPH = By.xpath("(//p[@id='whoP'])[1]");
	    public static final By VISION_HEADING = By.xpath("//*[text()='Vision We Have']");
	    public static final By VISION_PARAGRAPH = By.xpath("//div[2]//p[2]");
	    public static final By MISSION_IMAGE = By.xpath("//img[@src='assets/images/blue.webp']");
	    public static final By VALUES_SECTION_IMAGE = By.xpath("//img[@alt='Values']");
	    public static final By FOOTER_LOGO = By.xpath("//img[@alt='logo']"); // Adjust alt if needed
	    public static final By FOOTER_TEXT = By.xpath("//p[@id='footxt']");
	    public static final By FOOTER_LINK_ABOUT = By.xpath("//footer//a[text()='About']");
	    public static final By FOOTER_LINK_DOCTORS = By.xpath("//footer//a[text()='Doctors']");
	    public static final By FOOTER_LINK_CONSUMERS = By.xpath("//footer//a[text()='Consumers']");
	    public static final By FOOTER_LINK_PROVIDERS = By.xpath("//footer//a[text()='Providers']");
	    public static final By FOOTER_LINK_BLOGS = By.xpath("//footer//a[text()='Blogs']");
	    public static final By FOOTER_LINK_FAQS = By.xpath("//footer//a[text()='FAQs']");
	    public static final By FOOTER_LINK_PRIVACY_POLICY = By.xpath("//footer//a[text()='Privacy Policy']");
	    public static final By FOOTER_LINK_TERMS = By.xpath("//footer//a[text()='Terms & Conditions']");
	    public static final By COPYRIGHT_NOTICE = By.xpath("//*[contains(text(),'© 2025, WONDRx')]");
	    public static final By FOLLOW_US_TEXT = By.xpath("//*[contains(text(),'Follow us')]");
	    public static final By ICON_LINKEDIN = By.xpath("//footer//a[contains(@href, 'linkedin.com')]");
	    public static final By ICON_FACEBOOK = By.xpath("//footer//a[contains(@href, 'facebook.com')]");
	    public static final By ICON_YOUTUBE = By.xpath("//footer//a[contains(@href, 'youtube.com')]");
	    public static final By ICON_INSTAGRAM = By.xpath("//footer//a[contains(@href, 'instagram.com')]");
	    public static final By ALL_SOCIAL_ICONS = By.xpath("//footer//div[contains(.,'Follow us')]//a[contains(@href, 'http')]");

	}
    
