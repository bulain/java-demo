package com.bulain.common.test;

import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class JQueryWebDriver {
    private WebDriver webDriver;

    private JQueryWebDriver(WebDriver webDriver) {
        this.webDriver = webDriver;
        injectJquery();
    }

    public static JQueryWebDriver wrap(WebDriver webDriver) {
        return new JQueryWebDriver(webDriver);
    }

    @SuppressWarnings("unchecked")
    public WebElement find(String selector) {
        String jqJS = "return jQuery('" + selector + "');";
        List<WebElement> elements = (List<WebElement>) getExecutor().executeScript(jqJS);
        return elements.size() > 0 ? elements.get(0) : null;
    }

    @SuppressWarnings("unchecked")
    public List<WebElement> finds(String selector) {
        String jqJS = "return jQuery('" + selector + "');";
        List<WebElement> elements = (List<WebElement>) getExecutor().executeScript(jqJS);
        return elements;
    }

    private JavascriptExecutor getExecutor() {
        return (JavascriptExecutor) webDriver;
    }

    public void executeJs(String script) {
        getExecutor().executeScript(script);
    }

    private void injectJquery() {
        if (!isInjected()) {
            String script = JQueryUtil.getJqueryContent();
            executeJs(script);
        }
    }

    private boolean isInjected() {
        boolean injected = true;
        String checkJS = "return typeof(jQuery);";
        Object results = getExecutor().executeScript(checkJS);
        if ("undefined".equalsIgnoreCase(results.toString())) {
            injected = false;
        }
        return injected;
    }

}
