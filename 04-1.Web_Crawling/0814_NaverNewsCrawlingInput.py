import requests as req
from bs4 import BeautifulSoup as bs

keyword = input("검색어를 입력하세요 : ")
res = req.get(f"https://search.naver.com/search.naver?ssc=tab.news.all&where=news&sm=tab_jum&query={keyword}")
html = res.text
soup = bs(html, "lxml")

# title = soup.select_one(".sa_text_strong")
# print(title.text)

# for i in range(1, 45):
#     title = soup.select_one("li:nth-child({}) .sa_text_strong".format(i))
#     print(title.text)

titles = soup.select(".news_tit")
for i in range(len(titles)):
    print(f"{i+1}. {titles[i].text}\n    {titles[i].attrs['href']}")
    
input("아무 키나 누르면 종료 됩니다.")