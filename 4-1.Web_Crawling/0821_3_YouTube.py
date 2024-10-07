from bs4 import BeautifulSoup as bs
import pandas as pd
from selenium import webdriver
from webdriver_manager.chrome import ChromeDriverManager
from selenium.webdriver.common.by import By
from selenium.webdriver.chrome.service import Service
from selenium.webdriver.common.keys import Keys 
import time

#지연시간을 전체적으로 수정할수 있게 변수를 사용함.
t = 3

#검색어 입력받기
word =input("검색어를 입력하세요 ▶ ")

#chrome으로 동적 크롤릴할 준비.
service = Service(executable_path=ChromeDriverManager().install())
driver = webdriver.Chrome(service=service)
driver.get(f"https://www.youtube.com/results?search_query={word}")
time.sleep(t)

try:
    # XPATH로 간단하게 선택.
    serch = driver.find_element(By.XPATH, '//*[@title="최근에 업로드된 동영상"]').click()
    time.sleep(t)
except:
    pass
body = driver.find_element(By.TAG_NAME, 'body')
for i in range(1, 11): #스크롤 내리는 횟수
    body.send_keys(Keys.END)
    time.sleep(t)

html = driver.page_source
soup = bs(html, 'lxml')
titles = soup.select("#video-title > yt-formatted-string")
views=soup.select("#metadata-line > span:nth-child(3)")
data = []
count= 1

for j in range(len(titles)):
    title=titles[j].text.strip()
    view=views[j].text.replace("조회수","").strip()
    data.append({'번호':count,
                 '동영상제목' : title,
                 '조회수' : view})
    print(count, title, view)
    count+=1

YouTube = pd.DataFrame(data)
YouTube.set_index("번호", inplace=True)

YouTube.to_csv("YouTube_utf8.csv",encoding="utf8")
YouTube.to_csv("YouTube_utf-8-sig.csv",encoding="utf-8-sig")
#YouTube.to_csv("YouTube_euc-kr2.csv",encoding="EUC-KR")
#UnicodeEncodeError: 'euc_kr' codec can't encode character '\xcd' in position 25: illegal multibyte sequence

print("데이터가 파일로 저장되었습니다.")
#input("화면을 종료하려면 입력하세요.")