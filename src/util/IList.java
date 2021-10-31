package util;

import java.util.Iterator;

import util.IList.INode;

/***
 * 这是一个侵入式链表的非常规实现
 * 侵入式链表和一般链表的区别在于
 * 一般的链表是链表结点中存储了元素
 * Node{
 *   YourEleType yourEle;
 *   }
 * 而侵入式链表是元素中存储了链表
 * YourEleType{
 *   Node prev;
 *   Node next;
 * }
 * 这样做的好处是你在拿到一个元素的时候，
 * 能够很方便地知道这个元素的前一个元素和后一个元素是什么
 *
 * 如果你们这学期的OS实验还是 pintos,
 * 那实际上这个链表的和 pintos 里面的内核链表是差不多的
 * ps: linux 里面的链表也是这个设计，所以这种链表又称作内核链表
 * ***/

public class IList<T, P> implements Iterable<IList.INode<T, P>> {
    /***
     * 链表头，
     * 因为我们要实现的数据结构的包含关系是
     * Module contains Function and GlobalVariable
     * Function contains BasicBlock
     * BasicBlock contains Instruction
     * 可以看出来是一个多层的包含关系
     * 所以我们在这里实现了一个同样能够满足多层的包含关系的链表
     ***/
    private P val;
    //持有链表头的元素，比如一个 BasicBlock 持有了一个包含多个 Inst 的链表
    // ,那这个val就是这个BasicBlock
    private INode<T, P> entry;//这个链表的表头
    private INode<T, P> last;//表尾
    private int numNode;//节点数

    public IList(P val) {
        this.val = val;
        entry = last = null;
    }

    public int getNumNode() {return numNode;}

    public P getVal() {return val;}

    public INode<T, P> getEntry() {return entry;}

    public INode<T, P> getLast() {return last;}

    public void setEntry(INode<T, P> entry) {this.entry = entry;}

    public void setLast(INode<T, P> last) {this.last = last;}

    @Override
    public Iterator<INode<T, P>> iterator() {return new IIterator(entry);}
    //给 Ilist 实现了迭代器接口,不过没实现删除接口
    class IIterator implements Iterator<INode<T, P>> {

        INode<T, P> tmp = new INode<>(null);
        INode<T, P> nxt = null;

        IIterator(INode<T, P> head) {
            tmp.next = head;
        }

        @Override
        public boolean hasNext() {
            return nxt != null || tmp.next != null;
        }

        @Override
        public INode<T, P> next() {
            if (nxt == null) {
                tmp = tmp.next;
            } else {
                tmp = nxt;
            }
            nxt = null;
            return tmp;
        }
    }

    public static class INode<T, P> {

        private T val;
        private INode<T, P> prev = null;//前驱
        private INode<T, P> next = null;//后继
        private IList<T, P> parent = null;

        public INode(T t) {this.val = t;}

        public T getVal() {return val;}

        public void setVal(T newVal) {this.val = newVal;}

        public void setParent(IList<T, P> parent) {this.parent = parent;}

        public IList<T, P> getParent() {return parent;}

        public INode<T, P> getPrev() {return prev;}

        public INode<T, P> getNext() {return next;}

        //将自己插入prev后面
        public void insertAfter(INode<T, P> prev) {
            this.parent = prev.parent;
            this.parent.numNode++;
            if (prev.getParent().getLast() == prev) prev.getParent().setLast(this);
            this.prev = prev;
            this.next = prev.next;
            prev.next = this;
            if (this.next != null) this.next.prev = this;
        }

        //将自己插入目标结点前面
        public void insertBefore(INode<T, P> next) {
            this.parent = next.parent;
            this.parent.numNode++;
            if (next.getParent().getEntry() == next) next.getParent().setEntry(this);
            this.prev = next.prev;
            this.next = next;
            next.prev = this;
            if (this.prev != null) this.prev.next = this;
        }

        //将自己插入目标IList的开头
        public void insertAtEntry(IList<T, P> father) {
            this.setParent(father);
            if (father.getEntry() == null && father.getLast() == null) {
                father.numNode++;
                father.setEntry(this);
                father.setLast(this);
            } else {
                insertBefore(father.getEntry());
            }
        }


        // 将自己插入目标IList的末尾

        public void insertAtEnd(IList<T, P> father) {
            this.setParent(father);
            if (father.getEntry() == null && father.getLast() == null) {
                father.numNode++;
                father.setEntry(this);
                father.setLast(this);
                this.prev = null;
                this.next = null;
            } else {
                insertAfter(father.getLast());
            }
        }
    }

}


