from sklearn.neighbors import KNeighborsClassifier
from sklearn.model_selection import train_test_split
from sklearn import datasets
import pandas as pd
from sklearn import svm, metrics


iris = datasets.load_iris()
# print(iris)

X= iris.data
y= iris.target

# 8:2로 분리
X_train, X_test, y_train, y_test = train_test_split(X, y, test_size=0.2, random_state=4)
print(X_train.shape)
print(X_test.shape)

knn = KNeighborsClassifier(n_neighbors=6)
knn.fit(X_train, y_train)

y_pred = knn.predict(X_test)
scores = metrics.accuracy_score(y_test, y_pred)
print(scores)

classes ={0:'setosa', 1:'versicolor', 2:'virginica'}

# 전혀 보지 못한 새로운 데이터를 제시해 보자
x_new = [[3,4,5,2], [5,4,2,2]]

y_predict = knn.predict(x_new)
print(classes[y_predict[0]])
print(classes[y_predict[1]])