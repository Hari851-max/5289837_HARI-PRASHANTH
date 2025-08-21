import sys

class QueueUsingTwoStacks:
    def __init__(self):
        self.in_stack = []
        self.out_stack = []

    def enqueue(self, value):
        self.in_stack.append(value)

    def dequeue(self):
        self.shift_stacks()
        if self.out_stack:
            self.out_stack.pop()

    def front(self):
        self.shift_stacks()
        if self.out_stack:
            print(self.out_stack[-1])

    def shift_stacks(self):
        if not self.out_stack:
            while self.in_stack:
                self.out_stack.append(self.in_stack.pop())

if __name__ == "__main__":
    q = int(sys.stdin.readline().strip())
    queue = QueueUsingTwoStacks()

    for _ in range(q):
        query = sys.stdin.readline().strip().split()
        if query[0] == "1":
            queue.enqueue(int(query[1]))
        elif query[0] == "2":
            queue.dequeue()
        elif query[0] == "3":
            queue.front()