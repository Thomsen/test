
graph = {
    "A": ["B", "C"],
    "B": ["A", "C", "D"],
    "C": ["A", "B", "D", "E"],
    "D": ["B", "C", "D", "F"],
    "E": ["C", "D"],
    "F": ["D"]
}

print(graph.keys())


def DFS(graph, s):
    stack = []
    stack.append(s)
    # seen = {}  # 'dict' object has no attribute 'add'
    seen = set()
    seen.add(s)
    while (len(stack) > 0):
        vertex = stack.pop()
        nodes = graph[vertex]
        for w in nodes:
            # if w NOT SEEN:
            if w not in seen:
                stack.append(w)
                seen.add(w)
        print(vertex)


DFS(graph, "A")
print()
DFS(graph, "E")