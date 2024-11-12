from bs4 import BeautifulSoup as bs
import pandas as pd
from selenium import webdriver
from webdriver_manager.chrome import ChromeDriverManager
from selenium.webdriver.common.by import By
from selenium.webdriver.chrome.service import Service
import time


t = 5
service = Service(executable_path=ChromeDriverManager().install())
driver = webdriver.Chrome(service=service)
#driver.get("https://www.starbucks.co.kr/store/store_map.do?disp=locale")
driver.get("https://www.starbucks.co.kr/store/store_map.do")
data = []


count =1
serch = driver.find_element(By.LINK_TEXT, "지역 검색").click()
time.sleep(t)
local = driver.find_element(By.CSS_SELECTOR, "#container div.loca_step1_cont > ul > li:nth-child(1) > a").text
serch = driver.find_element(By.CSS_SELECTOR, "#container div.loca_step1_cont > ul > li:nth-child(1)").click()
time.sleep(t)
serch = driver.find_element(By.LINK_TEXT, "전체").click()
time.sleep(t)
html = driver.page_source
soup = bs(html, 'lxml')
sto = soup.select(".quickResultLstCon")

# latitude= soup.select("#mCSB_3_container > ul > li")[1]['data-lat']
# print(latitude)
datas= soup.select("#mCSB_3_container > ul > li")
for data in datas:
    latitude = data['data-lat']
    longitude = data['data-long']
    store = data.select_one("strong").text.strip()
    j = data.select_one("p").text
    add = j[:-9]
    num = j[-9:]

    print(local, count, store, add,num, latitude,longitude,sep=" / ")
    count+=1
