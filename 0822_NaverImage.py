from selenium import webdriver                              #크롬 창 역할
from webdriver_manager.chrome import ChromeDriverManager    #크롬 드라이버 자동 업데이트
from selenium.webdriver.chrome.service import Service       #크롬 드라이버를 최신 버전으로 설치
from selenium.webdriver.common.by import By                 #요소 선택자를 구분해주는 라이브러리
from selenium.webdriver.common.keys import Keys 

import time                                                 #사이트 전환이 될 수 있도록 delay 주기

import os
from urllib.request import urlretrieve

t = 2

# driver 설정하기 전에 검색어를 먼저 입력받아 놓기
# 이 문구가 driver 설정 후에 들어가면, 검색어 입력 대기 중에 크롬 창이 열린 채로 대기함
search = input("네이버에서 이미지를 검색할 검색어를 입력하세요 ▶ ")


#chromedrivermanager를 이용하여 크롬드라이브 설치.
serv = Service(executable_path=ChromeDriverManager().install())
#service를 이용하여 최신버전의 크롬브라우저를 만든 후 driver 변수에 할당.
driver = webdriver.Chrome(service =serv)

driver.get(f"https://search.naver.com/search.naver?ssc=tab.image.all&where=image&sm=tab_jum&query={search}")
time.sleep(t)

#페이지를 내리지 않으면 이미지가 정상적으로 로딩되지 않아서 출력이 이상해 진다.
#END로 한번에 내리면, 경우에 따라 가운데 이미지가 출력이 안되는 경우가 발생한다.
body = driver.find_element(By.TAG_NAME, 'body')
for i in range(5): #최소 5번은 내려야 안정적으로 50개가 출력됨
    body.send_keys(Keys.PAGE_DOWN)
    time.sleep(t)

#이미지 가져오기
imgs = driver.find_elements(By.CSS_SELECTOR, "div.image_tile._fe_image_tab_grid img")

#이미지를 저장할 폴더(디렉토리) 생성.
if not os.path.isdir("c:/pic"):
    os.mkdir("c:/pic")

for i in range(50):
    urlretrieve(imgs[i].get_attribute("src"), f"c:/pic/{search}{i+1:02}.jpg")
    #urlretrieve("이미지주소","저장할 경로 및 파일명") 
    #01, 02 이런식으로 출력하기위해 f스트링 사용

print("데이터가 파일로 저장되었습니다.")
#input("화면을 종료하려면 입력하세요.")

