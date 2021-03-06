package org.kxml2.wap;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.Hashtable;
import java.util.Vector;
import org.xmlpull.v1.XmlSerializer;

public class WbxmlSerializer
  implements XmlSerializer
{
  private int attrPage;
  Hashtable attrStartTable = new Hashtable();
  Hashtable attrValueTable = new Hashtable();
  Vector attributes = new Vector();
  ByteArrayOutputStream buf = new ByteArrayOutputStream();
  int depth;
  private String encoding;
  private boolean headerSent = false;
  String name;
  String namespace;
  OutputStream out;
  String pending;
  Hashtable stringTable = new Hashtable();
  ByteArrayOutputStream stringTableBuf = new ByteArrayOutputStream();
  private int tagPage;
  Hashtable tagTable = new Hashtable();
  
  static void writeInt(OutputStream paramOutputStream, int paramInt)
    throws IOException
  {
    byte[] arrayOfByte = new byte[5];
    int j = 0;
    int i = paramInt;
    for (paramInt = j;; paramInt = j)
    {
      j = paramInt + 1;
      arrayOfByte[paramInt] = ((byte)(i & 0x7F));
      i >>= 7;
      if (i == 0)
      {
        while (j > 1)
        {
          j -= 1;
          paramOutputStream.write(arrayOfByte[j] | 0x80);
        }
        paramOutputStream.write(arrayOfByte[0]);
        return;
      }
    }
  }
  
  private void writeStr(String paramString)
    throws IOException
  {
    int i = 0;
    int k = 0;
    int n = paramString.length();
    if (this.headerSent) {
      writeStrI(this.buf, paramString);
    }
    label159:
    label243:
    do
    {
      return;
      int m = k;
      int j;
      if (j - i > 10)
      {
        if ((i <= k) || (paramString.charAt(i - 1) != ' ') || (this.stringTable.get(paramString.substring(i, j)) != null)) {
          break label159;
        }
        this.buf.write(131);
        writeStrT(paramString.substring(k, j), false);
      }
      for (;;)
      {
        m = j;
        i = j;
        k = m;
        if (i >= n) {
          break label243;
        }
        while ((i < n) && (paramString.charAt(i) < 'A')) {
          i += 1;
        }
        j = i;
        while ((j < n) && (paramString.charAt(j) >= 'A')) {
          j += 1;
        }
        break;
        m = i;
        if (i > k)
        {
          m = i;
          if (paramString.charAt(i - 1) == ' ') {
            m = i - 1;
          }
        }
        if (m > k)
        {
          this.buf.write(131);
          writeStrT(paramString.substring(k, m), false);
        }
        this.buf.write(131);
        writeStrT(paramString.substring(m, j), true);
      }
    } while (k >= n);
    this.buf.write(131);
    writeStrT(paramString.substring(k, n), false);
  }
  
  private final void writeStrT(String paramString, boolean paramBoolean)
    throws IOException
  {
    Integer localInteger = (Integer)this.stringTable.get(paramString);
    ByteArrayOutputStream localByteArrayOutputStream = this.buf;
    if (localInteger == null) {}
    for (int i = addToStringTable(paramString, paramBoolean);; i = localInteger.intValue())
    {
      writeInt(localByteArrayOutputStream, i);
      return;
    }
  }
  
  public int addToStringTable(String paramString, boolean paramBoolean)
    throws IOException
  {
    if (this.headerSent) {
      throw new IOException("stringtable sent");
    }
    int k = this.stringTableBuf.size();
    int j = k;
    int i = j;
    String str = paramString;
    if (paramString.charAt(0) >= '0')
    {
      i = j;
      str = paramString;
      if (paramBoolean)
      {
        str = ' ' + paramString;
        i = j + 1;
      }
    }
    this.stringTable.put(str, new Integer(k));
    if (str.charAt(0) == ' ') {
      this.stringTable.put(str.substring(1), new Integer(k + 1));
    }
    j = str.lastIndexOf(' ');
    if (j > 1)
    {
      this.stringTable.put(str.substring(j), new Integer(k + j));
      this.stringTable.put(str.substring(j + 1), new Integer(k + j + 1));
    }
    writeStrI(this.stringTableBuf, str);
    this.stringTableBuf.flush();
    return i;
  }
  
  public XmlSerializer attribute(String paramString1, String paramString2, String paramString3)
  {
    this.attributes.addElement(paramString2);
    this.attributes.addElement(paramString3);
    return this;
  }
  
  public void cdsect(String paramString)
    throws IOException
  {
    text(paramString);
  }
  
  public void checkPending(boolean paramBoolean)
    throws IOException
  {
    if (this.pending == null) {
      return;
    }
    int j = this.attributes.size();
    Object localObject1 = (int[])this.tagTable.get(this.pending);
    int i;
    label73:
    label130:
    Object localObject2;
    if (localObject1 == null)
    {
      localObject1 = this.buf;
      if (j == 0) {
        if (paramBoolean)
        {
          i = 4;
          ((ByteArrayOutputStream)localObject1).write(i);
          writeStrT(this.pending, false);
          i = 0;
          if (i >= j) {
            break label425;
          }
          localObject1 = (int[])this.attrStartTable.get(this.attributes.elementAt(i));
          if (localObject1 != null) {
            break label321;
          }
          this.buf.write(4);
          writeStrT((String)this.attributes.elementAt(i), false);
          localObject1 = this.attrValueTable;
          localObject2 = this.attributes;
          i += 1;
          localObject1 = (int[])((Hashtable)localObject1).get(((Vector)localObject2).elementAt(i));
          if (localObject1 != null) {
            break label373;
          }
          writeStr((String)this.attributes.elementAt(i));
        }
      }
    }
    for (;;)
    {
      i += 1;
      break label73;
      i = 68;
      break;
      if (paramBoolean)
      {
        i = 132;
        break;
      }
      i = 196;
      break;
      if (localObject1[0] != this.tagPage)
      {
        this.tagPage = localObject1[0];
        this.buf.write(0);
        this.buf.write(this.tagPage);
      }
      localObject2 = this.buf;
      if (j == 0) {
        if (paramBoolean) {
          i = localObject1[1];
        }
      }
      for (;;)
      {
        ((ByteArrayOutputStream)localObject2).write(i);
        break;
        i = localObject1[1] | 0x40;
        continue;
        if (paramBoolean) {
          i = localObject1[1] | 0x80;
        } else {
          i = localObject1[1] | 0xC0;
        }
      }
      label321:
      if (localObject1[0] != this.attrPage)
      {
        this.attrPage = localObject1[0];
        this.buf.write(0);
        this.buf.write(this.attrPage);
      }
      this.buf.write(localObject1[1]);
      break label130;
      label373:
      if (localObject1[0] != this.attrPage)
      {
        this.attrPage = localObject1[0];
        this.buf.write(0);
        this.buf.write(this.attrPage);
      }
      this.buf.write(localObject1[1]);
    }
    label425:
    if (j > 0) {
      this.buf.write(1);
    }
    this.pending = null;
    this.attributes.removeAllElements();
  }
  
  public void comment(String paramString) {}
  
  public void docdecl(String paramString)
  {
    throw new RuntimeException("Cannot write docdecl for WBXML");
  }
  
  public void endDocument()
    throws IOException
  {
    flush();
  }
  
  public XmlSerializer endTag(String paramString1, String paramString2)
    throws IOException
  {
    if (this.pending != null) {
      checkPending(true);
    }
    for (;;)
    {
      this.depth -= 1;
      return this;
      this.buf.write(1);
    }
  }
  
  public void entityRef(String paramString)
  {
    throw new RuntimeException("EntityReference not supported for WBXML");
  }
  
  public void flush()
    throws IOException
  {
    checkPending(false);
    if (!this.headerSent)
    {
      writeInt(this.out, this.stringTableBuf.size());
      this.out.write(this.stringTableBuf.toByteArray());
      this.headerSent = true;
    }
    this.out.write(this.buf.toByteArray());
    this.buf.reset();
  }
  
  public int getDepth()
  {
    return this.depth;
  }
  
  public boolean getFeature(String paramString)
  {
    return false;
  }
  
  public String getName()
  {
    return this.pending;
  }
  
  public String getNamespace()
  {
    return null;
  }
  
  public String getPrefix(String paramString, boolean paramBoolean)
  {
    throw new RuntimeException("NYI");
  }
  
  public Object getProperty(String paramString)
  {
    return null;
  }
  
  public void ignorableWhitespace(String paramString) {}
  
  public void processingInstruction(String paramString)
  {
    throw new RuntimeException("PI NYI");
  }
  
  public void setAttrStartTable(int paramInt, String[] paramArrayOfString)
  {
    int i = 0;
    while (i < paramArrayOfString.length)
    {
      if (paramArrayOfString[i] != null) {
        this.attrStartTable.put(paramArrayOfString[i], new int[] { paramInt, i + 5 });
      }
      i += 1;
    }
  }
  
  public void setAttrValueTable(int paramInt, String[] paramArrayOfString)
  {
    int i = 0;
    while (i < paramArrayOfString.length)
    {
      if (paramArrayOfString[i] != null) {
        this.attrValueTable.put(paramArrayOfString[i], new int[] { paramInt, i + 133 });
      }
      i += 1;
    }
  }
  
  public void setFeature(String paramString, boolean paramBoolean)
  {
    throw new IllegalArgumentException("unknown feature " + paramString);
  }
  
  public void setOutput(OutputStream paramOutputStream, String paramString)
    throws IOException
  {
    String str = paramString;
    if (paramString == null) {
      str = "UTF-8";
    }
    this.encoding = str;
    this.out = paramOutputStream;
    this.buf = new ByteArrayOutputStream();
    this.stringTableBuf = new ByteArrayOutputStream();
    this.headerSent = false;
  }
  
  public void setOutput(Writer paramWriter)
  {
    throw new RuntimeException("Wbxml requires an OutputStream!");
  }
  
  public void setPrefix(String paramString1, String paramString2)
  {
    throw new RuntimeException("NYI");
  }
  
  public void setProperty(String paramString, Object paramObject)
  {
    throw new IllegalArgumentException("unknown property " + paramString);
  }
  
  public void setTagTable(int paramInt, String[] paramArrayOfString)
  {
    int i = 0;
    while (i < paramArrayOfString.length)
    {
      if (paramArrayOfString[i] != null) {
        this.tagTable.put(paramArrayOfString[i], new int[] { paramInt, i + 5 });
      }
      i += 1;
    }
  }
  
  public void startDocument(String paramString, Boolean paramBoolean)
    throws IOException
  {
    this.out.write(3);
    this.out.write(1);
    if (paramString != null) {
      this.encoding = paramString;
    }
    if (this.encoding.toUpperCase().equals("UTF-8"))
    {
      this.out.write(106);
      return;
    }
    if (this.encoding.toUpperCase().equals("ISO-8859-1"))
    {
      this.out.write(4);
      return;
    }
    throw new UnsupportedEncodingException(paramString);
  }
  
  public XmlSerializer startTag(String paramString1, String paramString2)
    throws IOException
  {
    if ((paramString1 != null) && (!"".equals(paramString1))) {
      throw new RuntimeException("NSP NYI");
    }
    checkPending(false);
    this.pending = paramString2;
    this.depth += 1;
    return this;
  }
  
  public XmlSerializer text(String paramString)
    throws IOException
  {
    checkPending(false);
    writeStr(paramString);
    return this;
  }
  
  public XmlSerializer text(char[] paramArrayOfChar, int paramInt1, int paramInt2)
    throws IOException
  {
    checkPending(false);
    writeStr(new String(paramArrayOfChar, paramInt1, paramInt2));
    return this;
  }
  
  void writeStrI(OutputStream paramOutputStream, String paramString)
    throws IOException
  {
    paramOutputStream.write(paramString.getBytes(this.encoding));
    paramOutputStream.write(0);
  }
  
  public void writeWapExtension(int paramInt, Object paramObject)
    throws IOException
  {
    checkPending(false);
    this.buf.write(paramInt);
    switch (paramInt)
    {
    default: 
      throw new IllegalArgumentException();
    case 195: 
      paramObject = (byte[])paramObject;
      writeInt(this.buf, paramObject.length);
      this.buf.write((byte[])paramObject);
    case 192: 
    case 193: 
    case 194: 
      return;
    case 64: 
    case 65: 
    case 66: 
      writeStrI(this.buf, (String)paramObject);
      return;
    }
    writeStrT((String)paramObject, false);
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\org\kxml2\wap\WbxmlSerializer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */