import requests as req
from bs4 import BeautifulSoup as bs


res = req.get("https://search.naver.com/search.naver?ssc=tab.news.all&where=news&sm=tab_jum&query=%EC%82%BC%EC%84%B1%EC%A0%84%EC%9E%90")
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