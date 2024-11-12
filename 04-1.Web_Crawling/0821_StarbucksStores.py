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
table = []


count =1
serch = driver.find_element(By.LINK_TEXT, "지역 검색").click()
time.sleep(t)
local = driver.find_element(By.CSS_SELECTOR, "#container div.loca_step1_cont > ul > li:nth-child(17) > a").text
serch = driver.find_element(By.CSS_SELECTOR, "#container div.loca_step1_cont > ul > li:nth-child(17)").click()
time.sleep(t)
# serch = driver.find_element(By.LINK_TEXT, "전체").click()
# time.sleep(t)
html = driver.page_source
soup = bs(html, 'lxml')
sto = soup.select(".quickResultLstCon")

datas= soup.select("#mCSB_3_container > ul > li")
for data in datas:
    store = data.select_one("strong").text.strip()
    add = data.select_one("p").text[:-9]
    num = data.select_one("p").text[-9:]
    latitude = data['data-lat']
    longitude = data['data-long']
    table.append({'지역' : local,
                  '매장명': store,
                  '주소': add,
                  '전화번호': num,
                  '위도' : latitude,
                  '경도' : longitude
                  })
    print(f"{local} {count} : {store}, {add}, {num} | 위도:{latitude}, 경도:{longitude}")
    count+=1

Starbucks = pd.DataFrame(table)
Starbucks.set_index("매장명", inplace=True)

Starbucks.to_csv("Starbucks_utf8.csv",encoding="utf8")
Starbucks.to_csv("Starbucks_utf-8-sig.csv",encoding="utf-8-sig")
#Melon.to_csv("MelonHot100_euc-kr2.csv",encoding="EUC-KR")
#UnicodeEncodeError: 'euc_kr' codec can't encode character '\xcd' in position 25: illegal multibyte sequence

print("데이터가 파일로 저장되었습니다.")
# #input("화면을 종료하려면 입력하세요.")

