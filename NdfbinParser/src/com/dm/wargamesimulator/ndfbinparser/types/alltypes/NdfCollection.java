package com.dm.wargamesimulator.ndfbinparser.types.alltypes;

import com.dm.wargamesimulator.ndfbinparser.types.CollectionItemValueHolder;
import com.dm.wargamesimulator.ndfbinparser.types.NdfType;
import com.dm.wargamesimulator.ndfbinparser.types.NdfbinValueWrapper;

import java.util.*;

/**
 * Created by inv on 20/05/14.
 */
public class NdfCollection extends NdfbinValueWrapper implements List<CollectionItemValueHolder> {

    public List<CollectionItemValueHolder> mList = new ArrayList<CollectionItemValueHolder>();

    public NdfCollection() {
        super(NdfType.LIST);
    }

    @Override
    public int size() {
        return mList.size();
    }

    @Override
    public boolean isEmpty() {
        return mList.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return mList.contains(o);
    }

    @Override
    public Iterator<CollectionItemValueHolder> iterator() {
        return mList.iterator();
    }

    @Override
    public Object[] toArray() {
        return mList.toArray();
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return mList.toArray(a);
    }

    @Override
    public boolean add(CollectionItemValueHolder valueWrapper) {
        return mList.add(valueWrapper);
    }

    @Override
    public boolean remove(Object o) {
        return mList.remove(o);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return mList.containsAll(c);
    }

    @Override
    public boolean addAll(Collection<? extends CollectionItemValueHolder> c) {
        return mList.addAll(c);
    }

    @Override
    public boolean addAll(int index, Collection<? extends CollectionItemValueHolder> c) {
        return mList.addAll(index, c);
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return mList.removeAll(c);
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return mList.removeAll(c);
    }

    @Override
    public void clear() {
        mList.clear();
    }

    @Override
    public CollectionItemValueHolder get(int index) {
        return mList.get(index);
    }

    @Override
    public CollectionItemValueHolder set(int index, CollectionItemValueHolder element) {
        return mList.set(index,element);
    }

    @Override
    public void add(int index, CollectionItemValueHolder element) {
        mList.add(index, element);
    }

    @Override
    public CollectionItemValueHolder remove(int index) {
        return mList.remove(index);
    }

    @Override
    public int indexOf(Object o) {
        return mList.indexOf(o);
    }

    @Override
    public int lastIndexOf(Object o) {
        return mList.lastIndexOf(o);
    }

    @Override
    public ListIterator<CollectionItemValueHolder> listIterator() {
        return mList.listIterator();
    }

    @Override
    public ListIterator<CollectionItemValueHolder> listIterator(int index) {
        return mList.listIterator(index);
    }

    @Override
    public List<CollectionItemValueHolder> subList(int fromIndex, int toIndex) {
        return mList.subList(fromIndex,toIndex);
    }

    public NdfMap getMapItemFromKey(String key){
          for(CollectionItemValueHolder item : mList){
              NdfMap map = (NdfMap)item.value;
              NdfString mapKey = (NdfString) map.key.value;
              if(mapKey.value.toString().equals(key))
                  return map;

          }
        return null;
    }

}
