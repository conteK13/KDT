from sklearn import svm, metrics
import random, re

csv = []
with open('iris.csv', 'r', encoding='utf-8') as fp:
    for line in fp:
        line = line.strip()
        cols = line.split(',')
        #문자열 데이터를 숫자로 변환하기
        fn = lambda n : float(n) if re.match(r'^[0-9\.]+$',n) else n
        # 정규식을 사용하여 숫자와 온점으로 이루어진 실수인지 구분, 실수라면 float로 변환, 아니라면 문자열 그대로 반환.
        cols = list(map(fn, cols)) #cols의 각 요소에 함수 fn을 적용.
        csv.append(cols)

# 가장 앞 줄의 헤더 제거
del csv[0]

# 데이터 셔플하기(섞기)
random.shuffle(csv)

#학습 전용 데이터와 테스트 전용 데이터 분할하기(2:1 비율)
total_len = len(csv)
train_len =int(total_len*2/3)
train_data =[]
train_label =[]
test_data = []
test_label =[]

for i in range(total_len):
    data= csv[i][0:3]
    label = csv[i][4]
    
    if i < train_len:
        train_data.append(data)
        train_label.append(label)
    else:
        test_data.append(data)
        test_label.append(label)
        
        
# 데이터를 학습 시키고 예측하기
clf = svm.SVC()
clf.fit(train_data, train_label)
pre = clf.predict(test_data)

# 정답률 구하기
ac_score= metrics.accuracy_score(test_label, pre)
print("정답률 =", ac_score)