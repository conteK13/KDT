import numpy as np
from sklearn.model_selection import train_test_split
from sklearn.neighbors import KNeighborsClassifier
import matplotlib.pyplot as plt

fish_length = [25.4, 26.3, 26.5, 29.0, 29.0, 29.7, 29.7, 30.0, 30.0, 30.7, 31.0, 31.0, 
                31.5, 32.0, 32.0, 32.0, 33.0, 33.0, 33.5, 33.5, 34.0, 34.0, 34.5, 35.0, 
                35.0, 35.0, 35.0, 36.0, 36.0, 37.0, 38.5, 38.5, 39.5, 41.0, 41.0, 9.8, 
                10.5, 10.6, 11.0, 11.2, 11.3, 11.8, 11.8, 12.0, 12.2, 12.4, 13.0, 14.3, 15.0]
fish_weight = [242.0, 290.0, 340.0, 363.0, 430.0, 450.0, 500.0, 390.0, 450.0, 500.0, 475.0, 500.0, 
                500.0, 340.0, 600.0, 600.0, 700.0, 700.0, 610.0, 650.0, 575.0, 685.0, 620.0, 680.0, 
                700.0, 725.0, 720.0, 714.0, 850.0, 1000.0, 920.0, 955.0, 925.0, 975.0, 950.0, 6.7, 
                7.5, 7.0, 9.7, 9.8, 8.7, 10.0, 9.9, 9.8, 12.2, 13.4, 12.2, 19.7, 19.9]


# 각 배열을 열로 결합
# print(np.column_stack(([1,2,3], [4,5,6])))
fish_data = np.column_stack((fish_length, fish_weight))


# 길이가 5인 배열을 만들고, 그 모든 요소를 1로 초기화하는 함수
# print(np.ones(5))

# 35까지는 1로, 이후 14개는 0으로 설정
fish_target = np.concatenate((np.ones(35), np.zeros(14)))
# concat은 주로 pandas를 이어붙일때 사용
# concatenate는 주로 Numpy에서 배열을 이어 붙일때 사용


train_input, test_input, train_target, test_target = train_test_split(fish_data, fish_target, random_state=42)
print(train_input.shape, test_input.shape)
print(train_target.shape, test_target.shape)
print(test_target)

# stratify=fish_target옵션 : fish_target의 각 클레스('0'과 '1' 혹은 '도미'와 '빙어'와 같은 클레스)의 비율을 반영하여 train과 test 설정
train_input, test_input, train_target, test_target = train_test_split(fish_data, fish_target, stratify=fish_target, random_state=42)
print(test_target)


kn = KNeighborsClassifier()
kn.fit(train_input, train_target)
# kn.score(test_input, test_target)
print(kn.score(test_input, test_target))

print(kn.predict([[25, 150]]))

# plt.scatter(train_input[:,0], train_input[:,1])
# plt.scatter(25, 150, marker='^')
# plt.xlabel('length')
# plt.ylabel('weight')
# plt.show()

# KNN 모델을 사용하여 특정 데이터 포인트와 훈련 데이터 셋에서 가장 가까운 이웃들을 찾아내는 코드
distances, indexes = kn.kneighbors([[25, 150]])
# distances 주어진 데이터 포인터와 가장 가까운 이웃들 간의 거리를 나타내는 배열
# indexes 훈련 데이터 셋 내에서 가장 가까운 이웃들의 인덱스

plt.scatter(train_input[:,0], train_input[:,1])
plt.scatter(25, 150, marker='^')
# 가장 가까운 k개의 훈련 데이터 포인트를 다이아몬드 모양의 마커(D)로 표시
plt.scatter(train_input[indexes,0], train_input[indexes,1], marker='D')
plt.xlabel('length')
plt.ylabel('weight')
plt.show()

print(train_input[indexes])


# 데이터의 스케일을 조정
# 사람이 생각할 때 데이터는 [25, 150]는 1(도미)에 가깝지만, 
# 거리상으로는 0(빙어)에 가깝다고 결정된 이유는 데이터의 스케일이 표준화 되지 않아서임

plt.scatter(train_input[:,0], train_input[:,1])
plt.scatter(25, 150, marker='^')
plt.scatter(train_input[indexes,0], train_input[indexes,1], marker='D')
plt.xlim((0, 1000))     # Y축의 변화가 작은 범위에 집중되어 y축이 거의 직선처럼 보일 수 있음
plt.xlabel('length')
plt.ylabel('weight')
plt.show()

#평균과 표준편차를 각 특성(열, axis=0)마다 계산
mean = np.mean(train_input, axis=0)
std = np.std(train_input, axis=0)

print(mean, std)

# 표준화 (Standardization) 또는 z-score 정규화라는 기법을 사용하여 훈련 데이터의 특성 (features)을 조정
# 머신러닝 모델이 효율적으로 학습하기 위해서
train_scaled = (train_input - mean) / std

plt.scatter(train_scaled[:,0], train_scaled[:,1])
#plt.scatter(25, 150, marker='^')
plt.xlabel('length')
plt.ylabel('weight')
plt.show()


new = ([25, 150] - mean) / std
plt.scatter(train_scaled[:,0], train_scaled[:,1])
plt.scatter(new[0], new[1], marker='^')
plt.xlabel('length')
plt.ylabel('weight')
plt.show()


kn.fit(train_scaled, train_target)
test_scaled = (test_input - mean) / std
kn.score(test_scaled, test_target)
print(kn.predict([new]))


distances, indexes = kn.kneighbors([new])

plt.scatter(train_scaled[:,0], train_scaled[:,1])
plt.scatter(new[0], new[1], marker='^')
plt.scatter(train_scaled[indexes,0], train_scaled[indexes,1], marker='D')
plt.xlabel('length')
plt.ylabel('weight')
plt.show()
# 기존에는 빙어와의 거리가 더 가깝다고 나타났다면,
# 현재는 도미와 더 유사하다고 나타남.