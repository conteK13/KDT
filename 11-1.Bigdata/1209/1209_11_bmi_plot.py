import matplotlib.pyplot as plt
import pandas as pd

#Pandas로 CSV 파일 읽어 들이기
tbl = pd.read_csv("bmi.csv", index_col=2)

# 그래프 그ㅡ리기 시작
fig = plt.figure()
ax = fig.add_subplot(1, 1, 1)

# 서브플롯 전용
def scatter(lbl, color):
    b= tbl.loc[lbl]
    ax.scatter(b["weight"], b["height"], c=color, label=lbl)

scatter("fat", "red")
scatter("normal", "yellow")
scatter("thin", "purple")

ax.legend()
plt.savefig("bmi_test.png")
plt.show()