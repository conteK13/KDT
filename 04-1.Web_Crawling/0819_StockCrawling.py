import pandas as pd
import requests as req
from bs4 import BeautifulSoup as bs

sosok = 0 #0이면 코스피, 1이면 코스닥
pages = int(input("확인하고 싶은 마지막 페이지를 입력하세요 : "))


data = []

for page in range(1, pages+1):
    res = req.get(f"https://finance.naver.com/sise/sise_market_sum.naver?sosok={sosok}&page={page}")
    html = res.text
    soup = bs(html, "lxml")
    stocks = soup.select("table.type_2 tr")
    for stock in stocks:
        stock = stock.select("td")
        
        if len(stock) <= 1:
            continue
        
        #for문으로 돌려 볼려고 인덱스 형태로 바꿈
        n = stock[0].text
        종목명 = stock[1].text
        현재가 = stock[2].text
        전일비 = stock[3].text.strip().replace('\t','').replace('\n','').replace('상승','▲ ').replace('하락','▽ ')
        등락률 = stock[4].text.strip().replace('\t','').replace('\n','')
        
        print(f"{n} : {종목명} / {현재가} / {전일비} / {등락률}")

        data.append({'n' : n,
                    '종목명': 종목명,
                    '현재가': 현재가,
                    '전일비': 전일비, 
                    '등락률': 등락률
                    })

# stockChart = pd.DataFrame(data)
# stockChart.set_index("n", inplace=True)

# stockChart.to_csv("주식차트_utf8.csv",encoding="utf8")
# stockChart.to_csv("주식차트_euc-kr.csv",encoding="euc-kr")
# stockChart.to_csv("주식차트_utf-8-sig.csv",encoding="utf-8-sig")

# print("데이터가 파일로 저장되었습니다.")
