import requests as req
from bs4 import BeautifulSoup as bs

res = req.get("https://news.naver.com/section/105")
soup = bs(res.text, 'lxml')


news_head1 = soup.select_one("#newsct a")
news_head2 = soup.select_one("#newsct a").text
news_head3 = soup.select_one("#newsct a").string
#<a>와 </a> 사이에 모든 문자열을 가져와야 하는데, string은 그 사이에 태그(i)가 있어서 못 읽음
#<i> 태그 안에 있는 '안내'를 제외하고 헤드라인 뉴스만 가져온다면 어떻게 해야 할까


news_date1 = soup.select_one("#ct_wrap strong")
news_date2 = soup.select_one("#ct_wrap strong").text
news_date3 = soup.select_one("#ct_wrap strong").string

print(news_head1)
print(news_head2)
print(news_head3)
print(news_date1)
print(news_date2)
print(news_date3)
