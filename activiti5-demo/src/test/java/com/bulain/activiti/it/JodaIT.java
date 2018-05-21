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
@DataSet(file = "it-data/init_jodas.xml")
public class JodaIT extends WebTestCase {

    @Test
    public void testList() {
        List<WebElement> listTr;

        driver.get(baseUrl + "/joda/list.action?request_locale=zh_CN");
        driver.findElement(By.id("search_search_xdate")).clear();
        driver.findElement(By.id("search_search_xdate")).sendKeys("2010-04-04");
        driver.findElement(By.id("search_search_xtime")).clear();
        driver.findElement(By.id("search_search_xtime")).sendKeys("10:00");
        driver.findElement(By.id("btn_search")).click();
        listTr = driver.findElements(By.xpath("id('list')/tbody/tr"));
        assertEquals(4, listTr.size());
        assertTrue(isElementPresent(By.id("btn_search")));

        driver.get(baseUrl + "/joda/list.action?request_locale=zh_CN");
        driver.findElement(By.id("search_search_xdate")).clear();
        driver.findElement(By.id("search_search_xdate")).sendKeys("");
        driver.findElement(By.id("search_search_xtime")).clear();
        driver.findElement(By.id("search_search_xtime")).sendKeys("");
        driver.findElement(By.id("btn_search")).click();
        listTr = driver.findElements(By.xpath("id('list')/tbody/tr"));
        assertEquals(8, listTr.size());
        assertTrue(isElementPresent(By.id("btn_search")));
    }

    @Test
    public void testCreate() {
        driver.get(baseUrl + "/joda/list.action?request_locale=zh_CN");
        driver.findElement(By.id("lnk_new")).click();
        driver.findElement(By.id("create_joda_xdate")).clear();
        driver.findElement(By.id("create_joda_xdate")).sendKeys("2010-04-04");
        driver.findElement(By.id("create_joda_xtime")).clear();
        driver.findElement(By.id("create_joda_xtime")).sendKeys("10:00");
        driver.findElement(By.id("btn_create")).click();
        List<WebElement> listTr = driver.findElements(By.xpath("id('list')/tbody/tr"));
        assertEquals(9, listTr.size());
        assertTrue(isElementPresent(By.id("btn_search")));
    }

    @Test
    public void testUpdate() {
        driver.get(baseUrl + "/joda/list.action?request_locale=zh_CN");
        String xpath = String.format("//tr[td/text()='%s']/td/a[contains(@href,'edit.action')]", "2010-04-01");
        driver.findElement(By.xpath(xpath)).click();
        driver.findElement(By.id("update_joda_xdate")).clear();
        driver.findElement(By.id("update_joda_xdate")).sendKeys("2010-04-04");
        driver.findElement(By.id("update_joda_xtime")).clear();
        driver.findElement(By.id("update_joda_xtime")).sendKeys("10:00");
        driver.findElement(By.id("btn_update")).click();
        assertTrue(isElementPresent(By.id("btn_search")));
    }

    @Test
    public void testShow() {
        driver.get(baseUrl + "/joda/list.action?request_locale=zh_CN");
        String xpath = String.format("//tr[td/text()='%s']/td/a[contains(@href,'show.action')]", "2010-04-01");
        driver.findElement(By.xpath(xpath)).click();
        String xdate = driver.findElement(By.id("show_joda_xdate")).getText();
        String xtime = driver.findElement(By.id("show_joda_xtime")).getText();
        assertEquals("2010-04-01", xdate);
        assertEquals("05:00", xtime);
    }

    @Test
    public void testDelete() {
        driver.get(baseUrl + "/joda/list.action?request_locale=zh_CN");
        String xpath = String.format("//tr[td/text()='%s']/td/a[contains(@href,'destroy.action')]", "2010-04-01");
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
