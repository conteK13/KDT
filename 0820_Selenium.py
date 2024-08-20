from selenium import webdriver                              #크롬 창 역할
from webdriver_manager.chrome import ChromeDriverManager    #크롬 드라이버 자동 업데이트

#==============필수=================
from selenium.webdriver.common.keys import Keys             #컴퓨터용 키보드 라이블러리
from selenium.webdriver.common.by import By                 #요소 선택자를 구분해주는 라이브러리
from selenium.webdriver.chrome.service import Service       #크롬 드라이버를 최신 버전으로 설치
from selenium.webdriver.chrome.options import Options       
import time

#크롬 드라이버 설치
service = Service(executable_path=ChromeDriverManager().install())
driver = webdriver.Chrome(service=service)  #크롬창을 만듦
#driver.get("https://www.melon.com/chart/hot100/index.htm")
driver.get("https://www.naver.com")


search = driver.find_element(By.XPATH, "//*[@id='query']")

search.send_keys("손흥민")
search.send_keys(Keys.ENTER)
time.sleep(2)

body = driver.find_element(By.TAG_NAME, "body")
body.send_keys(Keys.END)
time.sleep(5)
"""
#네이버 검색창
search = driver.find_element(By.ID, "query")
search = driver.find_element(By.CLASS_NAME, "search_input")
search = driver.find_element(By.NAME, "query")
search = driver.find_element(By.CSS_SELECTOR, ".search_input")#class
search = driver.find_element(By.CSS_SELECTOR, "#query") #ID
search = driver.find_element(By.CSS_SELECTOR, ".search_input_box > input") #ID
search = driver.find_element(By.XPATH, "//*[@id='query']")

driver.find_element(By.PARTIAL_LINK_TEXT, "치지").click()
driver.find_element(By.LINK_TEXT, "치지직").click()

body = driver.find_element(By.TAG_NAME, "body")


driver.find_element(By.ID, "")
driver.find_element(By.CSS_SELECTOR, "")
driver.find_element(By.NAME, "")
driver.find_element(By.TAG_NAME, "")
driver.find_element(By.XPATH, "")
driver.find_element(By.LINK_TEXT, "")
driver.find_element(By.PARTIAL_LINK_TEXT, "")
"""

"""
<input id="query" name="query" type="search"
title="검색어를 입력해 주세요."
placeholder="검색어를 입력해 주세요." 
maxlength="255" autocomplete="off" 
class="search_input" 
data-atcmp-element="">

"""
"""
<a href="https://mail.naver.com" class="link_service" target="_blank">
<span class="service_icon type_mail"></span>
<span class="service_name">메일</span></a>
"""
