def solution(board, h, w):
    n = len(board)
    move = [(0,1), (1,0), (0,-1), (-1,0)]
    count = 0 
    
    for dh, dw in move:
        nh = h+dh
        nw = w+dw
        
        if nh >=0 and nh <n and nw>=0 and nw < n:
            if board[h][w] == board[nh][nw]:
                count +=1 
                
    return count


result = solution([["blue", "red", "orange", "red"], ["red", "red", "blue", "orange"], ["blue", "orange", "red", "red"], ["orange", "orange", "red", "blue"]], 1, 1)
print(result)   #result : 2
