package com.bulain.activiti.it;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import com.bulain.activiti.test.TestConst;
import com.bulain.common.dataset.DataSet;
import com.bulain.common.dataset.SeedDataSet;
import com.bulain.common.test.WebTestCase;

@SeedDataSet(file = TestConst.IT_DATA_INIT_COMMON_XML)
@DataSet(file = "it-data/init_users.xml")
public class UserIT extends WebTestCase {

    @Test
    public void testList() {
        List<WebElement> listTr;

        driver.get(baseUrl + "/user/list.action");
        driver.findElement(By.id("search_search_firstName")).clear();
        driver.findElement(By.id("search_search_firstName")).sendKeys("first_name_page");
        driver.findElement(By.id("search_search_lastName")).clear();
        driver.findElement(By.id("search_search_lastName")).sendKeys("last_name_page");
        driver.findElement(By.id("btn_search")).click();
        listTr = driver.findElements(By.xpath("id('list')/tbody/tr"));
        assertEquals(4, listTr.size());
        assertTrue(isElementPresent(By.id("btn_search")));

        driver.get(baseUrl + "/user/list.action");
        driver.findElement(By.id("search_search_firstName")).clear();
        driver.findElement(By.id("search_search_lastName")).clear();
        driver.findElement(By.id("btn_search")).click();
        listTr = driver.findElements(By.xpath("id('list')/tbody/tr"));
        assertEquals(8, listTr.size());
        assertTrue(isElementPresent(By.id("btn_search")));
    }

    @Test
    public void testCreate() {
        driver.get(baseUrl + "/user/list.action");
        driver.findElement(By.id("lnk_new")).click();
        driver.findElement(By.id("create_user_firstName")).clear();
        driver.findElement(By.id("create_user_firstName")).sendKeys("user01");
        driver.findElement(By.id("create_user_lastName")).clear();
        driver.findElement(By.id("create_user_lastName")).sendKeys("test");
        driver.findElement(By.id("btn_create")).click();
        List<WebElement> listTr = driver.findElements(By.xpath("id('list')/tbody/tr"));
        assertEquals(9, listTr.size());
        assertTrue(isElementPresent(By.id("btn_search")));
    }

    @Test
    public void testUpdate() {
        driver.get(baseUrl + "/user/list.action");
        String xpath = String.format("//tr[td/text()='%s']/td/a[contains(@href,'edit.action')]", "first_name_101");
        driver.findElement(By.xpath(xpath)).click();
        driver.findElement(By.id("update_user_firstName")).clear();
        driver.findElement(By.id("update_user_firstName")).sendKeys("first_name-updated");
        driver.findElement(By.id("update_user_lastName")).clear();
        driver.findElement(By.id("update_user_lastName")).sendKeys("last_name-updated");
        driver.findElement(By.id("btn_update")).click();
        assertTrue(isElementPresent(By.id("btn_search")));
    }

    @Test
    public void testShow() {
        driver.get(baseUrl + "/user/list.action");
        String xpath = String.format("//tr[td/text()='%s']/td/a[contains(@href,'show.action')]", "first_name_101");
        driver.findElement(By.xpath(xpath)).click();
        String firstName = driver.findElement(By.id("show_user_firstName")).getText();
        String lastName = driver.findElement(By.id("show_user_lastName")).getText();
        assertEquals("first_name_101", firstName);
        assertEquals("last_name_101", lastName);
    }

    @Test
    public void testDelete() {
        driver.get(baseUrl + "/user/list.action");
        String xpath = String.format("//tr[td/text()='%s']/td/a[contains(@href,'destroy.action')]", "first_name_101");
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
