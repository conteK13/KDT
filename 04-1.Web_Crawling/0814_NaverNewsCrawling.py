import requests as req
from bs4 import BeautifulSoup as bs


res = req.get("https://news.naver.com/section/105")
html = res.text
soup = bs(html, "lxml")

# title = soup.select_one(".sa_text_strong")
# print(title.text)

# for i in range(1, 45):
#     title = soup.select_one("li:nth-child({}) .sa_text_strong".format(i))
#     print(title.text)

titles = soup.select(".sa_text_strong")
for i in range(len(titles)):
    print(f"{i+1}. {titles[i].text}")