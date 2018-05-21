package com.bulain.activiti.it;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.support.ui.Select;

import com.bulain.activiti.test.TestConst;
import com.bulain.common.dataset.SeedDataSet;
import com.bulain.common.test.WebTestCase;

@SeedDataSet(file = TestConst.IT_DATA_INIT_COMMON_XML)
public class ReferanceIT extends WebTestCase {

    @Test
    public void testList() {
        List<WebElement> listTr;

        driver.get(baseUrl + "/referance/list.action");
        new Select(driver.findElement(By.id("search_search_name"))).selectByValue("");
        driver.findElement(By.id("search_search_code")).clear();
        new Select(driver.findElement(By.id("search_search_lang"))).selectByValue("");
        new Select(driver.findElement(By.id("search_search_category"))).selectByValue("");
        driver.findElement(By.id("btn_search")).click();
        listTr = driver.findElements(By.xpath("id('list')/tbody/tr"));
        assertEquals(11, listTr.size());
        assertTrue(isElementPresent(By.id("btn_search")));

        driver.get(baseUrl + "/referance/list.action");
        new Select(driver.findElement(By.id("search_search_name"))).selectByValue("AddressType");
        driver.findElement(By.id("search_search_code")).clear();
        new Select(driver.findElement(By.id("search_search_lang"))).selectByValue("en");
        new Select(driver.findElement(By.id("search_search_category"))).selectByValue("");
        driver.findElement(By.id("btn_search")).click();
        listTr = driver.findElements(By.xpath("id('list')/tbody/tr"));
        assertEquals(7, listTr.size());
        assertTrue(isElementPresent(By.id("btn_search")));
    }

    @Test
    public void testCreate() {
        driver.get(baseUrl + "/referance/list.action");
        driver.findElement(By.id("lnk_new")).click();
        new Select(driver.findElement(By.id("create_referanceBean_name"))).selectByValue("AddressType");
        driver.findElement(By.id("create_referanceBean_code")).clear();
        driver.findElement(By.id("create_referanceBean_code")).sendKeys("XXX");
        new Select(driver.findElement(By.id("create_referanceBean_category"))).selectByValue("");
        driver.findElement(By.id("create_referanceBean_textEN")).clear();
        driver.findElement(By.id("create_referanceBean_textEN")).sendKeys("TEST EN");
        driver.findElement(By.id("create_referanceBean_textCN")).clear();
        driver.findElement(By.id("create_referanceBean_textCN")).sendKeys("TEST CN");
        driver.findElement(By.id("btn_create")).click();
        List<WebElement> listTr = driver.findElements(By.xpath("id('list')/tbody/tr"));
        assertEquals(8, listTr.size());
        assertTrue(isElementPresent(By.id("btn_search")));
    }

    @Test
    public void testUpdate() {
        driver.get(baseUrl + "/referance/list.action");
        String xpath = String.format("//tr[td/text()='%s']/td/a[contains(@href,'edit.action')]", "ADDR");
        driver.findElement(By.xpath(xpath)).click();
        new Select(driver.findElement(By.id("update_referance_name"))).selectByValue("AddressType");
        driver.findElement(By.id("update_referance_code")).clear();
        driver.findElement(By.id("update_referance_code")).sendKeys("ADDR");
        new Select(driver.findElement(By.id("update_referance_category"))).selectByValue("");
        driver.findElement(By.id("update_referance_text")).clear();
        driver.findElement(By.id("update_referance_text")).sendKeys("Postal-updated");
        driver.findElement(By.id("btn_update")).click();
        assertTrue(isElementPresent(By.id("btn_search")));
    }

    @Test
    public void testShow() {
        driver.get(baseUrl + "/referance/list.action?request_locale=en_US");
        String xpath = String.format("//tr[td/text()='%s']/td/a[contains(@href,'show.action')]", "HOME");
        driver.findElement(By.xpath(xpath)).click();
        String name = driver.findElement(By.id("show_referance_name")).getText();
        String code = driver.findElement(By.id("show_referance_code")).getText();
        String lang = driver.findElement(By.id("show_referance_lang")).getText();
        String category = driver.findElement(By.id("show_referance_category")).getText();
        String text = driver.findElement(By.id("show_referance_text")).getText();
        assertEquals("AddressType", name);
        assertEquals("HOME", code);
        assertEquals("English", lang);
        assertEquals("", category);
        assertEquals("Residential", text);
    }

    @Test
    public void testDelete() {
        driver.get(baseUrl + "/referance/list.action");
        String xpath = String.format("//tr[td/text()='%s']/td/a[contains(@href,'destroy.action')]", "ADDR");
        driver.findElement(By.xpath(xpath)).click();
        if (!(driver instanceof HtmlUnitDriver)) {
            driver.switchTo().alert().accept();
            driver.switchTo().defaultContent();
        }
        List<WebElement> listTr = driver.findElements(By.xpath("id('list')/tbody/tr"));
        assertEquals(7, listTr.size());
        assertTrue(isElementPresent(By.id("btn_search")));
    }

}
