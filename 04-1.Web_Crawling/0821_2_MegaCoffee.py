from bs4 import BeautifulSoup as bs
import pandas as pd
from selenium import webdriver
from webdriver_manager.chrome import ChromeDriverManager
from selenium.webdriver.common.by import By
from selenium.webdriver.chrome.service import Service
import time
#필요한 모듈 import
from selenium.webdriver.common.action_chains import ActionChains as ac #actions 모듈 


#지연시간을 전체적으로 수정할수 있게 변수를 사용함.
t = 3

#chrome으로 동적 크롤링할 준비.
service = Service(executable_path=ChromeDriverManager().install())
driver = webdriver.Chrome(service=service)

#url에서 휴먼 error가 많이 발생하므로 주의.
driver.get("https://www.mega-mgccoffee.com/store/find/")

#모든 정보를 담을 변수. data라는 변수를 사용할 예정이므로 변수명을 사용할때 주의한다.
table = []
count = 1

#빠르게 접근하기 위해 첫번째 TC 설정
search = driver.find_element(By.CSS_SELECTOR, "div.cont_text_wrap.map_search_tab_wrap ul > li:nth-child(2)").click()
time.sleep(t)
search = driver.find_element(By.CSS_SELECTOR, "#store_area_search_list > li:nth-child(1)").click()
time.sleep(t)
search = driver.find_element(By.CSS_SELECTOR, f"#store_area_search_list_result > li:nth-child(2)").click()
time.sleep(t)

#map에 생성된 표시들가져오기
지점개수 = len(driver.find_elements(By.CSS_SELECTOR, "#map > div:nth-child(1) > div > div:nth-child(6) > div"))
stores = driver.find_elements(By.CSS_SELECTOR, "#map > div:nth-child(1) > div > div:nth-child(6) > div")
for store in range(3, 지점개수):
    actions = ac(driver)
    actions.move_to_element(stores[store]).click().perform()
    time.sleep(t)
    e = driver.find_element(By.CSS_SELECTOR, "#map > div:nth-child(1) > div > div:nth-child(6) > div:nth-child(21) > img")
    actions.move_to_element(e).click().perform()
    time.sleep(t)

