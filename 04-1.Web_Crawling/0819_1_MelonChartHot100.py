import pandas as pd
import requests as req
from bs4 import BeautifulSoup as bs


h = {"User-Agent":"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/127.0.0.0 Safari/537.36"}
res = req.get("https://www.melon.com/chart/hot100/index.htm", headers=h)
html = res.text
soup = bs(html, "lxml")

chart = soup.select("#frm > div > table tbody tr")
data = []
for song in chart:
    if len(song) <= 1:
        continue
    순위 = song.select_one("td:nth-child(2) span").text.strip()
    노래제목 = song.select_one("td:nth-child(6) div.ellipsis.rank01 > span").text.strip()
    가수 = song.select_one("td:nth-child(6) div.ellipsis.rank02 > span").text.strip()
    앨범 =song.select_one("td:nth-child(7) a").text.strip()
    print(순위, 노래제목, 가수, 앨범, sep = " / ")
    data.append({'순위' : 순위,
            '노래제목': 노래제목,
            '가수': 가수,
            '앨범': 앨범
            })

Melon = pd.DataFrame(data)
Melon.set_index("순위", inplace=True)


Melon.to_csv("MelonHot100_utf8.csv",encoding="utf8")
Melon.to_csv("MelonHot100_utf-8-sig.csv",encoding="utf-8-sig")
Melon.to_csv("MelonHot100_euc-kr2.csv",encoding="EUC-KR")


print("데이터가 파일로 저장되었습니다.")
#input("화면을 종료하려면 입력하세요.")