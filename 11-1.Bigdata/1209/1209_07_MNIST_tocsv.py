import struct

def to_csv(name, maxdata):
    #레이블 파일과 이미지 열기
    lbl_f = open("./mnist/"+name+"-labels-idx1-ubyte", "rb")
    img_f = open("./mnist/"+name+"-images-idx3-ubyte", "rb")
    csv_f = open("./mnist/"+name+".csv", "w", encoding="utf-8")
    
    #헤더 정보 읽기
    mag, lbl_count = struct.unpack(">II", lbl_f.read(8))
    # struct.unpack() 바이너리 데이터를 Python 객체로 변환
    # ">II" : 빅엔디안 방식으로 4바이트 정수 두 개
    #  헤더에는 2개의 4바이트 정수가 포함되어 있기 때문에 8바이트를 읽겠다는 의미
    mag, img_count = struct.unpack(">II", img_f.read(8))
    rows, cols = struct.unpack(">II", img_f.read(8))
    pixels = rows*cols
    
    # 이미지 데이터를 읽고 CSV 파일로 저장하기
    for idx in range(lbl_count):
        if idx > maxdata: break             # maxdata의 수는 함수의 인수로 받음
        label = struct.unpack("B", lbl_f.read(1))[0]
        bdata = img_f.read(pixels)              # 이미지에서 읽어온 픽셀 데이터. 바이트 형식
        sdata = list(map(lambda n: str(n), bdata))  #픽셀 데이터를 문자열로 변환한 후 리스트로 저장
        csv_f.write(str(label)+",")             # 라벨 값(결과)
        csv_f.write(",".join(sdata)+"\r\n")     # 이미지를 색에 따라 0과 255 사이의 숫자로 변환한 후 한줄로 표현
        
        # 잘 저장됐는지 이미지 파일로 저장해서 테스트 하기
        if idx < 10:
            s = "P2 28 28 255\n"
            # P2는 PGM (Portable GrayMap) 포맷에서 흑백 이미지를 나타내는 형식을 지정하는 식별자.add()
            # 이미지의 가로와 세로 크기
            # 픽셀 값의 최대값
            s+= " ".join(sdata)
            iname = "./mnist/{0}-{1}-{2}.pgm".format(name, idx, label)
            with open(iname, "w", encoding="utf-8") as f:
                f.write(s)
                
    csv_f.close()
    lbl_f.close()
    img_f.close()
# 결과를 파일로 출력하기 --- (※4)
to_csv("train", 999)
to_csv("t10k", 500)

            