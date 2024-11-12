import requests as req
from bs4 import BeautifulSoup as bs

res = req.get("https://news.naver.com/section/105")

soup = bs(res.text, 'lxml')

#newsct > div.section_component.as_section_headline._PERSIST_CONTENT > div.section_article.as_headline._TEMPLATE > div > span > a
news_head = soup.select_one("#newsct a").text  #i태그가 하나 더 있어서 text로 문자열 추출
print(news_head)

#ct_wrap > div.ct_lnb > div > strong
news_date = soup.select_one("#ct_wrap strong").string
print(news_date)

news1 = soup.select_one("#_SECTION_HEADLINE_LIST_7sz36 > li:nth-child(1) > div > div > div.sa_text > a > strong")
print(news1)


news2 = soup.select_one("#_SECTION_HEADLINE_LIST_7pek1 > li:nth-child(2) > div > div > div.sa_text > a > strong")
print(news2)


news1 = soup.select_one("#newsct li:nth-child(1) > div > div > div.sa_text > a > strong")
print(news1)


news2 = soup.select_one("#newsct li:nth-child(2) > div > div > div.sa_text > a > strong")
print(news2)
