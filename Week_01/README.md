1. ### queue
    1. 特点:FIFO(first in first out),添加只能在队尾,删除只能删队头节点
    2. 主要方法:`offer()`：添加队尾节点;`poll()`：删除队头节点;`peek()`：获取队头节点
    3. 实现:很多实现类,常用实现LinkedList(链表,LinkedList实现Deuque,Deque实现Queue)
    4. 方法分析(以LinkedList为例):
        1. offer,最终调用的是`linkLast`,见注释
        ```
        void linkLast(E e) {
                //指向最后一个节点
                final Node<E> l = last;
                //创建一个prev节点为l,next节点为null的节点
                final Node<E> newNode = new Node<>(l, e, null);
                last = newNode;
                if (l == null)
                    //如何链表为空,first节点赋为newNode
                    first = newNode;
                else
                    //原有尾节点的next改为新增节点
                    l.next = newNode;
                size++;
                modCount++;
            }
        ```
        2.poll,最终调用的是`unlinkFirst`,见注释
        
        ```
           //f=first
         private E unlinkFirst(Node<E> f) {
            // assert f == first && f != null;
            final E element = f.item;
            final Node<E> next = f.next;
            f.item = null;
            f.next = null; // help GC
            //移除头结点
            first = next;
            if (next == null)
                last = null;
            else
                //清理头结点的prev节点
                next.prev = null;
            size--;
            modCount++;
            return element;
        }
        ```
        3.peek,比较简单,见注释
        
        ```
        public E peek() {
            //返回头节点,空链表返回空
            final Node<E> f = first;
            return (f == null) ? null : f.item;
        }
        ```
1. ### PriorityQueue
    1. 特点:优先队列,不同于FIFO,每次出队的节点为优先级最高的节点
    2. 本质:通过数组实现了一个平衡二叉树
        1. 头结点为优先级最高节点(自然排序的情况下)
        2. 左右,父节点有如下特点
            1. parentNode=(currentNode-1)/2
            2. leftNode=parentNode*2+1
            3. rightNode=parentNode*2+2
    3. 主要方法:`offer()`：添加节点;`remove()`：删除节点;
    4. 方法图例
        1.https://img-blog.csdnimg.cn/20190417102145729.jpg?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzM5NDcwNzQy,size_16,color_FFFFFF,t_70
       
    5. 方法分析(以LinkedList为例):
        1. `offer`:插入节点比较后可能上浮
        
        ```
        public boolean offer(E e) {
            if (e == null)
                throw new NullPointerException();
            modCount++;
            int i = size;
            if (i >= queue.length)
                grow(i + 1);//如需扩容,用copy方式扩容
            size = i + 1;
            if (i == 0)
                queue[0] = e;
            else
                siftUp(i, e);
            return true;
        }
        //自然顺序下siftUp调用的方法,比较上浮,rmove的上浮也是这个方法
        private void siftUpComparable(int k, E x) {
            Comparable<? super E> key = (Comparable<? super E>) x;
            while (k > 0) {
                int parent = (k - 1) >>> 1;
                Object e = queue[parent];
                if (key.compareTo((E) e) >= 0)
                    break;
                queue[k] = e;
                k = parent;
            }
            queue[k] = key;
        }
        ```
        2. `remove`:删除节点后,末尾节点可能上浮或下沉
        
        ```
        public boolean remove(Object o) {
            int i = indexOf(o);//查找到待删除节点
            if (i == -1)
                return false;
            else {
                removeAt(i);
                return true;
            }
        }
        private E removeAt(int i) {
            // assert i >= 0 && i < size;
            modCount++;
            int s = --size;
            if (s == i) // removed last element
                queue[i] = null;
            else {
                E moved = (E) queue[s];
                queue[s] = null;
                //下沉
                siftDown(i, moved);
                //如果没交换
                if (queue[i] == moved) {
                    //上浮
                    siftUp(i, moved);
                    if (queue[i] != moved)
                        return moved;
                }
            }
            return null;
        }
        ```
