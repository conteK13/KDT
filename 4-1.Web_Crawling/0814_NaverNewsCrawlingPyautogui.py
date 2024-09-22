import requests as req
from bs4 import BeautifulSoup as bs
import pyautogui

keyword = pyautogui.prompt("검색어를 입력하세요")
endpage =  int(pyautogui.prompt("몇 페이지까지 검색하시겠습니까 ex)10, "))

count =1
page =1
for p in range(1, endpage*10+1, 10):
    #range(1, endpage*10, 10) / page 변수 = 10개 1페이지
    res = req.get(f"https://search.naver.com/search.naver?ssc=tab.news.all&where=news&sm=tab_jum&query={keyword}&start={p}")
    html = res.text

    soup = bs(html, "lxml")


    titles = soup.select(".news_tit")
    
    print("============================", page,"============================")
    for title in titles:
        print(f"{count}. {title.text}\n    {title.attrs['href']}")
        count+=1
    page+=1
    
#input("아무 키나 누르면 종료 됩니다.")