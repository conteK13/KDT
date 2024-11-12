from selenium import webdriver                              #크롬 창 역할
from webdriver_manager.chrome import ChromeDriverManager    #크롬 드라이버 자동 업데이트
from selenium.webdriver.chrome.service import Service       #크롬 드라이버를 최신 버전으로 설치
from selenium.webdriver.chrome.options import Options
from selenium.webdriver.common.by import By                 #요소 선택자를 구분해주는 라이브러리
import pandas as pd                                         #행렬구조의 2차원 테이블 형태로 데이어를 저장, 분석, 시각화를 지원하는 모듈
from bs4 import BeautifulSoup as bs                         #html 코드 해석
import time #사이트 전환이 될 수 있도록 delay 주기

t = 2
#chromedrivermanager를 이용하여 크롬드라이브 설치
serv = Service(executable_path=ChromeDriverManager().install())
#service를 이용하여 최신버전의 크롬브라우저를 만든 후 driver 변수에 할당
driver = webdriver.Chrome(service =serv)

driver.get("https://www.hsd.co.kr/menu/menu_list#none")
time.sleep(t)
try:
    for i in range(5):
        driver.find_element(By.CSS_SELECTOR, "a.c_05").click()
        time.sleep(t)
except:
    print('더보기 완료')

#메뉴명, 가격
titles = driver.find_elements(By.CSS_SELECTOR, "div.item-text > h4")
prices = driver.find_elements(By.CSS_SELECTOR, "div.item-text > div > strong")

title_list =[]
price_list =[]
print("메뉴\t\t\t\t\t가격")
for i in range(len(titles)):
    title_list.append(titles[i].text.replace("[8월 할인메뉴]","").strip())
    price_list.append(prices[i].text.strip())
    print(f'{title_list[i]:30} {price_list[i]:>10}')

dic = {'메뉴명' : title_list, '가격' : price_list}
hansot = pd.DataFrame(dic)
hansot.set_index("메뉴명", inplace=True)
hansot.to_csv("hansot_final_utf8.csv",encoding="utf8")
hansot.to_csv("hansot_final_utf-8-sig.csv",encoding="utf-8-sig")
hansot.to_csv("hansot_final_euc-kr2.csv",encoding="EUC-KR")
#UnicodeEncodeError: 'euc_kr' codec can't encode character '\xcd' in position 25: illegal multibyte sequence

print("데이터가 파일로 저장되었습니다.")
#input("화면을 종료하려면 입력하세요.")
