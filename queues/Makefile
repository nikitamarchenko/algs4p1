
JFLAGS = -g
JC = javac-algs4
.SUFFIXES: .java .class
.java.class:
	$(JC) $(JFLAGS) $*.java

CLASSES = \
        Deque.java \
        RandomizedQueue.java \
        Subset.java

default: classes

classes: $(CLASSES:.java=.class)

clean:
	$(RM) *.class
