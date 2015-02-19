

class LifoStack(object):

    def __init__(self):
        self.list = []

    def push(self, item):
        self.list.append(item)

    def pop(self):
        return self.list.pop()

    def top(self):
        if len(self.list):
            return self.list[-1]
        return None


class FifoStack(object):
    def __init__(self):
        self.list = []

    def push(self, item):
        self.list.append(item)

    def pop(self):
        return self.list.pop(0)

    def top(self):
        if len(self.list):
            return self.list[0]
        return None


def lifo_test(input_text):
    container = LifoStack()
    input = range(0, 10)

    seq = map(int, input_text.split(' '))
    try:
        for s in seq:
            if container.top() != s:
                print 'expect {} in input'.format(s)
                if s not in input:
                    print 'FALURE {} not in {}'.format(s, input)
                    return False

                i = None
                while i != s:
                    i = input.pop(0)
                    container.push(i)
                    print 'push {}'.format(i)

            print 'pop {} real {}'.format(s, container.pop())
    except Exception as ex:
        print 'ERROR', ex
        return False
    return True



def fifo_test(input_text):
    container = FifoStack()
    input = range(0, 10)

    seq = map(int, input_text.split(' '))
    try:
        for s in seq:
            if container.top() != s:
                print 'expect {} in input'.format(s)
                if s not in input:
                    print 'FALURE {} not in {}'.format(s, input)
                    return False

                i = None
                while i != s:
                    i = input.pop(0)
                    container.push(i)

                    print 'push {}'.format(i)
                    print 'input {} fifo {}'.format(input, container.list)

            if s != container.top():
                print 'FALURE {} !=  {}'.format(s, container.top())
                print 'input {} fifo {}'.format(input, container.list)
                return False


            print 'pop {} real {}'.format(s, container.pop())
            print 'input {} fifo {}'.format(input, container.list)


    except Exception as ex:
        print 'ERROR', ex
        return False

    return True

def lifo_test_bulk(lines):
    result = []
    for l in lines.split('\n'):
        if l:
            result.append(lifo_test(l))

    for i, e in enumerate(result, 1):
        print i, e


lifo_test_bulk(
"""
6 5 4 3 2 1 0 7 8 9
1 3 4 2 6 8 5 7 0 9
0 1 3 5 2 6 7 8 4 9
0 2 1 3 4 5 7 9 8 6
5 4 9 8 7 6 3 2 1 0
"""
)

def fifo_test_bulk(lines):
    result = []
    for l in lines.split('\n'):
        if l:
            result.append(fifo_test(l))

    for i, e in enumerate(result, 1):
        print i, e

fifo_test_bulk(
'''
0 1 2 3 4 5 9 7 6 8
0 1 2 3 4 5 6 7 8 9
5 9 2 1 6 7 0 8 4 3
0 1 7 5 9 4 3 8 6 2
0 1 2 3 4 5 6 7 9 8
'''
)
