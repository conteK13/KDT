from bs4 import BeautifulSoup as bs
import pandas as pd
from selenium import webdriver
from webdriver_manager.chrome import ChromeDriverManager
from selenium.webdriver.common.by import By
from selenium.webdriver.chrome.service import Service
import time


#지연시간을 전체적으로 수정할수 있게 변수를 사용함.
t = 2

#chrome으로 동적 크롤링할 준비.
service = Service(executable_path=ChromeDriverManager().install())
driver = webdriver.Chrome(service=service)

#url에서 휴먼 error가 많이 발생하므로 주의.
driver.get("https://www.starbucks.co.kr/store/store_map.do")

#모든 정보를 담을 변수. data라는 변수를 사용할 예정이므로 변수명을 사용할때 주의한다.
table = []


#지역이 총 17개이므로 for문을 1부터 18까지 설정한다.
for i in range(1,18):
    #지역별 개수를 확인할 예정이므로 반복문 안에서 count를 초기화한다.
    count =1
    search = driver.find_element(By.LINK_TEXT, "지역 검색").click()
    time.sleep(t)
    
    #f스트링을 사용하여 css selector를 변경한다.
    local = driver.find_element(By.CSS_SELECTOR, f"div.loca_step1_cont li:nth-child({i}) > a").text
    search = driver.find_element(By.CSS_SELECTOR, f"div.loca_step1_cont li:nth-child({i})").click()
    time.sleep(t)
    
    #세종에는 '전체'가 없으므로 try except으로 예외처리한다.
    try:
        serch = driver.find_element(By.LINK_TEXT, "전체").click()
        time.sleep(t)
    except:
        pass
    
    html = driver.page_source
    soup = bs(html, 'lxml')

    #매장별 처리를 위해 li 단위로 크롤링한다. 
    datas= soup.select("#mCSB_3_container > ul > li")
    for data in datas:
        store = data.select_one("strong").text.strip()
        p = data.select_one("p").text
        add = p[:-9]
        num = p[-9:]
        latitude = data['data-lat']
        longitude = data['data-long']

        table.append({'매장명': store,
                      '주소': add,
                      '전화번호': num,
                      '위도' : latitude,
                      '경도' : longitude,
                      '지역' : local   
                    })
        
        print(f"{local} {count:3} : {store}, {add}, {num} | 위도:{latitude}, 경도:{longitude}")
        count+=1

Starbucks = pd.DataFrame(table)
Starbucks.set_index("매장명", inplace=True)

Starbucks.to_csv("Starbucks_final_utf8.csv",encoding="utf8")
Starbucks.to_csv("Starbucks_final_utf-8-sig.csv",encoding="utf-8-sig")
Starbucks.to_csv("Starbucks_final_euc-kr2.csv",encoding="EUC-KR")
#UnicodeEncodeError: 'euc_kr' codec can't encode character '\xcd' in position 25: illegal multibyte sequence

print("데이터가 파일로 저장되었습니다.")
#input("화면을 종료하려면 입력하세요.")

