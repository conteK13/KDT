sum = 0

print(type(sum))          #result : <class 'int'>

if isinstance(sum, int):  #sum의 type이 int인지 확인
  del sum                 #sum 삭제
  
#이제 변수로 선언됐던 sum이 초기화 되면서, 다시 함수로 동작함

print(type(sum))          #result : <class 'builtin_function_or_method'>
