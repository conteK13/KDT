import requests as req
from bs4 import BeautifulSoup as bs
import pyautogui

res = req.get("https://finance.naver.com/news/mainnews.naver")
html = res.text

soup = bs(html, "lxml")
count=1
titles = soup.select(".articleSubject a")

for title in titles:
    print(f"{count}. {title.text}\n    {title.attrs['href']}")
    count+=1
    
# for i in range(1,7):
#     titles = soup.select(f"#content li:nth-child({i}) > span > a")
    
#     for title in titles:
#         print(f"{count}. {title.text}\n    {title.attrs['href']}")
#         count+=1
    
#input("아무 키나 누르면 종료 됩니다.")