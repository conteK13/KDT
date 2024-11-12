import pandas as pd
import requests as req
from bs4 import BeautifulSoup as bs

data = []
count = 1
h = {"User-Agent":"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/127.0.0.0 Safari/537.36"}
res = req.get("https://www.aladin.co.kr/shop/common/wbest.aspx?BestType=Bestseller&BranchType=1&CID=0&page=1&cnt=1000&SortOrder=1",headers=h)

html = res.text
soup = bs(html, "lxml")
books = soup.select("#Myform td:nth-child(3) tr:nth-child(1) > td:nth-child(1)")
for book in books :
    if len(book) <= 4:
         continue

    책이름 = book.select_one("div:nth-child(1) li:nth-last-child(4) a").text
    info = book.select_one("div:nth-child(1) li:nth-last-child(3)")
    au = info.text.strip().split('|')[0] if info else 'N/A'
    au = au.replace("), "," (").split(' (')
    저자 = []
    for i in range(0,len(au)-1,2):
        # print(au[i+1][:].replace(") ",""),end = " : ")
        # print(au[i][:])
        저자.append(au[i+1][:].replace(") ","") + ' : ' + au[i][:])

    저자=' / '.join(저자)
    원가 = book.select_one("div:nth-child(1) li:nth-last-child(2) span").text
    세일가 = book.select_one("div:nth-child(1) li:nth-last-child(2) span b span").text
    구분 = book.select_one("div:nth-child(1) li:nth-last-child(4) span").text.replace('[','').replace(']','')

    print(count, 책이름, 저자, 원가, 세일가, 구분, sep=" / ")

    #이름 저자 원가 세일가 구분     
    data.append({'순위' : count,
        '책이름' : 책이름,
        '저자' : 저자,
        '원가' : 원가, 
        '세일가' :세일가,
        '구분' : 구분})
    count +=1
aladin = pd.DataFrame(data)
aladin.set_index("순위", inplace=True)


aladin.to_csv("aladinBooks_utf8.csv",encoding="utf8")
aladin.to_csv("aladinBooks_euc-kr.csv",encoding="euc-kr")
aladin.to_csv("aladinBooks_utf-8-sig.csv",encoding="utf-8-sig")

print("데이터가 파일로 저장되었습니다.")
#input("화면을 종료하려면 입력하세요.")