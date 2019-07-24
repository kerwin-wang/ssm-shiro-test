package com.kerwin.shiro.test.web.util;

/**
 * @ClassName: SeqList
 * @version: v1.0.0
 * @Author: d.w
 * @Date: 2019-06-27 13:09
 */
public class SeqList<T> extends Object
{
    /** 数组 */
    private Object[] elements;

    /** 长度 */
    private int n;

    /**
     * 构造方法，根据传参创建空表
     *
     * @param length
     */
    public SeqList(int length)
    {
        this.elements = new Object[length];
//        this.n = length;
    }

    /**
     * 空参构造将会调用本类已声明的指定参数列表的构造方法 进而创建一个空表,默认长度为64
     */
    public SeqList()
    {
        this(64);
    }

    /**
     * 重载构造方法，根据传参确定表长度，并且将数组元素复制到成员变量elements中
     *
     * @param values
     */
    public SeqList(T[] values)
    {
        // 创建容量为values.length的空表
        this(values.length);
        for (int i = 0; i < values.length; i++)
        {
            // 复制数组元素
            this.elements[i] = values[i];
            this.n = elements.length;
        }
    }

    /**
     * 判断顺序表是否为空，为空则返回true,时间复杂度为O(1)
     *
     * @return
     */
    public boolean isEmpty()
    {
        return this.n == 0;
    }

    /**
     * 返回顺序表元素个数,时间复杂度为O(1)
     *
     * @return
     */
    public int size()
    {
        return this.n;
    }

    /**
     * 返回第i个元素，若i越界则返回null,,时间复杂度为O(1)
     *
     * @param index
     * @return
     */
    @SuppressWarnings("unchecked")
    public T get(int index)
    {
        if (index >= 0 && index < this.n)
        {
            return (T) this.elements[index];
        }
        else
        {
            return null;
        }
    }

    /**
     * 替换指定位置的元素，若index越界，则抛出越界异常，若x为空，则抛出空对象异常,时间复杂度为O(1)
     *
     * @param index
     * @param x
     */
    public void set(int index, T x)
    {
        if (x == null)
        {
            throw new NullPointerException("x==null");
        }
        if (index >= 0 && index < this.n)
        {
            this.elements[index] = x;
        }
        else
        {
            throw new java.lang.IndexOutOfBoundsException(index + "");
        }
    }

    /**
     * 覆写Object的toString 方法,需要遍历,时间复杂度为O(n)
     */
    @Override
    public String toString()
    {
        String str = this.getClass().getName() + "(";
        if (this.n > 0)
        {
            str += this.elements[0].toString();
        }
        for (int i = 1; i < this.n; i++)
        {
            str += "," + this.elements[i].toString();
        }
        return str + ")";
    }

    /**
     * 在指定位置插入操作，index是插入的位置（下标），x是插入的元素
     *
     * @param index
     * @param x
     * @return
     */
    public int add(int index, T x)
    {
        if (x == null)
        {
            throw new NullPointerException("x==null");
        }
        // 对index进行容错处理,使下标index始终在数组长度范围内
        if (index < 0)
        {
            index = 0;
        }
        if (index > this.n)
        {
            index = this.n;
        }
        // 复制原数组到一个新的数组source中
        Object[] source = this.elements;
        // 如数组满，则扩充顺序表的数组容量，通过重申请和复制完成
        if (this.n == elements.length)
        {
            this.elements = new Object[source.length + 1];
            // 复制当前数组前i-1个元素到新的数组中
            for (int j = 0; j < index; j++)
            {
                this.elements[j] = source[j];
            }
        }
        // x插入为第i个元素
        this.elements[index] = x;
        // 从i开始至表尾的元素往后移，次序从后向前，这些元素的下标都要加1
        for (int j = this.n - 1; j >= index; j--)
        {
            this.elements[j + 1] = source[j];
        }
        // 数组的长度增加
        this.n++;
        // 返回插入元素的下标
        return index;
    }

    /**
     * 重载add方法，在尾部插入元素
     *
     * @param x
     * @return
     */
    public int add(T x)
    {
        return this.add(this.n, x);
    }

    /**
     * 在尾部插入一个线性表对象
     *
     * @param newList
     * @return
     */
    public int addAll(SeqList<T> newList)
    {
        Object[] source = this.elements;
        this.n = source.length + newList.size();
        // 数组扩容
        this.elements = new Object[n];
        // 将原数组的元素拷贝到新数组中
        for (int i = 0; i < source.length; i++)
        {
            this.elements[i] = source[i];
        }
        // 将插入的集合元素拷贝到数组的后面
        for (int j = 0; j < newList.size(); j++)
        {
            this.elements[source.length + j] = newList.get(j);
        }
        return n;
    }

    /**
     * 从指定位置index处开始插入一组元素
     *
     * @param index
     * @param newList
     * @return
     */
    public int addAll(int index, SeqList<T> newList)
    {
        // 对index进行容错处理,使下标index始终在数组长度范围内
        if (index < 0)
        {
            index = 0;
        }
        if (index > this.n)
        {
            index = this.n;
        }
        // 复制原数组到一个新的数组source中
        Object[] source = this.elements;
        this.n = source.length + newList.size();
        this.elements = new Object[n];
        // 从source中拷贝index之前的元素
        for (int j = 0; j < index; j++)
        {
            this.elements[j] = source[j];
        }
        // 从集合中取出元素拷贝到新数组中
        for (int j = 0; j < newList.size(); j++)
        {
            this.elements[j + index] = newList.get(j);
        }
        // 从source中拷贝index之后的元素
        for (int j = index; j < source.length; j++)
        {
            this.elements[j + newList.size()] = source[j];
        }
        return n;
    }

    /**
     * 删除元素，返回被删除元素，index之后的元素都要往前移一位
     *
     * @param index
     */
    @SuppressWarnings("unchecked")
    public T remove(int index)
    {
        T old = null;
        if (index > this.n)
        {
            index = this.n - 1;
        }
        if (index >= 0 && index < this.n)
        {
            old = (T) this.elements[index]; // 被删除元素
        }
        // 复制原数组到source作为备份
        Object[] source = this.elements;
        // 前i个元素复制
        for (int j = 0; j < index; j++)
        {
            this.elements[j] = source[j];
        }
        // 后i个朝前挪一位
        for (int j = index; j < this.n - 1; j++)
        {
            this.elements[j] = source[j + 1];
        }
        this.n--;
        return old;
    }

    /**
     * 查找指定元素的位置,若不存在则返回-1
     *
     * @param value
     * @return
     */
    public int indexOf(T value)
    {
        for (int i = 0; i < this.n; i++)
        {
            if (value.equals(this.elements[i]))
            {
                return i;
            }
        }
        return -1;
    }

    /**
     * 顺序表比较相等 (non-Javadoc)
     */
    @Override
    public boolean equals(Object obj)
    {
        // 同一个顺序表实例
        if (this == obj)
        {
            return true;
        }
        // SeqList<?>是所有SeqList<T>的父类
        if (obj instanceof SeqList<?>)
        {
            SeqList<T> slist = (SeqList<T>) obj;
            if (this.n == slist.n)
            {
                for (int i = 0; i < this.n; i++)
                {
                    if (!this.get(i).equals(slist.get(i)))
                    {
                        return false;
                    }
                    return true;
                }
            }
        }
        return false;
    }
}
