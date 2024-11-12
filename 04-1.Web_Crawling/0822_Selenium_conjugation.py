from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.chrome.service import Service
from selenium.webdriver.chrome.options import Options
from webdriver_manager.chrome import ChromeDriverManager
from bs4 import BeautifulSoup as bs
import time

t = 5

serv = Service(executable_path=ChromeDriverManager().install())
driver = webdriver.Chrome(service=serv)
url = "https://www.coffeebeankorea.com/store/store.asp"

driver.get(url)
time.sleep(t) 
print("커피빈", driver.window_handles)
#driver.window_handles : 현재 driver가 가지고 있는 모든 handle의 값을 출력한다.
#지금은 커피빈 페이지만 열려 잇으므로 커피빈 페이지만 열릴 것이다.add()

#언제든 원래 handle이 돌아올 수 있도록 main_window를 현재 페이지로 설정한다.
main_window = driver.current_window_handle

#새로운 창(구글)을 연다.
driver.execute_script("window.open('https://www.google.com/')")
time.sleep(t)

#기존창(커피빈)에 대한 handle과 새로운창(구글)에 대한 handle이 생겼음.
print("구글 추가", driver.window_handles)

try:
    for handle in driver.window_handles:
        #handle이 main이 아니면
        if handle != main_window:
            # handle 변경
            driver.switch_to.window(handle)
            #현재 handle 출력
            print(driver.current_window_handle)
            time.sleep(t)
            break
    #현재 handle을 닫는다.
    driver.close()
    
    #main_window로 handle을 변경
    driver.switch_to.window(main_window)
    print(driver.current_window_handle)
    time.sleep(5)
    driver.quit()
except:
    pass

