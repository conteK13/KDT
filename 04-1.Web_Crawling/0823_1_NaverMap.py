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
word = "이수역 맛집" #input("검색어를 입력하세요 ▶ ")

#chrome으로 동적 크롤링할 준비.
service = Service(executable_path=ChromeDriverManager().install())
driver = webdriver.Chrome(service=service)
# driver.get("https://map.naver.com/p")
# time.sleep(t)



# 네이버에서 시작하는 경우
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
search.send_keys(word)       #파이썬에서 입력받은 단어를 검색창에 입력
search.send_keys(Keys.ENTER) #엔터키 입력
#다른 화면보다 로딩시간이 길다.
time.sleep(5)



#iframe의 id 입력하여 현재창을 iframe으로 변경한다.
driver.switch_to.frame("searchIframe")
time.sleep(10)

# body 스크롤 내리기
# body = driver.find_element(By.TAG_NAME, "body")
# body.click()
# time.sleep(2)
# for _ in range(10):
#     body.send_keys(Keys.PAGE_DOWN)
#     time.sleep(1)

data = []
stores = driver.find_elements(By.CSS_SELECTOR, "span.place_bluelink")
count = 1
for store in stores:
    store.click()
    time.sleep(10)
    driver.switch_to.default_content()
    time.sleep(t)
    driver.switch_to.frame("entryIframe")
    time.sleep(t)
    title = driver.find_element(By.CSS_SELECTOR, "#_title span.GHAhO").text.strip()
    add = driver.find_element(By.CSS_SELECTOR, "#app-root > div > div > div > div:nth-child(5) > div > div:nth-child(2) > div.place_section_content > div > div.O8qbU.tQY7D > div > a > span.LDgIH").text.strip()
    tel = driver.find_element(By.CSS_SELECTOR, "#app-root > div > div > div > div:nth-child(5) > div > div:nth-child(2) > div.place_section_content > div > div.O8qbU.nbXkr > div > span.xlx7Q").text.strip()
    driver.find_element(By.CSS_SELECTOR, "#app-root > div > div > div > div:nth-child(5) > div > div:nth-child(2) > div.place_section_content > div > div.O8qbU.pSavy > div > a").click()
    times = driver.find_elements(By.CSS_SELECTOR, "#app-root > div > div > div > div:nth-child(5) > div > div:nth-child(2) > div.place_section_content > div > div.O8qbU.pSavy > div > a > div")
    store_T= []
    for tt in times:
        store_T.append(tt.text)
    
    result_T = '\n'.join(store_T).replace("\n접기", "")
    data.append({'매장명': title,
                      '주소': add,
                      '전화번호': tel,
                      '영업시간' : result_T   
                    })
    print(title, add, tel, result_T, sep=" / ")
    driver.switch_to.default_content()
    time.sleep(t)
    driver.switch_to.frame("searchIframe")
    time.sleep(t)


NaverMap = pd.DataFrame(data)
NaverMap.set_index("매장명", inplace=True)

NaverMap.to_csv("NaverMap__utf8.csv",encoding="utf8")
NaverMap.to_csv("NaverMap_utf-8-sig.csv",encoding="utf-8-sig")
NaverMap.to_csv("NaverMap_euc-kr2.csv",encoding="EUC-KR")
#UnicodeEncodeError: 'euc_kr' codec can't encode character '\xcd' in position 25: illegal multibyte sequence

print("데이터가 파일로 저장되었습니다.")
# driver.switch_to.default_content()
# driver.close()