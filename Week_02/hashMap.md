1. ### 关键点
    1. 线程不安全
    2. 主要通过`hashcode`获取数据,根据key的equals获取到val
    3. hash冲突的处理方式
        1. 若干key的hash值按数组大小取余后,落在同一个下标(桶)里,形成一个entry链表
            1. 如果链表的容量超过阈值TREEIFY_THRESHOLD(8)会改为红黑树结构
        2. 查找key需要遍历entry链表上的每个元素,做equals()比较
    4. 方法分析:
        1. `put`,见注释
        ```
        public V put(K key, V value) {
        //对key做hash,确定数据插入那个"桶"内
        return putVal(hash(key), key, value, false, true);
        }

        final V putVal(int hash, K key, V value, boolean onlyIfAbsent,
                       boolean evict) {
            Node<K,V>[] tab; Node<K,V> p; int n, i;
            //步骤①:如何table为空,进行扩容(resize)
            if ((tab = table) == null || (n = tab.length) == 0)
                n = (tab = resize()).length;
            //步骤②:确定插入table的index位置,如果这个槽位为null,key,val组合作为头节点插入
            if ((p = tab[i = (n - 1) & hash]) == null)
                tab[i] = newNode(hash, key, value, null);
            else {
                Node<K,V> e; K k;
                //步骤③:发生hash碰撞;如果值存在,覆盖
                if (p.hash == hash &&
                    ((k = p.key) == key || (key != null && key.equals(k))))
                    e = p;
                //步骤④:发生hash碰撞后;如果该槽位是红黑树,走红黑树的put方法
                else if (p instanceof TreeNode)
                    e = ((TreeNode<K,V>)p).putTreeVal(this, tab, hash, key, value);
                else {
                    //步骤⑤:发生hash碰撞后;链表方式,遍历
                    for (int binCount = 0; ; ++binCount) {
                        if ((e = p.next) == null) {
                            //步骤⑤.①:无重复节点,新节点加在尾部,如果超过阈值,转为红黑树
                            p.next = newNode(hash, key, value, null);
                            if (binCount >= TREEIFY_THRESHOLD - 1) // -1 for 1st
                                treeifyBin(tab, hash);
                            break;
                        }
                        //步骤⑤.②:节点存在,替换
                        if (e.hash == hash &&
                            ((k = e.key) == key || (key != null && key.equals(k))))
                            break;
                        p = e;
                    }
                }
                if (e != null) { // existing mapping for key
                    V oldValue = e.value;
                    if (!onlyIfAbsent || oldValue == null)
                        e.value = value;
                    afterNodeAccess(e);
                    return oldValue;
                }
            }
            ++modCount;
            //步骤⑥:容量超过阈值,扩容
            if (++size > threshold)
                resize();
            afterNodeInsertion(evict);
            return null;
        }
        ```
        2.`get`,见注释
        
        ```
        public V get(Object key) {
        Node<K,V> e;
        //对key做hash,确定数据在那个"桶"内
        return (e = getNode(hash(key), key)) == null ? null : e.value;
        }

        /**
         * Implements Map.get and related methods
         *
         * @param hash hash for key
         * @param key the key
         * @return the node, or null if none
         */
        final Node<K,V> getNode(int hash, Object key) {
            Node<K,V>[] tab; Node<K,V> first, e; int n; K k;
            //步骤①:table不为空,所在槽位不为空,才往下走
            if ((tab = table) != null && (n = tab.length) > 0 &&
                (first = tab[(n - 1) & hash]) != null) {
                //步骤②:始终校验first是否匹配,如果匹配直接返回,避免循环
                if (first.hash == hash && // always check first node
                    ((k = first.key) == key || (key != null && key.equals(k))))
                    return first;
                if ((e = first.next) != null) {
                    //步骤③:红黑树结果,直接走红黑树的get方法
                    if (first instanceof TreeNode)
                        return ((TreeNode<K,V>)first).getTreeNode(hash, key);
                    //步骤④:循环链表,查找是否有该key
                    do {
                        if (e.hash == hash &&
                            ((k = e.key) == key || (key != null && key.equals(k))))
                            return e;
                    } while ((e = e.next) != null);
                }
            }
            return null;
        }
        ``````
      
        3. 扩容`resize()`
            1. 本质是通过新建一个2倍容量的数组,然后把原数组数据copy到新数组
            2. 数据的下标可能会产生变化
        4. hash函数`hash()` 见注释
        ```
        static final int hash(Object key) {
            int h;
            //①取hashCode值: h = key.hashCode() 
            //②高位参与运算 h ^ (h >>> 16)      
            return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
        }
        ```
        
   