import pandas as pd
import requests as req
from bs4 import BeautifulSoup as bs

data = []
count = 1
for i in range(1,51):
    res = req.get(f"https://www.hollys.co.kr/store/korea/korStore2.do?pageNo={i}&sido=&gugun=&store=")
    html = res.text
    soup = bs(html, "lxml")

    stores = soup.select("#contents tbody > tr")

    for store in stores :
        if len(store) <= 1:
            continue
        지역 = store.select_one("td:nth-child(1)").text.strip()
        매장명 = store.select_one("td:nth-child(2)>a").text.strip()
        주소 = store.select_one("td:nth-child(4)>a").text.strip().replace(" .", "") #휴먼 에러(로 추정되는 것)은 replace로 제거.
        전화번호 = store.select_one("td:nth-child(6)").text.strip()
        if 전화번호 == ".": #전화번호가 누락된 경우에 대한 구체적인 표시.
            전화번호 = "전화번호 누락"
        print(count, 지역, 매장명, 주소, 전화번호, sep = " / ")
        data.append({'번호' : count,
            '지역': 지역,
            '매장명': 매장명,
            '주소': 주소, 
            '전화번호': 전화번호
            })
        count +=1

hallysStore = pd.DataFrame(data)
hallysStore.set_index("번호", inplace=True)


hallysStore.to_csv("hallysStore_utf8.csv",encoding="utf8")
hallysStore.to_csv("hallysStore_euc-kr.csv",encoding="euc-kr")
hallysStore.to_csv("hallysStore_utf-8-sig.csv",encoding="utf-8-sig")

print("데이터가 파일로 저장되었습니다.")
#input("화면을 종료하려면 입력하세요.")