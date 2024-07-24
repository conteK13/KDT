def solution(players, callings):
    index_map = {player : idx for idx, player in enumerate(players)}
    
    for call_player in callings:
        now_idx = index_map[call_player]
        prev_player = players[now_idx-1]
        
        players[now_idx-1], players[now_idx] = players[now_idx], players[now_idx-1]
        index_map[call_player] -= 1
        index_map[prev_player] += 1
    return players

players= ["mumu", "soe", "poe", "kai", "mine"]
callings= ["kai", "kai", "mine", "mine"]

result = solution(players, callings)
print(result)