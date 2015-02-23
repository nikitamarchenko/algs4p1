


def insertion_sorting(a, ex_calls):

    ex_call = 0

    for i in range(0, len(a)):
        j = i
        while j > 0:
            if a[j] < a[j-1]:
                a[j], a[j-1] = a[j-1], a[j]
                ex_call += 1
                #print ex_call, ' '.join(map(str, seq))
                if ex_call == ex_calls:
                    print ex_call, ' '.join(map(str, seq))
            else:
                break
            j -= 1



def selection_sorting(a, ex_calls):

    ex_call = 0

    for i in range(0, len(a)):
        min = i
        j = i+1
        while j < len(a):
            if a[j] < a[min]:
                min = j
            j += 1

        a[i], a[min] = a[min], a[i]
        ex_call += 1
        #print ex_call, ' '.join(map(str, seq))
        if ex_call == ex_calls:
            print ex_call, ' '.join(map(str, seq))


input_text = "79 55 81 94 11 70 86 59 54 57"

seq = map(int, input_text.split(' '))

#insertion_sorting(seq, 6)

#print ' '.join(map(str, seq))

#selection_sorting(seq, 4)





input_text = '''
    mole   bull   bull   bull   dove   lion   bull   bull
    worm   calf   dove   calf   gnat   wolf   goat   calf
    bull   crab   gnat   crab   bull   bull   lamb   crab
    lamb   dove   goat   dove   calf   lamb   mole   dove
    goat   gnat   lamb   gnat   goat   goat   myna   gnat
    myna   goat   mole   goat   mole   myna   sole   goat
    sole   kiwi   myna   kiwi   kiwi   sole   swan   kiwi
    swan   lamb   sole   lamb   crab   swan   worm   lamb
    dove   lion   swan   swan   lion   dove   dove   lion
    gnat   mole   worm   myna   myna   gnat   gnat   mole
    kiwi   sole   kiwi   sole   sole   kiwi   kiwi   myna
    crab   myna   crab   mole   lamb   crab   crab   sole
    tuna   tuna   tuna   tuna   tuna   tuna   tuna   swan
    lion   swan   lion   lion   wolf   mole   lion   tuna
    wolf   wolf   wolf   wolf   worm   worm   wolf   wolf
    calf   worm   calf   worm   swan   calf   calf   worm
'''

lines = [filter(len, l.split(' ')) for l in input_text.split('\n') if l]
from pprint import pprint
#pprint(lines)
index_mapping = { l[-1]: i for i, l in enumerate(lines) }

for l in lines:
    for i in l:
        print '{:2}'.format(index_mapping[i]),
    print ''



