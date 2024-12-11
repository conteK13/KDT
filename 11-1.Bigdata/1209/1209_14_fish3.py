import matplotlib.pyplot as plt
import numpy as np
from sklearn.model_selection import train_test_split
from sklearn.neighbors import KNeighborsRegressor
from sklearn.metrics import mean_absolute_error

perch_length = np.array(
    [8.4, 13.7, 15.0, 16.2, 17.4, 18.0, 18.7, 19.0, 19.6, 20.0, 
     21.0, 21.0, 21.0, 21.3, 22.0, 22.0, 22.0, 22.0, 22.0, 22.5, 
     22.5, 22.7, 23.0, 23.5, 24.0, 24.0, 24.6, 25.0, 25.6, 26.5, 
     27.3, 27.5, 27.5, 27.5, 28.0, 28.7, 30.0, 32.8, 34.5, 35.0, 
     36.5, 36.0, 37.0, 37.0, 39.0, 39.0, 39.0, 40.0, 40.0, 40.0, 
     40.0, 42.0, 43.0, 43.0, 43.5, 44.0]
     )
perch_weight = np.array(
    [5.9, 32.0, 40.0, 51.5, 70.0, 100.0, 78.0, 80.0, 85.0, 85.0, 
     110.0, 115.0, 125.0, 130.0, 120.0, 120.0, 130.0, 135.0, 110.0, 
     130.0, 150.0, 145.0, 150.0, 170.0, 225.0, 145.0, 188.0, 180.0, 
     197.0, 218.0, 300.0, 260.0, 265.0, 250.0, 250.0, 300.0, 320.0, 
     514.0, 556.0, 840.0, 685.0, 700.0, 700.0, 690.0, 900.0, 650.0, 
     820.0, 850.0, 900.0, 1015.0, 820.0, 1100.0, 1000.0, 1100.0, 
     1000.0, 1000.0]
     )

plt.scatter(perch_length, perch_weight)
plt.xlabel('length')
plt.ylabel('weight')
plt.show()

train_input, test_input, train_target, test_target = train_test_split(perch_length, perch_weight, random_state=42)
print(train_input.shape, test_input.shape)  #(42,) (14,)

# 배열의 크기 재변환
# reshape = NumPy 배열이나 TensorFlow와 같은 라이브러리에서 배열의 차원을 변경하는 함수
# (-1, 1)-> -1 : 배열의 길이에 맞춰서 자동으로 그 차원의 크기 결정 => train=42 / test = 14 
#           1  : 두번째 차원을 1로 설정(배열을 세로로 쭉 늘여 놓음)
train_input = train_input.reshape(-1, 1)
test_input = test_input.reshape(-1, 1)

print(train_input.shape, test_input.shape) # (42, 1) (14, 1)


knr = KNeighborsRegressor()
# k-최근접 이웃 회귀 모델을 훈련
knr.fit(train_input, train_target)
# knr.score(test_input, test_target)
print(knr.score(test_input, test_target))
# 0.992809406101064

# 테스트 세트에 대한 예측을 만듦
test_prediction = knr.predict(test_input)
# 테스트 세트에 대한 평균 절댓값 오차를 계산
# 평균 절댓값 오차 (Mean Absolute Error)
# 예측값과 실제 값 사이의 차이(오차)의 절댓값을 평균낸 값
mae = mean_absolute_error(test_target, test_prediction)
print(mae)
#19.157142857142862

# MAE는 직관적으로 이해하기 쉬운 지표
# 오차가 커지면 모델의 성능에 대한 해석이 상대적으로 약해질 수 있습니다.
# 작은 오차들이 다소 무시될 수 있습니다.
# 단위가 원본 데이터와 동일




# 과대적합 vs 과소적합
print(knr.score(train_input, train_target))
# 0.9698823289099254

# 이웃의 갯수를 3으로 설정합니다
knr.n_neighbors = 3
# 모델을 다시 훈련합니다
knr.fit(train_input, train_target)
print(knr.score(train_input, train_target))
# 0.9804899950518966

print(knr.score(test_input, test_target))
# 0.9746459963987609


# 과대적합과 과소적합
# 과대적합 : 훈련세트의 정확도는 높지만, 테스트 세트의 정확도는 낮음.
#            모델이 훈련 데이터에 과도하게 맞춰져 있어 테스트에서 성능이 떨어지는 상황
# 과소적합 : 훈련세트의 정확도와 테스트 세트의 정확도가 모두 낮을 때. 모델이 데이터의 패턴을 학습 x
# n_neighbors 가 1일때 과대적합 될 가능성이 높다.
# n_neighbors 가 10일때 과소적합 될 가능성이 높다.


# k-최근접 이웃 회귀 객체를 만듭니다
knr = KNeighborsRegressor()
# 5에서 45까지 x 좌표를 만듭니다
x = np.arange(5, 45).reshape(-1, 1)

# n = 1, 5, 10일 때 예측 결과를 그래프로 그립니다.
for n in [1, 5, 10]:
    # 모델 훈련
    knr.n_neighbors = n
    knr.fit(train_input, train_target)
    # 지정한 범위 x에 대한 예측 구하기 
    prediction = knr.predict(x)
    # 훈련 세트와 예측 결과 그래프 그리기
    plt.scatter(train_input, train_target)
    plt.plot(x, prediction)
    plt.title('n_neighbors = {}'.format(n))    
    plt.xlabel('length')
    plt.ylabel('weight')
    plt.show()