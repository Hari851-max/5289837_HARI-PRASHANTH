def simpleTextEditor(ops):
    s = ""
    history = []
    
    for op in ops:
        parts = op.split()
        if parts[0] == "1":
            history.append(s)
            s += parts[1]
        elif parts[0] == "2":
            k = int(parts[1])
            history.append(s)
            s = s[:-k]
        elif parts[0] == "3":
            k = int(parts[1])
            print(s[k-1])
        elif parts[0] == "4":
            s = history.pop()
if __name__ == "__main__":
    Q = int(input())
    ops = [input().strip() for _ in range(Q)]
    simpleTextEditor(ops)
