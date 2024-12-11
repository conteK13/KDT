from sklearn import svm

# XOR의 계산 결과 데이터
xor_data = [
    #P, Q, result
    [0,0,0],
    [0,1,1],
    [1,0,1],
    [1,1,0]
]

# 학습을 위해 데이터와 레이블 분리하기
data =[]
label = []
for row in xor_data:
    p=row[0]
    q=row[1]
    r=row[2]
    data.append([p,q])
    label.append(r)     # label에 result가 들어감

# 데이터 학습 시키기
clf = svm.SVC()         # svm = Support Vector Machine / SVC =  Support Vector Classification
clf.fit(data, label)

# 데이터 예측하기
pre = clf.predict(data)
print("예측결과 :", pre)

# 결과 확인하기
ok =0; total=0

for idx, answer in enumerate(label):        # enumerate: 인덱스와 해당 인덱스의 값을 동시에 얻을 수 있게 해주는 함수
                                            # idx와 result를 동시에 변수에 할당해줌
    p = pre[idx]
    if p == answer: ok+=1                   # 예측한 값과 실제 값이 동일할 경우 ok+=1
    total +=1

print("정답률 :", ok, "/", total, "=", ok/total)