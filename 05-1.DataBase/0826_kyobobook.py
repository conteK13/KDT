from bs4 import BeautifulSoup as bs
import pandas as pd
from selenium import webdriver
from webdriver_manager.chrome import ChromeDriverManager
from selenium.webdriver.common.by import By
from selenium.webdriver.chrome.service import Service
import time

data = []
count = 1
t = 3


# head = {"User-Agent":"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/127.0.0.0 Safari/537.36"}
# print(f"상태코드 : {res.status_code}")
# 정적 크롤링이 되지 않아 header 설정해봣지만 동작하지 않아 동적 크롤링으로 변경


#chrome으로 동적 크롤링할 준비.
service = Service(executable_path=ChromeDriverManager().install())
driver = webdriver.Chrome(service=service)


for page in range(1,11):
    driver.get(f"https://store.kyobobook.co.kr/bestseller/total/weekly?page={page}")

    time.sleep(t)
    # bs로 처리하기 위해 html 형식으로 변경 
    html = driver.page_source
    soup = bs(html, 'lxml')
    
    #한페이지에 있는 20개를 한번에 잡을 수 있도록 css 설정
    books = soup.select("ol.grid-cols-1 > li")

    for book in books:
        책이름 = book.select_one("a.mt-2").text
        저자 = book.select_one("div.mt-1").text.split("·")[0].strip()
        출판사 = book.select_one("div.mt-1").text.split("·")[1].strip()
        출판일 = book.select_one("span.date").text.replace("·", "").replace(".","").strip()
        원가 = book.select_one("s.text-gray-700").text.replace(",","").replace("원", "")
        할인가 =book.select_one("span > span.font-weight-bold").text.replace(",","")
        리뷰평점 = book.select_one("span.text-black").text
        print(count, 책이름, 저자, 출판사, 출판일, 원가, 할인가, 리뷰평점, sep = " / ")
        
        data.append({'순위' : count,
                     '책이름' : 책이름,
                     '저자' : 저자,
                     '출판사' : 출판사,
                     '출판일' : 출판일,
                     '원가' : 원가,
                     '할인가' : 할인가,
                     '리뷰평점' : 리뷰평점})
        count += 1

kyobobook = pd.DataFrame(data)
kyobobook.set_index("순위", inplace=True)

kyobobook.to_csv("kyobobook200_utf8.csv",encoding="utf8")
kyobobook.to_csv("kyobobook200_euc-kr.csv",encoding="euc-kr")
kyobobook.to_csv("kyobobook200_utf-8-sig.csv",encoding="utf-8-sig")

print("데이터가 파일로 저장되었습니다.")
#input("화면을 종료하려면 입력하세요.")


