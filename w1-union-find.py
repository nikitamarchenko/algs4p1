__author__ = 'tpuctah'



def union(range, p, q):

    pid = range[p]
    qid = range[q]

    for i, v in enumerate(range):
        if v == pid:
            range[i] = qid
    #print ' '.join(map(str, r))

call_list = "3-6 4-5 9-4 7-0 9-0 3-7".split(' ')


r = range(0, 10)
for c in call_list:
    union(r, *map(int, c.split('-')))

print ' '.join(map(str, r))




class Q(object):
    def __init__(self, N):
        self.id = range(0, N)
        self.sz = [1]*N

    def root(self, i):
        while i != self.id[i]:
            i = self.id[i]
        return i

    def connected(self, p, q):
        return self.root(p) == self.root(q)

    def union(self, p, q):
        i = self.root(p)
        j = self.root(q)
        if i == j:
            return

        if self.sz[i] < self.sz[j]:
            self.id[i] = j
            self.sz[j] += self.sz[i]
        else:
            self.id[j] = i
            self.sz[i] += self.sz[j]



call_list = "4-7 2-0 5-6 7-9 8-3 3-2 4-6 8-4 3-1".split(' ')

q = Q(10)

for c in call_list:
    q.union(*map(int, c.split('-')))

print ' '.join(map(str, q.id))