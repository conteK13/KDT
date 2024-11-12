import pandas as pd
from bs4 import BeautifulSoup as bs
from selenium import webdriver
from webdriver_manager.chrome import ChromeDriverManager
from selenium.webdriver.common.by import By
from selenium.webdriver.chrome.service import Service
import time
# 필요한 모듈 import


# 지연시간을 한번에 수정할 수 있게 변수를 사용함.
t = 2

# chrome으로 동적 크롤링할 준비.
service = Service(executable_path=ChromeDriverManager().install())
driver = webdriver.Chrome(service=service)

# url에서 휴먼 error가 많이 발생하므로 주의.
driver.get("https://www.baristapaulbassett.co.kr/store/Store.pb")

# 모든 정보를 담을 변수. data라는 변수를 main문에서 사용할 예정이므로 변수명을 사용할때 주의한다.
table = []

# 지역의 총 개수를 구한다.
locals = len(driver.find_elements(By.CSS_SELECTOR, "#areaList > li"))
for i in range(1, locals+1):
    count = 1
    # 지역선택하기.
    # 다른 지역으로 가기 위해서 매번 초기화 한다.
    search = driver.find_element(By.LINK_TEXT, "지역").click()
    time.sleep(t)
    
    #f스트링을 이용하여 지역을 이동한다.
    local = driver.find_element(By.CSS_SELECTOR, f"#areaList > li:nth-child({i})").text
    search = driver.find_element(By.CSS_SELECTOR, f"#areaList > li:nth-child({i})").click()
    time.sleep(t)
    
    #지역내 전체 매장을 확인한다.
    search = driver.find_element(By.LINK_TEXT, "전체").click()
    time.sleep(t)


    html = driver.page_source
    soup = bs(html, 'lxml')
    # 한번에 처리하기 위해 li단위로 가져온다.
    datas = soup.select("#shopList li")

    try:
        for data in datas:
            store = data.select_one("a > strong").text.strip()
            add = data.select_one("address").text
            tel = data.select_one("dl > dd").text
            print(local, count, store, add, tel)
            count += 1
            table.append({'지역' : local,
                          '매장명': store,
                          '주소': add,
                          '전화번호': tel
                        })
    except:
        #매장이 없는 경우 오류 발생.
        if len(datas) <= 1:
            print(f"{local}에는 폴바셋 매장이 없습니다.")
            #데이터는 추가하지 않는다.
    
    
Paulbassett = pd.DataFrame(table)
Paulbassett.set_index("매장명", inplace=True)

Paulbassett.to_csv("Paulbassett_utf8.csv",encoding="utf8")
Paulbassett.to_csv("Paulbassett_utf-8-sig.csv",encoding="utf-8-sig")
#Paulbassett.to_csv("Paulbassett_euc-kr2.csv",encoding="EUC-KR")

print("데이터가 파일로 저장되었습니다.")