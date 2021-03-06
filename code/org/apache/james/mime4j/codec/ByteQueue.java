package org.apache.james.mime4j.codec;

import java.util.Iterator;

public class ByteQueue
  implements Iterable<Byte>
{
  private UnboundedFifoByteBuffer buf;
  private int initialCapacity = -1;
  
  public ByteQueue()
  {
    this.buf = new UnboundedFifoByteBuffer();
  }
  
  public ByteQueue(int paramInt)
  {
    this.buf = new UnboundedFifoByteBuffer(paramInt);
    this.initialCapacity = paramInt;
  }
  
  public void clear()
  {
    if (this.initialCapacity != -1)
    {
      this.buf = new UnboundedFifoByteBuffer(this.initialCapacity);
      return;
    }
    this.buf = new UnboundedFifoByteBuffer();
  }
  
  public int count()
  {
    return this.buf.size();
  }
  
  public byte dequeue()
  {
    return this.buf.remove();
  }
  
  public void enqueue(byte paramByte)
  {
    this.buf.add(paramByte);
  }
  
  public Iterator<Byte> iterator()
  {
    return this.buf.iterator();
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\org\apache\james\mime4j\codec\ByteQueue.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */