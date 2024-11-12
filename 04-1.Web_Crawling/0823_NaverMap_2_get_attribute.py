from bs4 import BeautifulSoup as bs
import pandas as pd
from selenium import webdriver
from webdriver_manager.chrome import ChromeDriverManager
from selenium.webdriver.common.by import By
from selenium.webdriver.chrome.service import Service
from selenium.webdriver.common.keys import Keys 
import time
#필요한 모듈 import

#전체 시간 조절용
t = 3

#검색어 입력받기
word = input("검색어를 입력하세요 ▶ ")

#chrome으로 동적 크롤링할 준비.
service = Service(executable_path=ChromeDriverManager().install())
driver = webdriver.Chrome(service=service)
driver.get("https://www.naver.com")
time.sleep(t)

#0820_Selenium 처음 배울때 사용한 파일에 명령어 있음.
driver.find_element(By.LINK_TEXT, "지도").click()
time.sleep(t)

#0822_커피빈 window 전환하는 코드 활용.
#driver.window_handles[1] 두번째로 열린 윈도우의 핸들 값.
driver.switch_to.window(driver.window_handles[1])
time.sleep(t)

#지도창의 입력란에 검색어 입력.
search = driver.find_element(By.CLASS_NAME, "input_search")
search.send_keys(word)
search.send_keys(Keys.ENTER)
#다른 화면보다 로딩시간이 길다.
time.sleep(10)

#iframe의 id 입력하여 현재창을 iframe으로 변경한다.
#iframe의 id 말고 다른 값을 넣는 방법은 블로그나 노션에 정리.
driver.switch_to.frame("searchIframe")
stores = driver.find_elements(By.CSS_SELECTOR, "span.place_bluelink")
count = 1
for store in stores:
    print(count, ":", store.text)
    count+=1

#data가 나오는 부분을 get_gttribue 명령올 html로 변경하여 확인
# infos = driver.find_element(By.CSS_SELECTOR, "#app-layout")
# datas = infos.get_attribute("innerHTML")    #list object는 get_attribute할 수 없으므로, element로 가져와야 함
# soup = bs(datas, 'lxml')


#data가 나오는 부분을 일일히 찾아가며 접근해봄
# stores = driver.find_elements(By.CSS_SELECTOR, "#app-layout > div:nth-child(2) > div:nth-child(2) > div:nth-child(1) > div:nth-child(1) > div > div:nth-child(2) > div > div:nth-child(3) > div:nth-child(2) > div")
# count = 1
# for store in stores:
#     store = store.text.strip()
#     if len(store) <= 1:
#         continue
#     print(count, store)
#     count+=1
# print("test1", stores.text)


