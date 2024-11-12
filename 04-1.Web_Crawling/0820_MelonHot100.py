import pandas as pd
import requests as req
from bs4 import BeautifulSoup as bs

from selenium import webdriver                              #크롬 창 역할
from webdriver_manager.chrome import ChromeDriverManager    #크롬 드라이버 자동 업데이트
from selenium.webdriver.common.by import By                 #요소 선택자를 구분해주는 라이브러리
from selenium.webdriver.chrome.service import Service       #크롬 드라이버를 최신 버전으로 설치

service = Service(executable_path=ChromeDriverManager().install())
driver = webdriver.Chrome(service=service)                  #크롬창을 만듦
driver.get("https://www.melon.com/chart/hot100/index.htm")

#좋아요 갯수를 동적 크롤링

# td:nth-child(8) > div > button > span.cnt
like_counts = driver.find_elements(By.CSS_SELECTOR, "tbody button span.cnt")

# 확인용
# for i in like_counts:
#     print(i.text)

h = {"User-Agent":"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/127.0.0.0 Safari/537.36"}
res = req.get("https://www.melon.com/chart/hot100/index.htm", headers=h)
html = res.text
soup = bs(html, "lxml")

chart = soup.select("#frm tbody tr")
data = []
count = 0
for song in chart:
    if len(song) <= 1:
        continue
    순위 = song.select_one("td:nth-child(2) span").text.strip()
    노래제목 = song.select_one("td:nth-child(6) div.ellipsis.rank01 > span").text.strip()
    가수 = song.select_one("td:nth-child(6) div.ellipsis.rank02 > span").text.strip()
    앨범 =song.select_one("td:nth-child(7) a").text.strip()
    좋아요 = like_counts[count].text.replace(",","")
    print(순위, 노래제목, 가수, 앨범, 좋아요, sep = " / ")
    data.append({'순위' : 순위,
            '노래제목': 노래제목,
            '가수': 가수,
            '앨범': 앨범,
            '좋아요' : 좋아요
            })
    count+=1

Melon = pd.DataFrame(data)
Melon.set_index("순위", inplace=True)

Melon.to_csv("MelonHot100_utf8.csv",encoding="utf8")
Melon.to_csv("MelonHot100_utf-8-sig.csv",encoding="utf-8-sig")
#Melon.to_csv("MelonHot100_euc-kr2.csv",encoding="EUC-KR")
#UnicodeEncodeError: 'euc_kr' codec can't encode character '\xcd' in position 25: illegal multibyte sequence

print("데이터가 파일로 저장되었습니다.")
#input("화면을 종료하려면 입력하세요.")

