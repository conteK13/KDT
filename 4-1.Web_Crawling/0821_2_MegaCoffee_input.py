from bs4 import BeautifulSoup as bs
import pandas as pd
from selenium import webdriver
from webdriver_manager.chrome import ChromeDriverManager
from selenium.webdriver.common.by import By
from selenium.webdriver.chrome.service import Service
from selenium.webdriver.common.keys import Keys 
import time
#필요한 모듈 import


#지연시간을 전체적으로 수정할수 있게 변수를 사용함.
t = 2

#chrome으로 동적 크롤링할 준비.
service = Service(executable_path=ChromeDriverManager().install())
driver = webdriver.Chrome(service=service)

#url에서 휴먼 error가 많이 발생하므로 주의.
driver.get("https://www.mega-mgccoffee.com/store/find/")

#모든 정보를 담을 변수. data라는 변수를 사용할 예정이므로 변수명을 사용할때 주의한다.
table = []
count = 1

# 지역검색에서 지역명 가져오기.
## 지역검색 선택
search = driver.find_element(By.CSS_SELECTOR, "div.cont_text_wrap.map_search_tab_wrap ul > li:nth-child(2)").click()
time.sleep(t)

## 지역검색에 있는 지역 목록들의 개수를 가져오기.
cities_len = len(driver.find_elements(By.CSS_SELECTOR, "#store_area_search_list > li"))
cities = []

## cites라는 리스트에 지역이름을 삽입하기.
for i in range(1, cities_len+1):
    cities.append(driver.find_element(By.CSS_SELECTOR, f"#store_area_search_list > li:nth-child({i})").text.strip())
# print(cities)


# #지역검색에서 지역명 및 구 까지 가져오기 가져오기
# search = driver.find_element(By.CSS_SELECTOR, "div.cont_text_wrap.map_search_tab_wrap ul > li:nth-child(2)").click()
# time.sleep(t)

# cities_len = len(driver.find_elements(By.CSS_SELECTOR, "#store_area_search_list > li"))

# cities = []

# for i in range(1, cities_len+1):
#     cities.append(driver.find_element(By.CSS_SELECTOR, f"#store_area_search_list > li:nth-child({i})").text.strip())
#     search = driver.find_element(By.CSS_SELECTOR, f"#store_area_search_list > li:nth-child({i})").click()
#     time.sleep(t)
#     locals_len = len(driver.find_elements(By.CSS_SELECTOR, "#store_area_search_list_result > li"))
#     for j in range(2, locals_len+1):
#         #i가 1부터 시작했기 때문에, -1 보정한다.
#         locals[i-1].append(driver.find_element(By.CSS_SELECTOR, f"#store_area_search_list_result > li:nth-child({j})").text.strip())
#     serch = driver.find_element(By.ID, "store_area_search_back").click() #처음 지역 선택으로 돌아가기
#     time.sleep(t)
# #시, 도별 이름과 그 안에 속한 지역별 이름을 적는다.
# print(cities)
# print(locals)

count =1
for i in range(cities_len):
    # 검색 창에 locals의 값 입력하기
    search = driver.find_element(By.CSS_SELECTOR, "div.cont_text_wrap.map_search_tab_wrap li:nth-child(1)").click()
    time.sleep(t)
    search = driver.find_element(By.CSS_SELECTOR, "#store_search")
    
    #기존 내용을 삭제하기 위해 Control + a / Del
    search.send_keys(Keys.CONTROL + "a")
    search.send_keys(Keys.DELETE)
    search.send_keys(cities[i])
    search.send_keys(Keys.ENTER)
    time.sleep(t)
    
    html = driver.page_source
    soup = bs(html, 'lxml')
    # 한번에 처리하기 위해 li단위로 가져온다.
    #store_search_list a > div

    datas = soup.select("#store_search_list a > div")
    try:
        for data in datas:
            store = data.select_one("div:nth-child(1) > b").text.strip()
            add = data.select_one("div:nth-child(2)").text.strip().replace("\t","/").split("/")[0]
            tel = data.select_one("div:nth-child(2)").text.strip().replace("\t","/").split("/")[-1]
            
            #전화번호가 없을 때, 전화번호란에 주소가 들어가는 문제를 처리하기 위함
            if add == tel:
                tel = "전화번호 누락"
            local = add.split(" ")[0]
            print(count, local, store, add,tel)
            count +=1
            table.append({'지역' : local,
                        '매장명': store,
                        '주소': add,
                        '전화번호' : tel
                        })
    except:
        #딕셔너리로 중복 제거
        pass
        
Megacoffee = pd.DataFrame(table)
Megacoffee.set_index("매장명", inplace=True)

Megacoffee.to_csv("Megacoffee_utf8.csv",encoding="utf8")
Megacoffee.to_csv("Megacoffee_utf-8-sig.csv",encoding="utf-8-sig")
Megacoffee.to_csv("Megacoffee_euc-kr2.csv",encoding="EUC-KR")

print("데이터가 파일로 저장되었습니다.")



# serch = driver.find_element(By.CSS_SELECTOR, f"#store_area_search_list_result > li:nth-child(2)").click()
# time.sleep(t)
# 지점개수 = len(driver.find_elements(By.CSS_SELECTOR, "map > div:nth-child(1) > div > div:nth-child(6) > div"))
# serch = driver.find_element(By.CSS_SELECTOR, "#map div:nth-child(6) > div:nth-child(1) > img").click()
# time.sleep(t+2)

#지역검색 자동화
# serch = driver.find_element(By.CSS_SELECTOR, "div.cont_text_wrap.map_search_tab_wrap ul > li:nth-child(2)").click()
# time.sleep(t)
# 도시개수 = len(driver.find_elements(By.CSS_SELECTOR, "#store_area_search_list > li"))
# for i in range(1, 도시개수+1):
#     print(driver.find_element(By.CSS_SELECTOR, f"#store_area_search_list > li:nth-child({i})").text)
#     serch = driver.find_element(By.CSS_SELECTOR, f"#store_area_search_list > li:nth-child({i})").click()
#     time.sleep(t)
#     세부도시수 = len(driver.find_elements(By.CSS_SELECTOR, "#store_area_search_list_result > li"))
#     for j in range(2, 세부도시수+1):
#         print(driver.find_element(By.CSS_SELECTOR, f"#store_area_search_list_result > li:nth-child({j})").text)
#     serch = driver.find_element(By.ID, "store_area_search_back").click()
#     time.sleep(t)


#map > div:nth-child(1) > div > div:nth-child(6) >div

    



# 
#     print("=================================")
#     print(도시.text, " :")
#     time.sleep(t)
#     serch = 도시.click()
#     구분들 = driver.find_elements(By.CSS_SELECTOR, "#store_area_search_list_result > li")
#     time.sleep(t)
#     for 구분 in 구분들:
#         print(구분.text)

# local = driver.find_element(By.CSS_SELECTOR, f"#store_area_search_list > li({local})").text
# serch = driver.find_element(By.CSS_SELECTOR, f"#store_area_search_list > li({local})").click()
