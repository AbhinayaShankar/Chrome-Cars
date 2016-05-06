package org.apache.commons.lang.text;

import java.io.Reader;
import java.io.Writer;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.SystemUtils;

public class StrBuilder
  implements Cloneable
{
  static final int CAPACITY = 32;
  private static final long serialVersionUID = 7628716375283629643L;
  protected char[] buffer;
  private String newLine;
  private String nullText;
  protected int size;
  
  public StrBuilder()
  {
    this(32);
  }
  
  public StrBuilder(int paramInt)
  {
    int i = paramInt;
    if (paramInt <= 0) {
      i = 32;
    }
    this.buffer = new char[i];
  }
  
  public StrBuilder(String paramString)
  {
    if (paramString == null)
    {
      this.buffer = new char[32];
      return;
    }
    this.buffer = new char[paramString.length() + 32];
    append(paramString);
  }
  
  private void deleteImpl(int paramInt1, int paramInt2, int paramInt3)
  {
    System.arraycopy(this.buffer, paramInt2, this.buffer, paramInt1, this.size - paramInt2);
    this.size -= paramInt3;
  }
  
  private StrBuilder replaceImpl(StrMatcher paramStrMatcher, String paramString, int paramInt1, int paramInt2, int paramInt3)
  {
    if ((paramStrMatcher == null) || (this.size == 0)) {
      return this;
    }
    if (paramString == null) {}
    for (int i = 0;; i = paramString.length())
    {
      char[] arrayOfChar = this.buffer;
      int j = paramInt1;
      int n = paramInt2;
      paramInt2 = j;
      while ((paramInt2 < n) && (paramInt3 != 0))
      {
        int i1 = paramStrMatcher.isMatch(arrayOfChar, paramInt2, paramInt1, n);
        int m = paramInt2;
        j = n;
        int k = paramInt3;
        if (i1 > 0)
        {
          replaceImpl(paramInt2, paramInt2 + i1, i1, paramString, i);
          n = n - i1 + i;
          paramInt2 = paramInt2 + i - 1;
          m = paramInt2;
          j = n;
          k = paramInt3;
          if (paramInt3 > 0)
          {
            k = paramInt3 - 1;
            j = n;
            m = paramInt2;
          }
        }
        paramInt2 = m + 1;
        n = j;
        paramInt3 = k;
      }
      break;
    }
  }
  
  private void replaceImpl(int paramInt1, int paramInt2, int paramInt3, String paramString, int paramInt4)
  {
    int i = this.size - paramInt3 + paramInt4;
    if (paramInt4 != paramInt3)
    {
      ensureCapacity(i);
      System.arraycopy(this.buffer, paramInt2, this.buffer, paramInt1 + paramInt4, this.size - paramInt2);
      this.size = i;
    }
    if (paramInt4 > 0) {
      paramString.getChars(0, paramInt4, this.buffer, paramInt1);
    }
  }
  
  public StrBuilder append(char paramChar)
  {
    ensureCapacity(length() + 1);
    char[] arrayOfChar = this.buffer;
    int i = this.size;
    this.size = (i + 1);
    arrayOfChar[i] = paramChar;
    return this;
  }
  
  public StrBuilder append(double paramDouble)
  {
    return append(String.valueOf(paramDouble));
  }
  
  public StrBuilder append(float paramFloat)
  {
    return append(String.valueOf(paramFloat));
  }
  
  public StrBuilder append(int paramInt)
  {
    return append(String.valueOf(paramInt));
  }
  
  public StrBuilder append(long paramLong)
  {
    return append(String.valueOf(paramLong));
  }
  
  public StrBuilder append(Object paramObject)
  {
    if (paramObject == null) {
      return appendNull();
    }
    return append(paramObject.toString());
  }
  
  public StrBuilder append(String paramString)
  {
    StrBuilder localStrBuilder;
    if (paramString == null) {
      localStrBuilder = appendNull();
    }
    int i;
    do
    {
      return localStrBuilder;
      i = paramString.length();
      localStrBuilder = this;
    } while (i <= 0);
    int j = length();
    ensureCapacity(j + i);
    paramString.getChars(0, i, this.buffer, j);
    this.size += i;
    return this;
  }
  
  public StrBuilder append(String paramString, int paramInt1, int paramInt2)
  {
    StrBuilder localStrBuilder;
    if (paramString == null) {
      localStrBuilder = appendNull();
    }
    do
    {
      return localStrBuilder;
      if ((paramInt1 < 0) || (paramInt1 > paramString.length())) {
        throw new StringIndexOutOfBoundsException("startIndex must be valid");
      }
      if ((paramInt2 < 0) || (paramInt1 + paramInt2 > paramString.length())) {
        throw new StringIndexOutOfBoundsException("length must be valid");
      }
      localStrBuilder = this;
    } while (paramInt2 <= 0);
    int i = length();
    ensureCapacity(i + paramInt2);
    paramString.getChars(paramInt1, paramInt1 + paramInt2, this.buffer, i);
    this.size += paramInt2;
    return this;
  }
  
  public StrBuilder append(StringBuffer paramStringBuffer)
  {
    StrBuilder localStrBuilder;
    if (paramStringBuffer == null) {
      localStrBuilder = appendNull();
    }
    int i;
    do
    {
      return localStrBuilder;
      i = paramStringBuffer.length();
      localStrBuilder = this;
    } while (i <= 0);
    int j = length();
    ensureCapacity(j + i);
    paramStringBuffer.getChars(0, i, this.buffer, j);
    this.size += i;
    return this;
  }
  
  public StrBuilder append(StringBuffer paramStringBuffer, int paramInt1, int paramInt2)
  {
    StrBuilder localStrBuilder;
    if (paramStringBuffer == null) {
      localStrBuilder = appendNull();
    }
    do
    {
      return localStrBuilder;
      if ((paramInt1 < 0) || (paramInt1 > paramStringBuffer.length())) {
        throw new StringIndexOutOfBoundsException("startIndex must be valid");
      }
      if ((paramInt2 < 0) || (paramInt1 + paramInt2 > paramStringBuffer.length())) {
        throw new StringIndexOutOfBoundsException("length must be valid");
      }
      localStrBuilder = this;
    } while (paramInt2 <= 0);
    int i = length();
    ensureCapacity(i + paramInt2);
    paramStringBuffer.getChars(paramInt1, paramInt1 + paramInt2, this.buffer, i);
    this.size += paramInt2;
    return this;
  }
  
  public StrBuilder append(StrBuilder paramStrBuilder)
  {
    StrBuilder localStrBuilder;
    if (paramStrBuilder == null) {
      localStrBuilder = appendNull();
    }
    int i;
    do
    {
      return localStrBuilder;
      i = paramStrBuilder.length();
      localStrBuilder = this;
    } while (i <= 0);
    int j = length();
    ensureCapacity(j + i);
    System.arraycopy(paramStrBuilder.buffer, 0, this.buffer, j, i);
    this.size += i;
    return this;
  }
  
  public StrBuilder append(StrBuilder paramStrBuilder, int paramInt1, int paramInt2)
  {
    StrBuilder localStrBuilder;
    if (paramStrBuilder == null) {
      localStrBuilder = appendNull();
    }
    do
    {
      return localStrBuilder;
      if ((paramInt1 < 0) || (paramInt1 > paramStrBuilder.length())) {
        throw new StringIndexOutOfBoundsException("startIndex must be valid");
      }
      if ((paramInt2 < 0) || (paramInt1 + paramInt2 > paramStrBuilder.length())) {
        throw new StringIndexOutOfBoundsException("length must be valid");
      }
      localStrBuilder = this;
    } while (paramInt2 <= 0);
    int i = length();
    ensureCapacity(i + paramInt2);
    paramStrBuilder.getChars(paramInt1, paramInt1 + paramInt2, this.buffer, i);
    this.size += paramInt2;
    return this;
  }
  
  public StrBuilder append(boolean paramBoolean)
  {
    if (paramBoolean)
    {
      ensureCapacity(this.size + 4);
      arrayOfChar = this.buffer;
      i = this.size;
      this.size = (i + 1);
      arrayOfChar[i] = 't';
      arrayOfChar = this.buffer;
      i = this.size;
      this.size = (i + 1);
      arrayOfChar[i] = 'r';
      arrayOfChar = this.buffer;
      i = this.size;
      this.size = (i + 1);
      arrayOfChar[i] = 'u';
      arrayOfChar = this.buffer;
      i = this.size;
      this.size = (i + 1);
      arrayOfChar[i] = 'e';
      return this;
    }
    ensureCapacity(this.size + 5);
    char[] arrayOfChar = this.buffer;
    int i = this.size;
    this.size = (i + 1);
    arrayOfChar[i] = 'f';
    arrayOfChar = this.buffer;
    i = this.size;
    this.size = (i + 1);
    arrayOfChar[i] = 'a';
    arrayOfChar = this.buffer;
    i = this.size;
    this.size = (i + 1);
    arrayOfChar[i] = 'l';
    arrayOfChar = this.buffer;
    i = this.size;
    this.size = (i + 1);
    arrayOfChar[i] = 's';
    arrayOfChar = this.buffer;
    i = this.size;
    this.size = (i + 1);
    arrayOfChar[i] = 'e';
    return this;
  }
  
  public StrBuilder append(char[] paramArrayOfChar)
  {
    StrBuilder localStrBuilder;
    if (paramArrayOfChar == null) {
      localStrBuilder = appendNull();
    }
    int i;
    do
    {
      return localStrBuilder;
      i = paramArrayOfChar.length;
      localStrBuilder = this;
    } while (i <= 0);
    int j = length();
    ensureCapacity(j + i);
    System.arraycopy(paramArrayOfChar, 0, this.buffer, j, i);
    this.size += i;
    return this;
  }
  
  public StrBuilder append(char[] paramArrayOfChar, int paramInt1, int paramInt2)
  {
    StrBuilder localStrBuilder;
    if (paramArrayOfChar == null) {
      localStrBuilder = appendNull();
    }
    do
    {
      return localStrBuilder;
      if ((paramInt1 < 0) || (paramInt1 > paramArrayOfChar.length)) {
        throw new StringIndexOutOfBoundsException("Invalid startIndex: " + paramInt2);
      }
      if ((paramInt2 < 0) || (paramInt1 + paramInt2 > paramArrayOfChar.length)) {
        throw new StringIndexOutOfBoundsException("Invalid length: " + paramInt2);
      }
      localStrBuilder = this;
    } while (paramInt2 <= 0);
    int i = length();
    ensureCapacity(i + paramInt2);
    System.arraycopy(paramArrayOfChar, paramInt1, this.buffer, i, paramInt2);
    this.size += paramInt2;
    return this;
  }
  
  public StrBuilder appendAll(Collection paramCollection)
  {
    if ((paramCollection != null) && (paramCollection.size() > 0))
    {
      paramCollection = paramCollection.iterator();
      while (paramCollection.hasNext()) {
        append(paramCollection.next());
      }
    }
    return this;
  }
  
  public StrBuilder appendAll(Iterator paramIterator)
  {
    if (paramIterator != null) {
      while (paramIterator.hasNext()) {
        append(paramIterator.next());
      }
    }
    return this;
  }
  
  public StrBuilder appendAll(Object[] paramArrayOfObject)
  {
    if ((paramArrayOfObject != null) && (paramArrayOfObject.length > 0))
    {
      int i = 0;
      while (i < paramArrayOfObject.length)
      {
        append(paramArrayOfObject[i]);
        i += 1;
      }
    }
    return this;
  }
  
  public StrBuilder appendFixedWidthPadLeft(int paramInt1, int paramInt2, char paramChar)
  {
    return appendFixedWidthPadLeft(String.valueOf(paramInt1), paramInt2, paramChar);
  }
  
  public StrBuilder appendFixedWidthPadLeft(Object paramObject, int paramInt, char paramChar)
  {
    Object localObject;
    int j;
    if (paramInt > 0)
    {
      ensureCapacity(this.size + paramInt);
      if (paramObject != null) {
        break label79;
      }
      paramObject = getNullText();
      localObject = paramObject;
      if (paramObject == null) {
        localObject = "";
      }
      j = ((String)localObject).length();
      if (j < paramInt) {
        break label87;
      }
      ((String)localObject).getChars(j - paramInt, j, this.buffer, this.size);
    }
    for (;;)
    {
      this.size += paramInt;
      return this;
      label79:
      paramObject = paramObject.toString();
      break;
      label87:
      int k = paramInt - j;
      int i = 0;
      while (i < k)
      {
        this.buffer[(this.size + i)] = paramChar;
        i += 1;
      }
      ((String)localObject).getChars(0, j, this.buffer, this.size + k);
    }
  }
  
  public StrBuilder appendFixedWidthPadRight(int paramInt1, int paramInt2, char paramChar)
  {
    return appendFixedWidthPadRight(String.valueOf(paramInt1), paramInt2, paramChar);
  }
  
  public StrBuilder appendFixedWidthPadRight(Object paramObject, int paramInt, char paramChar)
  {
    Object localObject;
    int j;
    if (paramInt > 0)
    {
      ensureCapacity(this.size + paramInt);
      if (paramObject != null) {
        break label75;
      }
      paramObject = getNullText();
      localObject = paramObject;
      if (paramObject == null) {
        localObject = "";
      }
      j = ((String)localObject).length();
      if (j < paramInt) {
        break label83;
      }
      ((String)localObject).getChars(0, paramInt, this.buffer, this.size);
    }
    for (;;)
    {
      this.size += paramInt;
      return this;
      label75:
      paramObject = paramObject.toString();
      break;
      label83:
      ((String)localObject).getChars(0, j, this.buffer, this.size);
      int i = 0;
      while (i < paramInt - j)
      {
        this.buffer[(this.size + j + i)] = paramChar;
        i += 1;
      }
    }
  }
  
  public StrBuilder appendNewLine()
  {
    if (this.newLine == null)
    {
      append(SystemUtils.LINE_SEPARATOR);
      return this;
    }
    return append(this.newLine);
  }
  
  public StrBuilder appendNull()
  {
    if (this.nullText == null) {
      return this;
    }
    return append(this.nullText);
  }
  
  public StrBuilder appendPadding(int paramInt, char paramChar)
  {
    if (paramInt >= 0)
    {
      ensureCapacity(this.size + paramInt);
      int i = 0;
      while (i < paramInt)
      {
        char[] arrayOfChar = this.buffer;
        int j = this.size;
        this.size = (j + 1);
        arrayOfChar[j] = paramChar;
        i += 1;
      }
    }
    return this;
  }
  
  public StrBuilder appendSeparator(char paramChar)
  {
    if (size() > 0) {
      append(paramChar);
    }
    return this;
  }
  
  public StrBuilder appendSeparator(char paramChar1, char paramChar2)
  {
    if (size() > 0)
    {
      append(paramChar1);
      return this;
    }
    append(paramChar2);
    return this;
  }
  
  public StrBuilder appendSeparator(char paramChar, int paramInt)
  {
    if (paramInt > 0) {
      append(paramChar);
    }
    return this;
  }
  
  public StrBuilder appendSeparator(String paramString)
  {
    return appendSeparator(paramString, null);
  }
  
  public StrBuilder appendSeparator(String paramString, int paramInt)
  {
    if ((paramString != null) && (paramInt > 0)) {
      append(paramString);
    }
    return this;
  }
  
  public StrBuilder appendSeparator(String paramString1, String paramString2)
  {
    if (isEmpty()) {}
    for (;;)
    {
      if (paramString2 != null) {
        append(paramString2);
      }
      return this;
      paramString2 = paramString1;
    }
  }
  
  public StrBuilder appendWithSeparators(Collection paramCollection, String paramString)
  {
    if ((paramCollection != null) && (paramCollection.size() > 0))
    {
      String str = paramString;
      if (paramString == null) {
        str = "";
      }
      paramCollection = paramCollection.iterator();
      while (paramCollection.hasNext())
      {
        append(paramCollection.next());
        if (paramCollection.hasNext()) {
          append(str);
        }
      }
    }
    return this;
  }
  
  public StrBuilder appendWithSeparators(Iterator paramIterator, String paramString)
  {
    if (paramIterator != null)
    {
      String str = paramString;
      if (paramString == null) {
        str = "";
      }
      while (paramIterator.hasNext())
      {
        append(paramIterator.next());
        if (paramIterator.hasNext()) {
          append(str);
        }
      }
    }
    return this;
  }
  
  public StrBuilder appendWithSeparators(Object[] paramArrayOfObject, String paramString)
  {
    if ((paramArrayOfObject != null) && (paramArrayOfObject.length > 0))
    {
      String str = paramString;
      if (paramString == null) {
        str = "";
      }
      append(paramArrayOfObject[0]);
      int i = 1;
      while (i < paramArrayOfObject.length)
      {
        append(str);
        append(paramArrayOfObject[i]);
        i += 1;
      }
    }
    return this;
  }
  
  public StrBuilder appendln(char paramChar)
  {
    return append(paramChar).appendNewLine();
  }
  
  public StrBuilder appendln(double paramDouble)
  {
    return append(paramDouble).appendNewLine();
  }
  
  public StrBuilder appendln(float paramFloat)
  {
    return append(paramFloat).appendNewLine();
  }
  
  public StrBuilder appendln(int paramInt)
  {
    return append(paramInt).appendNewLine();
  }
  
  public StrBuilder appendln(long paramLong)
  {
    return append(paramLong).appendNewLine();
  }
  
  public StrBuilder appendln(Object paramObject)
  {
    return append(paramObject).appendNewLine();
  }
  
  public StrBuilder appendln(String paramString)
  {
    return append(paramString).appendNewLine();
  }
  
  public StrBuilder appendln(String paramString, int paramInt1, int paramInt2)
  {
    return append(paramString, paramInt1, paramInt2).appendNewLine();
  }
  
  public StrBuilder appendln(StringBuffer paramStringBuffer)
  {
    return append(paramStringBuffer).appendNewLine();
  }
  
  public StrBuilder appendln(StringBuffer paramStringBuffer, int paramInt1, int paramInt2)
  {
    return append(paramStringBuffer, paramInt1, paramInt2).appendNewLine();
  }
  
  public StrBuilder appendln(StrBuilder paramStrBuilder)
  {
    return append(paramStrBuilder).appendNewLine();
  }
  
  public StrBuilder appendln(StrBuilder paramStrBuilder, int paramInt1, int paramInt2)
  {
    return append(paramStrBuilder, paramInt1, paramInt2).appendNewLine();
  }
  
  public StrBuilder appendln(boolean paramBoolean)
  {
    return append(paramBoolean).appendNewLine();
  }
  
  public StrBuilder appendln(char[] paramArrayOfChar)
  {
    return append(paramArrayOfChar).appendNewLine();
  }
  
  public StrBuilder appendln(char[] paramArrayOfChar, int paramInt1, int paramInt2)
  {
    return append(paramArrayOfChar, paramInt1, paramInt2).appendNewLine();
  }
  
  public Reader asReader()
  {
    return new StrBuilderReader();
  }
  
  public StrTokenizer asTokenizer()
  {
    return new StrBuilderTokenizer();
  }
  
  public Writer asWriter()
  {
    return new StrBuilderWriter();
  }
  
  public int capacity()
  {
    return this.buffer.length;
  }
  
  public char charAt(int paramInt)
  {
    if ((paramInt < 0) || (paramInt >= length())) {
      throw new StringIndexOutOfBoundsException(paramInt);
    }
    return this.buffer[paramInt];
  }
  
  public StrBuilder clear()
  {
    this.size = 0;
    return this;
  }
  
  public Object clone()
    throws CloneNotSupportedException
  {
    StrBuilder localStrBuilder = (StrBuilder)super.clone();
    localStrBuilder.buffer = new char[this.buffer.length];
    System.arraycopy(this.buffer, 0, localStrBuilder.buffer, 0, this.buffer.length);
    return localStrBuilder;
  }
  
  public boolean contains(char paramChar)
  {
    char[] arrayOfChar = this.buffer;
    int i = 0;
    while (i < this.size)
    {
      if (arrayOfChar[i] == paramChar) {
        return true;
      }
      i += 1;
    }
    return false;
  }
  
  public boolean contains(String paramString)
  {
    boolean bool = false;
    if (indexOf(paramString, 0) >= 0) {
      bool = true;
    }
    return bool;
  }
  
  public boolean contains(StrMatcher paramStrMatcher)
  {
    boolean bool = false;
    if (indexOf(paramStrMatcher, 0) >= 0) {
      bool = true;
    }
    return bool;
  }
  
  public StrBuilder delete(int paramInt1, int paramInt2)
  {
    paramInt2 = validateRange(paramInt1, paramInt2);
    int i = paramInt2 - paramInt1;
    if (i > 0) {
      deleteImpl(paramInt1, paramInt2, i);
    }
    return this;
  }
  
  public StrBuilder deleteAll(char paramChar)
  {
    int j;
    for (int i = 0; i < this.size; i = j + 1)
    {
      j = i;
      if (this.buffer[i] == paramChar)
      {
        j = i;
        int k;
        do
        {
          k = j + 1;
          if (k >= this.size) {
            break;
          }
          j = k;
        } while (this.buffer[k] == paramChar);
        j = k - i;
        deleteImpl(i, k, j);
        j = k - j;
      }
    }
    return this;
  }
  
  public StrBuilder deleteAll(String paramString)
  {
    if (paramString == null) {}
    for (int i = 0; i > 0; i = paramString.length()) {
      for (int j = indexOf(paramString, 0); j >= 0; j = indexOf(paramString, j)) {
        deleteImpl(j, j + i, i);
      }
    }
    return this;
  }
  
  public StrBuilder deleteAll(StrMatcher paramStrMatcher)
  {
    return replace(paramStrMatcher, null, 0, this.size, -1);
  }
  
  public StrBuilder deleteCharAt(int paramInt)
  {
    if ((paramInt < 0) || (paramInt >= this.size)) {
      throw new StringIndexOutOfBoundsException(paramInt);
    }
    deleteImpl(paramInt, paramInt + 1, 1);
    return this;
  }
  
  public StrBuilder deleteFirst(char paramChar)
  {
    int i = 0;
    for (;;)
    {
      if (i < this.size)
      {
        if (this.buffer[i] == paramChar) {
          deleteImpl(i, i + 1, 1);
        }
      }
      else {
        return this;
      }
      i += 1;
    }
  }
  
  public StrBuilder deleteFirst(String paramString)
  {
    if (paramString == null) {}
    for (int i = 0;; i = paramString.length())
    {
      if (i > 0)
      {
        int j = indexOf(paramString, 0);
        if (j >= 0) {
          deleteImpl(j, j + i, i);
        }
      }
      return this;
    }
  }
  
  public StrBuilder deleteFirst(StrMatcher paramStrMatcher)
  {
    return replace(paramStrMatcher, null, 0, this.size, 1);
  }
  
  public boolean endsWith(String paramString)
  {
    if (paramString == null) {}
    int k;
    do
    {
      return false;
      k = paramString.length();
      if (k == 0) {
        return true;
      }
    } while (k > this.size);
    int i = this.size - k;
    int j = 0;
    for (;;)
    {
      if (j >= k) {
        break label69;
      }
      if (this.buffer[i] != paramString.charAt(j)) {
        break;
      }
      j += 1;
      i += 1;
    }
    label69:
    return true;
  }
  
  public StrBuilder ensureCapacity(int paramInt)
  {
    if (paramInt > this.buffer.length)
    {
      char[] arrayOfChar = this.buffer;
      this.buffer = new char[paramInt * 2];
      System.arraycopy(arrayOfChar, 0, this.buffer, 0, this.size);
    }
    return this;
  }
  
  public boolean equals(Object paramObject)
  {
    if ((paramObject instanceof StrBuilder)) {
      return equals((StrBuilder)paramObject);
    }
    return false;
  }
  
  public boolean equals(StrBuilder paramStrBuilder)
  {
    if (this == paramStrBuilder) {}
    for (;;)
    {
      return true;
      if (this.size != paramStrBuilder.size) {
        return false;
      }
      char[] arrayOfChar = this.buffer;
      paramStrBuilder = paramStrBuilder.buffer;
      int i = this.size - 1;
      while (i >= 0)
      {
        if (arrayOfChar[i] != paramStrBuilder[i]) {
          return false;
        }
        i -= 1;
      }
    }
  }
  
  public boolean equalsIgnoreCase(StrBuilder paramStrBuilder)
  {
    if (this == paramStrBuilder) {}
    for (;;)
    {
      return true;
      if (this.size != paramStrBuilder.size) {
        return false;
      }
      char[] arrayOfChar = this.buffer;
      paramStrBuilder = paramStrBuilder.buffer;
      int i = this.size - 1;
      while (i >= 0)
      {
        char c1 = arrayOfChar[i];
        char c2 = paramStrBuilder[i];
        if ((c1 != c2) && (Character.toUpperCase(c1) != Character.toUpperCase(c2))) {
          return false;
        }
        i -= 1;
      }
    }
  }
  
  public void getChars(int paramInt1, int paramInt2, char[] paramArrayOfChar, int paramInt3)
  {
    if (paramInt1 < 0) {
      throw new StringIndexOutOfBoundsException(paramInt1);
    }
    if ((paramInt2 < 0) || (paramInt2 > length())) {
      throw new StringIndexOutOfBoundsException(paramInt2);
    }
    if (paramInt1 > paramInt2) {
      throw new StringIndexOutOfBoundsException("end < start");
    }
    System.arraycopy(this.buffer, paramInt1, paramArrayOfChar, paramInt3, paramInt2 - paramInt1);
  }
  
  public char[] getChars(char[] paramArrayOfChar)
  {
    int i = length();
    char[] arrayOfChar;
    if (paramArrayOfChar != null)
    {
      arrayOfChar = paramArrayOfChar;
      if (paramArrayOfChar.length >= i) {}
    }
    else
    {
      arrayOfChar = new char[i];
    }
    System.arraycopy(this.buffer, 0, arrayOfChar, 0, i);
    return arrayOfChar;
  }
  
  public String getNewLineText()
  {
    return this.newLine;
  }
  
  public String getNullText()
  {
    return this.nullText;
  }
  
  public int hashCode()
  {
    char[] arrayOfChar = this.buffer;
    int j = 0;
    int i = this.size - 1;
    while (i >= 0)
    {
      j = j * 31 + arrayOfChar[i];
      i -= 1;
    }
    return j;
  }
  
  public int indexOf(char paramChar)
  {
    return indexOf(paramChar, 0);
  }
  
  public int indexOf(char paramChar, int paramInt)
  {
    int i = paramInt;
    if (paramInt < 0) {
      i = 0;
    }
    if (i >= this.size)
    {
      i = -1;
      return i;
    }
    char[] arrayOfChar = this.buffer;
    paramInt = i;
    for (;;)
    {
      if (paramInt >= this.size) {
        break label53;
      }
      i = paramInt;
      if (arrayOfChar[paramInt] == paramChar) {
        break;
      }
      paramInt += 1;
    }
    label53:
    return -1;
  }
  
  public int indexOf(String paramString)
  {
    return indexOf(paramString, 0);
  }
  
  public int indexOf(String paramString, int paramInt)
  {
    int i = paramInt;
    if (paramInt < 0) {
      i = 0;
    }
    if ((paramString == null) || (i >= this.size)) {
      paramInt = -1;
    }
    int j;
    do
    {
      return paramInt;
      j = paramString.length();
      if (j == 1) {
        return indexOf(paramString.charAt(0), i);
      }
      paramInt = i;
    } while (j == 0);
    if (j > this.size) {
      return -1;
    }
    char[] arrayOfChar = this.buffer;
    int k = this.size;
    paramInt = i;
    if (paramInt < k - j + 1)
    {
      i = 0;
      for (;;)
      {
        if (i >= j) {
          break label126;
        }
        if (paramString.charAt(i) != arrayOfChar[(paramInt + i)])
        {
          paramInt += 1;
          break;
        }
        i += 1;
      }
      label126:
      return paramInt;
    }
    return -1;
  }
  
  public int indexOf(StrMatcher paramStrMatcher)
  {
    return indexOf(paramStrMatcher, 0);
  }
  
  public int indexOf(StrMatcher paramStrMatcher, int paramInt)
  {
    int i = paramInt;
    if (paramInt < 0) {
      i = 0;
    }
    int j;
    if ((paramStrMatcher == null) || (i >= this.size))
    {
      j = -1;
      return j;
    }
    int k = this.size;
    char[] arrayOfChar = this.buffer;
    paramInt = i;
    for (;;)
    {
      if (paramInt >= k) {
        break label69;
      }
      j = paramInt;
      if (paramStrMatcher.isMatch(arrayOfChar, paramInt, i, k) > 0) {
        break;
      }
      paramInt += 1;
    }
    label69:
    return -1;
  }
  
  public StrBuilder insert(int paramInt, char paramChar)
  {
    validateIndex(paramInt);
    ensureCapacity(this.size + 1);
    System.arraycopy(this.buffer, paramInt, this.buffer, paramInt + 1, this.size - paramInt);
    this.buffer[paramInt] = paramChar;
    this.size += 1;
    return this;
  }
  
  public StrBuilder insert(int paramInt, double paramDouble)
  {
    return insert(paramInt, String.valueOf(paramDouble));
  }
  
  public StrBuilder insert(int paramInt, float paramFloat)
  {
    return insert(paramInt, String.valueOf(paramFloat));
  }
  
  public StrBuilder insert(int paramInt1, int paramInt2)
  {
    return insert(paramInt1, String.valueOf(paramInt2));
  }
  
  public StrBuilder insert(int paramInt, long paramLong)
  {
    return insert(paramInt, String.valueOf(paramLong));
  }
  
  public StrBuilder insert(int paramInt, Object paramObject)
  {
    if (paramObject == null) {
      return insert(paramInt, this.nullText);
    }
    return insert(paramInt, paramObject.toString());
  }
  
  public StrBuilder insert(int paramInt, String paramString)
  {
    validateIndex(paramInt);
    String str = paramString;
    if (paramString == null) {
      str = this.nullText;
    }
    if (str == null) {}
    for (int i = 0;; i = str.length())
    {
      if (i > 0)
      {
        int j = this.size + i;
        ensureCapacity(j);
        System.arraycopy(this.buffer, paramInt, this.buffer, paramInt + i, this.size - paramInt);
        this.size = j;
        str.getChars(0, i, this.buffer, paramInt);
      }
      return this;
    }
  }
  
  public StrBuilder insert(int paramInt, boolean paramBoolean)
  {
    validateIndex(paramInt);
    if (paramBoolean)
    {
      ensureCapacity(this.size + 4);
      System.arraycopy(this.buffer, paramInt, this.buffer, paramInt + 4, this.size - paramInt);
      arrayOfChar = this.buffer;
      i = paramInt + 1;
      arrayOfChar[paramInt] = 't';
      arrayOfChar = this.buffer;
      paramInt = i + 1;
      arrayOfChar[i] = 'r';
      this.buffer[paramInt] = 'u';
      this.buffer[(paramInt + 1)] = 'e';
      this.size += 4;
      return this;
    }
    ensureCapacity(this.size + 5);
    System.arraycopy(this.buffer, paramInt, this.buffer, paramInt + 5, this.size - paramInt);
    char[] arrayOfChar = this.buffer;
    int i = paramInt + 1;
    arrayOfChar[paramInt] = 'f';
    arrayOfChar = this.buffer;
    paramInt = i + 1;
    arrayOfChar[i] = 'a';
    arrayOfChar = this.buffer;
    i = paramInt + 1;
    arrayOfChar[paramInt] = 'l';
    this.buffer[i] = 's';
    this.buffer[(i + 1)] = 'e';
    this.size += 5;
    return this;
  }
  
  public StrBuilder insert(int paramInt, char[] paramArrayOfChar)
  {
    validateIndex(paramInt);
    StrBuilder localStrBuilder;
    if (paramArrayOfChar == null) {
      localStrBuilder = insert(paramInt, this.nullText);
    }
    int i;
    do
    {
      return localStrBuilder;
      i = paramArrayOfChar.length;
      localStrBuilder = this;
    } while (i <= 0);
    ensureCapacity(this.size + i);
    System.arraycopy(this.buffer, paramInt, this.buffer, paramInt + i, this.size - paramInt);
    System.arraycopy(paramArrayOfChar, 0, this.buffer, paramInt, i);
    this.size += i;
    return this;
  }
  
  public StrBuilder insert(int paramInt1, char[] paramArrayOfChar, int paramInt2, int paramInt3)
  {
    validateIndex(paramInt1);
    StrBuilder localStrBuilder;
    if (paramArrayOfChar == null) {
      localStrBuilder = insert(paramInt1, this.nullText);
    }
    do
    {
      return localStrBuilder;
      if ((paramInt2 < 0) || (paramInt2 > paramArrayOfChar.length)) {
        throw new StringIndexOutOfBoundsException("Invalid offset: " + paramInt2);
      }
      if ((paramInt3 < 0) || (paramInt2 + paramInt3 > paramArrayOfChar.length)) {
        throw new StringIndexOutOfBoundsException("Invalid length: " + paramInt3);
      }
      localStrBuilder = this;
    } while (paramInt3 <= 0);
    ensureCapacity(this.size + paramInt3);
    System.arraycopy(this.buffer, paramInt1, this.buffer, paramInt1 + paramInt3, this.size - paramInt1);
    System.arraycopy(paramArrayOfChar, paramInt2, this.buffer, paramInt1, paramInt3);
    this.size += paramInt3;
    return this;
  }
  
  public boolean isEmpty()
  {
    return this.size == 0;
  }
  
  public int lastIndexOf(char paramChar)
  {
    return lastIndexOf(paramChar, this.size - 1);
  }
  
  public int lastIndexOf(char paramChar, int paramInt)
  {
    int i = paramInt;
    if (paramInt >= this.size) {
      i = this.size - 1;
    }
    if (i < 0)
    {
      i = -1;
      return i;
    }
    paramInt = i;
    for (;;)
    {
      if (paramInt < 0) {
        break label50;
      }
      i = paramInt;
      if (this.buffer[paramInt] == paramChar) {
        break;
      }
      paramInt -= 1;
    }
    label50:
    return -1;
  }
  
  public int lastIndexOf(String paramString)
  {
    return lastIndexOf(paramString, this.size - 1);
  }
  
  public int lastIndexOf(String paramString, int paramInt)
  {
    int i = paramInt;
    if (paramInt >= this.size) {
      i = this.size - 1;
    }
    if ((paramString == null) || (i < 0))
    {
      i = -1;
      return i;
    }
    int k = paramString.length();
    if ((k > 0) && (k <= this.size))
    {
      if (k == 1) {
        return lastIndexOf(paramString.charAt(0), i);
      }
      paramInt = i - k + 1;
      label73:
      if (paramInt >= 0)
      {
        int j = 0;
        for (;;)
        {
          i = paramInt;
          if (j >= k) {
            break;
          }
          if (paramString.charAt(j) != this.buffer[(paramInt + j)])
          {
            paramInt -= 1;
            break label73;
          }
          j += 1;
        }
      }
    }
    else if (k == 0)
    {
      return i;
    }
    return -1;
  }
  
  public int lastIndexOf(StrMatcher paramStrMatcher)
  {
    return lastIndexOf(paramStrMatcher, this.size);
  }
  
  public int lastIndexOf(StrMatcher paramStrMatcher, int paramInt)
  {
    int i = paramInt;
    if (paramInt >= this.size) {
      i = this.size - 1;
    }
    int j;
    if ((paramStrMatcher == null) || (i < 0))
    {
      j = -1;
      return j;
    }
    char[] arrayOfChar = this.buffer;
    paramInt = i;
    for (;;)
    {
      if (paramInt < 0) {
        break label67;
      }
      j = paramInt;
      if (paramStrMatcher.isMatch(arrayOfChar, paramInt, 0, i + 1) > 0) {
        break;
      }
      paramInt -= 1;
    }
    label67:
    return -1;
  }
  
  public String leftString(int paramInt)
  {
    if (paramInt <= 0) {
      return "";
    }
    if (paramInt >= this.size) {
      return new String(this.buffer, 0, this.size);
    }
    return new String(this.buffer, 0, paramInt);
  }
  
  public int length()
  {
    return this.size;
  }
  
  public String midString(int paramInt1, int paramInt2)
  {
    int i = paramInt1;
    if (paramInt1 < 0) {
      i = 0;
    }
    if ((paramInt2 <= 0) || (i >= this.size)) {
      return "";
    }
    if (this.size <= i + paramInt2) {
      return new String(this.buffer, i, this.size - i);
    }
    return new String(this.buffer, i, paramInt2);
  }
  
  public StrBuilder minimizeCapacity()
  {
    if (this.buffer.length > length())
    {
      char[] arrayOfChar = this.buffer;
      this.buffer = new char[length()];
      System.arraycopy(arrayOfChar, 0, this.buffer, 0, this.size);
    }
    return this;
  }
  
  public StrBuilder replace(int paramInt1, int paramInt2, String paramString)
  {
    int i = validateRange(paramInt1, paramInt2);
    if (paramString == null) {}
    for (paramInt2 = 0;; paramInt2 = paramString.length())
    {
      replaceImpl(paramInt1, i, i - paramInt1, paramString, paramInt2);
      return this;
    }
  }
  
  public StrBuilder replace(StrMatcher paramStrMatcher, String paramString, int paramInt1, int paramInt2, int paramInt3)
  {
    return replaceImpl(paramStrMatcher, paramString, paramInt1, validateRange(paramInt1, paramInt2), paramInt3);
  }
  
  public StrBuilder replaceAll(char paramChar1, char paramChar2)
  {
    if (paramChar1 != paramChar2)
    {
      int i = 0;
      while (i < this.size)
      {
        if (this.buffer[i] == paramChar1) {
          this.buffer[i] = paramChar2;
        }
        i += 1;
      }
    }
    return this;
  }
  
  public StrBuilder replaceAll(String paramString1, String paramString2)
  {
    int i;
    if (paramString1 == null)
    {
      i = 0;
      if (i <= 0) {
        return this;
      }
      if (paramString2 != null) {
        break label67;
      }
    }
    label67:
    for (int j = 0;; j = paramString2.length())
    {
      for (int k = indexOf(paramString1, 0); k >= 0; k = indexOf(paramString1, k + j)) {
        replaceImpl(k, k + i, i, paramString2, j);
      }
      i = paramString1.length();
      break;
    }
    return this;
  }
  
  public StrBuilder replaceAll(StrMatcher paramStrMatcher, String paramString)
  {
    return replace(paramStrMatcher, paramString, 0, this.size, -1);
  }
  
  public StrBuilder replaceFirst(char paramChar1, char paramChar2)
  {
    int i;
    if (paramChar1 != paramChar2) {
      i = 0;
    }
    for (;;)
    {
      if (i < this.size)
      {
        if (this.buffer[i] == paramChar1) {
          this.buffer[i] = paramChar2;
        }
      }
      else {
        return this;
      }
      i += 1;
    }
  }
  
  public StrBuilder replaceFirst(String paramString1, String paramString2)
  {
    int j = 0;
    int i;
    int k;
    if (paramString1 == null)
    {
      i = 0;
      if (i > 0)
      {
        k = indexOf(paramString1, 0);
        if (k >= 0) {
          if (paramString2 != null) {
            break label54;
          }
        }
      }
    }
    for (;;)
    {
      replaceImpl(k, k + i, i, paramString2, j);
      return this;
      i = paramString1.length();
      break;
      label54:
      j = paramString2.length();
    }
  }
  
  public StrBuilder replaceFirst(StrMatcher paramStrMatcher, String paramString)
  {
    return replace(paramStrMatcher, paramString, 0, this.size, 1);
  }
  
  public StrBuilder reverse()
  {
    if (this.size == 0) {}
    for (;;)
    {
      return this;
      int m = this.size / 2;
      char[] arrayOfChar = this.buffer;
      int k = 0;
      int j = this.size - 1;
      while (k < m)
      {
        int i = arrayOfChar[k];
        arrayOfChar[k] = arrayOfChar[j];
        arrayOfChar[j] = i;
        k += 1;
        j -= 1;
      }
    }
  }
  
  public String rightString(int paramInt)
  {
    if (paramInt <= 0) {
      return "";
    }
    if (paramInt >= this.size) {
      return new String(this.buffer, 0, this.size);
    }
    return new String(this.buffer, this.size - paramInt, paramInt);
  }
  
  public StrBuilder setCharAt(int paramInt, char paramChar)
  {
    if ((paramInt < 0) || (paramInt >= length())) {
      throw new StringIndexOutOfBoundsException(paramInt);
    }
    this.buffer[paramInt] = paramChar;
    return this;
  }
  
  public StrBuilder setLength(int paramInt)
  {
    if (paramInt < 0) {
      throw new StringIndexOutOfBoundsException(paramInt);
    }
    if (paramInt < this.size) {
      this.size = paramInt;
    }
    for (;;)
    {
      return this;
      if (paramInt > this.size)
      {
        ensureCapacity(paramInt);
        int i = this.size;
        this.size = paramInt;
        while (i < paramInt)
        {
          this.buffer[i] = '\000';
          i += 1;
        }
      }
    }
  }
  
  public StrBuilder setNewLineText(String paramString)
  {
    this.newLine = paramString;
    return this;
  }
  
  public StrBuilder setNullText(String paramString)
  {
    String str = paramString;
    if (paramString != null)
    {
      str = paramString;
      if (paramString.length() == 0) {
        str = null;
      }
    }
    this.nullText = str;
    return this;
  }
  
  public int size()
  {
    return this.size;
  }
  
  public boolean startsWith(String paramString)
  {
    if (paramString == null) {}
    int j;
    do
    {
      return false;
      j = paramString.length();
      if (j == 0) {
        return true;
      }
    } while (j > this.size);
    int i = 0;
    for (;;)
    {
      if (i >= j) {
        break label53;
      }
      if (this.buffer[i] != paramString.charAt(i)) {
        break;
      }
      i += 1;
    }
    label53:
    return true;
  }
  
  public String substring(int paramInt)
  {
    return substring(paramInt, this.size);
  }
  
  public String substring(int paramInt1, int paramInt2)
  {
    paramInt2 = validateRange(paramInt1, paramInt2);
    return new String(this.buffer, paramInt1, paramInt2 - paramInt1);
  }
  
  public char[] toCharArray()
  {
    if (this.size == 0) {
      return ArrayUtils.EMPTY_CHAR_ARRAY;
    }
    char[] arrayOfChar = new char[this.size];
    System.arraycopy(this.buffer, 0, arrayOfChar, 0, this.size);
    return arrayOfChar;
  }
  
  public char[] toCharArray(int paramInt1, int paramInt2)
  {
    paramInt2 = validateRange(paramInt1, paramInt2) - paramInt1;
    if (paramInt2 == 0) {
      return ArrayUtils.EMPTY_CHAR_ARRAY;
    }
    char[] arrayOfChar = new char[paramInt2];
    System.arraycopy(this.buffer, paramInt1, arrayOfChar, 0, paramInt2);
    return arrayOfChar;
  }
  
  public String toString()
  {
    return new String(this.buffer, 0, this.size);
  }
  
  public StringBuffer toStringBuffer()
  {
    return new StringBuffer(this.size).append(this.buffer, 0, this.size);
  }
  
  public StrBuilder trim()
  {
    if (this.size == 0) {}
    int i;
    do
    {
      return this;
      int k = this.size;
      char[] arrayOfChar = this.buffer;
      i = 0;
      int j;
      for (;;)
      {
        j = k;
        if (i >= k) {
          break;
        }
        j = k;
        if (arrayOfChar[i] > ' ') {
          break;
        }
        i += 1;
      }
      while ((i < j) && (arrayOfChar[(j - 1)] <= ' ')) {
        j -= 1;
      }
      if (j < this.size) {
        delete(j, this.size);
      }
    } while (i <= 0);
    delete(0, i);
    return this;
  }
  
  protected void validateIndex(int paramInt)
  {
    if ((paramInt < 0) || (paramInt > this.size)) {
      throw new StringIndexOutOfBoundsException(paramInt);
    }
  }
  
  protected int validateRange(int paramInt1, int paramInt2)
  {
    if (paramInt1 < 0) {
      throw new StringIndexOutOfBoundsException(paramInt1);
    }
    int i = paramInt2;
    if (paramInt2 > this.size) {
      i = this.size;
    }
    if (paramInt1 > i) {
      throw new StringIndexOutOfBoundsException("end < start");
    }
    return i;
  }
  
  class StrBuilderReader
    extends Reader
  {
    private int mark;
    private int pos;
    
    StrBuilderReader() {}
    
    public void close() {}
    
    public void mark(int paramInt)
    {
      this.mark = this.pos;
    }
    
    public boolean markSupported()
    {
      return true;
    }
    
    public int read()
    {
      if (!ready()) {
        return -1;
      }
      StrBuilder localStrBuilder = StrBuilder.this;
      int i = this.pos;
      this.pos = (i + 1);
      return localStrBuilder.charAt(i);
    }
    
    public int read(char[] paramArrayOfChar, int paramInt1, int paramInt2)
    {
      if ((paramInt1 < 0) || (paramInt2 < 0) || (paramInt1 > paramArrayOfChar.length) || (paramInt1 + paramInt2 > paramArrayOfChar.length) || (paramInt1 + paramInt2 < 0)) {
        throw new IndexOutOfBoundsException();
      }
      if (paramInt2 == 0) {
        return 0;
      }
      if (this.pos >= StrBuilder.this.size()) {
        return -1;
      }
      int i = paramInt2;
      if (this.pos + paramInt2 > StrBuilder.this.size()) {
        i = StrBuilder.this.size() - this.pos;
      }
      StrBuilder.this.getChars(this.pos, this.pos + i, paramArrayOfChar, paramInt1);
      this.pos += i;
      return i;
    }
    
    public boolean ready()
    {
      return this.pos < StrBuilder.this.size();
    }
    
    public void reset()
    {
      this.pos = this.mark;
    }
    
    public long skip(long paramLong)
    {
      long l = paramLong;
      if (this.pos + paramLong > StrBuilder.this.size()) {
        l = StrBuilder.this.size() - this.pos;
      }
      if (l < 0L) {
        return 0L;
      }
      this.pos = ((int)(this.pos + l));
      return l;
    }
  }
  
  class StrBuilderTokenizer
    extends StrTokenizer
  {
    StrBuilderTokenizer() {}
    
    public String getContent()
    {
      String str2 = super.getContent();
      String str1 = str2;
      if (str2 == null) {
        str1 = StrBuilder.this.toString();
      }
      return str1;
    }
    
    protected List tokenize(char[] paramArrayOfChar, int paramInt1, int paramInt2)
    {
      if (paramArrayOfChar == null) {
        return super.tokenize(StrBuilder.this.buffer, 0, StrBuilder.this.size());
      }
      return super.tokenize(paramArrayOfChar, paramInt1, paramInt2);
    }
  }
  
  class StrBuilderWriter
    extends Writer
  {
    StrBuilderWriter() {}
    
    public void close() {}
    
    public void flush() {}
    
    public void write(int paramInt)
    {
      StrBuilder.this.append((char)paramInt);
    }
    
    public void write(String paramString)
    {
      StrBuilder.this.append(paramString);
    }
    
    public void write(String paramString, int paramInt1, int paramInt2)
    {
      StrBuilder.this.append(paramString, paramInt1, paramInt2);
    }
    
    public void write(char[] paramArrayOfChar)
    {
      StrBuilder.this.append(paramArrayOfChar);
    }
    
    public void write(char[] paramArrayOfChar, int paramInt1, int paramInt2)
    {
      StrBuilder.this.append(paramArrayOfChar, paramInt1, paramInt2);
    }
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\org\apache\commons\lang\text\StrBuilder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */