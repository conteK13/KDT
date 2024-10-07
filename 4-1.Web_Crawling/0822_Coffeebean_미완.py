from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.chrome.service import Service
from selenium.webdriver.chrome.options import Options
from webdriver_manager.chrome import ChromeDriverManager
from bs4 import BeautifulSoup as bs
import time


serv = Service(executable_path=ChromeDriverManager().install())
driver = webdriver.Chrome(service=serv)


# 대상 URL 설정
url = "https://www.coffeebeankorea.com/store/store.asp"
print(driver.current_window_handle)
driver.get(url)
time.sleep(5) 


for i in range(1, 200):  #매장 개수는 임의로 지정하였음
    try:
        driver.execute_script('storePop2(%d)' %i) #자바스크립트 불러오기 #storePop2 자바스크립트 이름
        
        print(driver.current_window_handle)
        #html = driver.page_source
        # soup = bs(html, 'lxml') 
        # info = driver.find_element(By.CSS_SELECTOR, "#matizCoverLayer0Content > div > div > div.store_txt").text
        # print(info)
        test = 0
        test = driver.find_element(By.CSS_SELECTOR, "#matizCoverLayer0Content > div > div > div.store_txt > table > tbody:nth-child(1) > tr:nth-child(2) > td").text
        print(test) 
        # time.sleep(5)
    except:
        print(f'{i} : not exist') #자바스크립가 없을 경우 출력
        #time.sleep(1)
        continue