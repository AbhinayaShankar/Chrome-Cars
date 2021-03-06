package com.nostra13.universalimageloader.cache.memory;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public abstract class LimitedMemoryCache<K, V>
  extends BaseMemoryCache<K, V>
{
  private int cacheSize = 0;
  private final List<V> hardCache = Collections.synchronizedList(new LinkedList());
  private final int sizeLimit;
  
  public LimitedMemoryCache(int paramInt)
  {
    this.sizeLimit = paramInt;
  }
  
  public void clear()
  {
    this.hardCache.clear();
    this.cacheSize = 0;
    super.clear();
  }
  
  protected abstract int getSize(V paramV);
  
  protected int getSizeLimit()
  {
    return this.sizeLimit;
  }
  
  public boolean put(K paramK, V paramV)
  {
    boolean bool = false;
    int i = getSize(paramV);
    int j = getSizeLimit();
    if (i < j)
    {
      while (this.cacheSize + i > j)
      {
        Object localObject = removeNext();
        if (this.hardCache.remove(localObject)) {
          this.cacheSize -= getSize(localObject);
        }
      }
      this.hardCache.add(paramV);
      this.cacheSize += i;
      bool = true;
    }
    super.put(paramK, paramV);
    return bool;
  }
  
  public void remove(K paramK)
  {
    Object localObject = super.get(paramK);
    if ((localObject != null) && (this.hardCache.remove(localObject))) {
      this.cacheSize -= getSize(localObject);
    }
    super.remove(paramK);
  }
  
  protected abstract V removeNext();
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\nostra13\universalimageloader\cache\memory\LimitedMemoryCache.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */