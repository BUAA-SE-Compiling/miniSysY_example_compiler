package util;

import java.util.Iterator;

/***
 * 这是一个侵入式链表的非常规实现
 * 侵入式链表和一般链表的区别在于
 * 一般的链表是链表结点中存储了元素
 * Node{
 *   YourEleType yourEle;
 *   Node prev;
 *   Node succ;
 *   }
 * 而侵入式链表是元素中存储了链表结点
 * YourEleType{
 *   Node prev;
 *   Node succ;
 * }
 * 这样做的好处是你在拿到一个元素(而不是这个元素的Node)的时候，
 * 能够很方便地知道这个元素的前一个元素和后一个元素是什么
 *
 * 如果你们这学期的OS实验还是 pintos,
 * 那实际上这个链表的和 pintos 里面的内核链表是差不多的
 * ps: linux 里面的链表也是这个设计，所以这种链表又称作内核链表
 * ***/
//
public class IList<T, P> implements Iterable<IList.INode<T, P>> {
    private P val;
    //持有链表头的元素，比如一个 BasicBlock 持有了一个包含多个 Inst 的链表
    // ,那这个val就是这个BasicBlock
    private INode<T, P> head;//邵兵结点，不存储数据
    private INode<T, P> trailer;//邵兵结点
    private int numNode = 0;//节点数

    public IList(P val) {
        this.val = val;
        head = new INode<>();
        head.setParent(this);

        trailer = new INode<>();
        trailer.setParent(this);
        head.succ = trailer;
        trailer.prev = head;
    }

    public int getNumNode() {return numNode;}

    public P getVal() {return val;}

    public INode<T, P> getEntry() {return head.succ;}

    public INode<T, P> getLast() {return trailer.prev;}

    @Override
    public Iterator<INode<T, P>> iterator() {return new IIterator(head, trailer);}

    class IIterator implements Iterator<INode<T, P>> {
        INode<T, P> head;
        INode<T, P> trailer;
        INode<T, P> tmp;

        IIterator(INode<T, P> head, INode<T, P> trailer) {
            this.head = head;
            this.trailer = trailer;
            tmp = head;
        }

        @Override
        public boolean hasNext() {
            return tmp.succ != trailer;
        }

        @Override
        public INode<T, P> next() {
            var t = tmp.succ;
            tmp = tmp.succ;
            return t;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    public static class INode<T, P> {
        public final boolean isguard;
        private T val;
        private INode<T, P> prev = null;//前驱
        private INode<T, P> succ = null;//后继
        private IList<T, P> parent = null;

        public INode() {this.isguard = true;}//只用来产生哨兵

        public INode(T t) {
            this.val = t;
            this.isguard = false;
        }

        public T getVal() {return val;}

        public void setVal(T newVal) {this.val = newVal;}

        public void setParent(IList<T, P> parent) {this.parent = parent;}

        public IList<T, P> getParent() {return parent;}

        public INode<T, P> getPrev() {return prev;}

        public INode<T, P> getNext() {return succ;}

        //将自己插入prev后面
        public void insertAsPrevOf(INode<T, P> prev) {
            this.parent = prev.parent;
            this.parent.numNode++;
            this.prev = prev;
            this.succ = prev.succ;
            prev.succ = this;
            if (this.succ != null) this.succ.prev = this;
        }

        //将自己插入目标结点前面
        public void insertAsSuccOf(INode<T, P> succ) {
            this.parent = succ.parent;
            this.parent.numNode++;
            this.prev = succ.prev;
            this.succ = succ;
            succ.prev = this;
            if (this.prev != null) this.prev.succ = this;
        }

        //将自己插入目标IList的开头
        public void insertAtEntry(IList<T, P> father) {
            this.setParent(father);
            father.numNode++;
            insertAsPrevOf(father.head);
        }


        // 将自己插入目标IList的末尾
        public void insertAtEnd(IList<T, P> father) {
            this.setParent(father);
            father.numNode++;
            insertAsSuccOf(father.trailer);
        }

        public boolean removeSelf() {
            if (this.parent == null) return false;
            this.succ.prev = this.prev;
            this.prev.succ = this.succ;
            this.parent.numNode--;
            this.parent = null;
            return true;
        }
    }

}


