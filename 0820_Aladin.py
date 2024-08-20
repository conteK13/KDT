import requests as req                                      #정적 크롤링
import pandas as pd                                         #정적_동적 크롤링
from bs4 import BeautifulSoup as bs                         #정적_동적 크롤링

res = req.get("https://www.aladin.co.kr/shop/common/wbest.aspx?BranchType=1")
soup = bs(res.text, "lxml")

books = soup.select("#Myform div:nth-child(1) > ul")

data = []
for book in books:
    책이름 = book.select_one("li:nth-last-child(4) > a.bo3 > b").text
    저자 = book.select_one("li:nth-last-child(3)").text.split("|")[0].strip()
    출판사 = book.select_one("li:nth-last-child(3)").text.split("|")[1].strip()
    출판일 = book.select_one("li:nth-last-child(3)").text.split("|")[2].strip()
    원가 = book.select_one("li:nth-last-child(2) > span:nth-child(1)").text.replace(",","")
    세일가 = book.select_one("li:nth-last-child(2) > span.ss_p2 > b > span").text.replace(",","")
    구분 = book.select_one("li:nth-last-child(4) > span").text.replace("[","").replace("]","")
    print(책이름, 저자, 출판사, 출판일, 원가, 세일가, 구분, sep = " / ")
    data.append({'책이름': 책이름,
                 '저자' :저자,
                 '출판사':출판사,
                 '출판일':출판일,
                 '원가': 원가,
                 '세일가':세일가, 
                 '구분' : 구분})

aladin = pd.DataFrame(data)
aladin.set_index("책이름", inplace=True)

aladin.to_csv("aladinBooks_utf8.csv",encoding="utf8")
aladin.to_csv("aladinBooks_euc-kr.csv",encoding="euc-kr")
aladin.to_csv("aladinBooks_utf-8-sig.csv",encoding="utf-8-sig")

print("데이터가 파일로 저장되었습니다.")
#input("화면을 종료하려면 입력하세요.")