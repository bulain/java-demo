package com.bulain.crawler;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class Town extends Base {
    public static void main(String[] args) {
        webDriver = CHROME_DRIVER;

        setUp();
        try {
            Town town = new Town();
            //town.runCity(Arrays.asList(provs));
            town.printDistrict();
            //town.printSpecilTown();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            tearDown();
        }

    }

    private static String[] provs = new String[]{"110000","120000","130000","140000","150000","210000","220000","230000","310000","320000","330000","340000","350000","360000","370000","410000","420000","430000","440000","450000","460000","500000","510000","520000","530000","540000","610000","620000","630000","640000","650000"};
    
    //private String provFormat = "http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2013/index.html";
    private String cityFormat = "http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2013/%s.html";
    private String dsrtFormat = "http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2013/%s/%s.html";
    private String townFormat = "http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2013/%s/%s/%s.html";

    private void runCity(List<String> provs) throws Exception {

        FileWriter writer = new FileWriter("target/city.txt");
        List<String> cities = new ArrayList<String>();

        for (String code : provs) {
            String p = code.substring(0, 2);
            
            String url = String.format(cityFormat, p);
            driver.get(url);

            List<WebElement> list = driver.findElements(By.cssSelector("tr.citytr"));
            for (WebElement we : list) {
                String text = we.getText();
                cities.add(text);
                
                writer.write(text + "\n");
                System.out.println(text);
            }
            writer.flush();
        }
        writer.close();

        runDistrict(cities);
    }
    
    private void printCity() throws Exception{
        FileReader fileReader = new FileReader("target/city.txt");
        BufferedReader reader = new BufferedReader(fileReader);
        List<String> districts = new ArrayList<String>();
        
        String line = null;
        while((line = reader.readLine()) != null ){
            districts.add(line);
        }
        reader.close();
        
        runTown(districts);
    }
    
    private void runDistrict(List<String> cities) throws Exception{

        FileWriter writer = new FileWriter("target/district.txt");
        List<String> districts = new ArrayList<String>();

        for (String code : cities) {
            String p = code.substring(0, 2);
            String c = code.substring(0, 4);

            String url = String.format(dsrtFormat, p, c);
            driver.get(url);

            List<WebElement> list = driver.findElements(By.cssSelector("tr.countytr"));
            for (WebElement we : list) {
                String text = we.getText();
                districts.add(text);
                
                writer.write(text + "\n");
                System.out.println(text);
            }
            writer.flush();
        }

        writer.close();
        
        
        runTown(districts);
    
    }
    
    private void printDistrict() throws Exception{
        FileReader fileReader = new FileReader("target/district.txt");
        BufferedReader reader = new BufferedReader(fileReader);
        List<String> districts = new ArrayList<String>();
        
        String line = null;
        while((line = reader.readLine()) != null ){
            districts.add(line);
        }
        reader.close();
        
        runTown(districts);
        
    }
    
    private void printSpecilTown() throws Exception{
        List<String> districts = Arrays.asList(new String[]{"441900000000", "442000000000"});
        

        FileWriter writer = new FileWriter("target/sptown.txt");

        for (String district : districts) {
            String p = district.substring(0, 2);
            String c = district.substring(0, 4);

            String url = String.format(dsrtFormat, p, c);
            driver.get(url);
            
            if (driver.getTitle().equals("404 Not Found")) {
                continue;
            }

            List<WebElement> list = driver.findElements(By.cssSelector("tr.towntr"));
            for (WebElement we : list) {
                String text = we.getText();
                
                writer.write(text + "\n");
                System.out.println(text);
            }
            writer.flush();
        }

        writer.close();
    
    }
    
    private void runTown(List<String> districts) throws Exception {
        FileWriter writer = new FileWriter("target/town.txt");

        for (String district : districts) {
            String p = district.substring(0, 2);
            String c = district.substring(2, 4);
            String disc = district.substring(0, 6);

            String url = String.format(townFormat, p, c, disc);
            driver.get(url);
            
            if (driver.getTitle().equals("404 Not Found")) {
                continue;
            }

            List<WebElement> list = driver.findElements(By.cssSelector("tr.towntr"));
            for (WebElement we : list) {
                String text = we.getText();
                
                writer.write(text + "\n");
                System.out.println(text);
            }
            writer.flush();
        }

        writer.close();
    }
}
