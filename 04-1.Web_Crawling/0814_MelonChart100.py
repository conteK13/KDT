import requests as req
from bs4 import BeautifulSoup as bs
import pandas as pd
#곡명과 좋아요 갯수 출력

head = {"User-Agent":"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/127.0.0.0 Safari/537.36"}
res = req.get("https://www.melon.com/chart/index.htm", headers = head)
html = res.text
soup = bs(html, "lxml")

song = soup.select("div.ellipsis.rank01 a")
singer = soup.select("td:nth-child(6) > div > div > div.ellipsis.rank02 > a")
#songLikes = soup.select("#lst50 span.cnt")
#lst50 > td:nth-child(6) > div > div > div.ellipsis.rank02 > a
#lst50 > td:nth-child(6) > div > div > div.ellipsis.rank02


song_list = []
singer_list = []
rank_list = []
for i in range(len(song)):
    #print(i+1, ":", song[i].text, singer[i].text)
    song_list.append(song[i].text)
    singer_list.append(singer[i].text.strip())
    rank_list.append(i+1)
    print(rank_list[i], ":", song_list[i], singer_list[i])
    
# print(song_list)
# print(singer_list)
# print(rank_list)

dic = {"순위" : rank_list, "노래제목": song_list, "가수이름" : singer_list}
melon = pd.DataFrame(dic)
melon.set_index("순위",inplace=True)

melon.to_csv("멜론차트_utf-8.csv", encoding="utf-8")
melon.to_csv("멜론차트_euc-kr.csv", encoding="euc-kr")
melon.to_csv("멜론차트_utf-8-sig.csv", encoding="utf-8-sig")
