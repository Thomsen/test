
graph = {
    "A": ["B", "C"],
    "B": ["A", "C", "D"],
    "C": ["A", "B", "D", "E"],
    "D": ["B", "C", "D", "F"],
    "E": ["C", "D"],
    "F": ["D"]
}

print(graph.keys())


def BFS(graph, s):
    queue = []
    queue.append(s)
    # seen = {}  # 'dict' object has no attribute 'add'
    seen = set()
    seen.add(s)

    parent = {s: None}

    while (len(queue) > 0):
        vertex = queue.pop(0)
        nodes = graph[vertex]
        for w in nodes:
            # if w NOT SEEN:
            if w not in seen:
                queue.append(w)
                seen.add(w)
                parent[w] = vertex
        # print(vertex)

    return parent


# BFS(graph, "A")
# print()
# BFS(graph, "E")

parent = BFS(graph, "E")
vt = 'B'
while vt != None:
    print(vt)
    vt = parent[vt]