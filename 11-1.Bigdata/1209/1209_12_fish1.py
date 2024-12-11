from sklearn.neighbors import KNeighborsClassifier
import numpy as np
import matplotlib.pyplot as plt

fish_length = [25.4, 26.3, 26.5, 29.0, 29.0, 29.7, 29.7, 30.0, 30.0, 30.7, 31.0, 31.0, 
                31.5, 32.0, 32.0, 32.0, 33.0, 33.0, 33.5, 33.5, 34.0, 34.0, 34.5, 35.0, 
                35.0, 35.0, 35.0, 36.0, 36.0, 37.0, 38.5, 38.5, 39.5, 41.0, 41.0, 9.8, 
                10.5, 10.6, 11.0, 11.2, 11.3, 11.8, 11.8, 12.0, 12.2, 12.4, 13.0, 14.3, 15.0]
fish_weight = [242.0, 290.0, 340.0, 363.0, 430.0, 450.0, 500.0, 390.0, 450.0, 500.0, 475.0, 500.0, 
                500.0, 340.0, 600.0, 600.0, 700.0, 700.0, 610.0, 650.0, 575.0, 685.0, 620.0, 680.0, 
                700.0, 725.0, 720.0, 714.0, 850.0, 1000.0, 920.0, 955.0, 925.0, 975.0, 950.0, 6.7, 
                7.5, 7.0, 9.7, 9.8, 8.7, 10.0, 9.9, 9.8, 12.2, 13.4, 12.2, 19.7, 19.9]

fish_data = [[l, w] for l, w in zip(fish_length, fish_weight)]
fish_target = [1]*35+ [0]*14        # 1은 도미, 0은 빙어

kn = KNeighborsClassifier()


# fish_data 확인하기
# print(fish_data[4])
# print(fish_data[0:5])
# print(fish_data[:5])
# print(fish_data[44:])

train_input = fish_data[:35]
train_target = fish_target[:35]

test_input = fish_data[35:]
test_target = fish_target[35:]


#첫번째 머신러닝
#training 용 데이터와 test용 데이터가 너무 달라서 추론이 되지 않는 현상 발생
kn = kn.fit(train_input, train_target)
kn.score(test_input, test_target)
print(kn.score(test_input, test_target))    # 0.0


# input용 데이터를 np 배열로 변환
input_arr =np.array(fish_data)
target_arr = np.array(fish_target)

print(input_arr.shape)      # input_arr의 크기 받기

np.random.seed(42)
index = np.arange(49)       # input_arr의 크기만큼 배열 생성
np.random.shuffle(index)    # 생성한 input_arr 섞기

print(index)

print(input_arr[[1,3]])     # input_arr에서 1번째, 3번째(인덱스를 이용한 접근) 데이터를 가져옴

# 위의 방식을 응용해서, suffle한 값으로 train과 test를 나눔
# 데이터 자체를 섞으면 input과 target의 매칭이 풀릴 확률이 있기 때문
train_input= input_arr[index[:35]]
train_target= target_arr[index[:35]]

test_input= input_arr[index[35:]]
test_target= target_arr[index[35:]]


# 산점도로 나타내기
plt.scatter(train_input[:, 0], train_input[:, 1], label="Train")
plt.scatter(test_input[:, 0], test_input[:,1], label="Test")
plt.xlabel('length')
plt.ylabel('weight')
plt.legend()
plt.show()


# 두번째 머신러닝
# test에 값이 적절히 섞여 학습이 적절히 이루어짐
kn = kn.fit(train_input, train_target)
kn.score(test_input, test_target)
print(kn.score(test_input, test_target))

# kn.predict(test_input)
print(kn.predict(test_input))

# test_target
print(test_target)