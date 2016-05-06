package org.apache.james.mime4j.message;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.apache.james.mime4j.field.ContentDispositionField;
import org.apache.james.mime4j.field.ContentTransferEncodingField;
import org.apache.james.mime4j.field.ContentTypeField;
import org.apache.james.mime4j.field.Fields;
import org.apache.james.mime4j.parser.Field;
import org.apache.james.mime4j.util.MimeUtil;

public abstract class Entity
  implements Disposable
{
  private Body body = null;
  private Header header = null;
  private Entity parent = null;
  
  protected Entity() {}
  
  protected Entity(Entity paramEntity)
  {
    if (paramEntity.header != null) {
      this.header = new Header(paramEntity.header);
    }
    if (paramEntity.body != null) {
      setBody(BodyCopier.copy(paramEntity.body));
    }
  }
  
  public void dispose()
  {
    if (this.body != null) {
      this.body.dispose();
    }
  }
  
  public Body getBody()
  {
    return this.body;
  }
  
  public String getCharset()
  {
    return ContentTypeField.getCharset((ContentTypeField)getHeader().getField("Content-Type"));
  }
  
  public String getContentTransferEncoding()
  {
    return ContentTransferEncodingField.getEncoding((ContentTransferEncodingField)getHeader().getField("Content-Transfer-Encoding"));
  }
  
  public String getDispositionType()
  {
    ContentDispositionField localContentDispositionField = (ContentDispositionField)obtainField("Content-Disposition");
    if (localContentDispositionField == null) {
      return null;
    }
    return localContentDispositionField.getDispositionType();
  }
  
  public String getFilename()
  {
    ContentDispositionField localContentDispositionField = (ContentDispositionField)obtainField("Content-Disposition");
    if (localContentDispositionField == null) {
      return null;
    }
    return localContentDispositionField.getFilename();
  }
  
  public Header getHeader()
  {
    return this.header;
  }
  
  public String getMimeType()
  {
    ContentTypeField localContentTypeField2 = (ContentTypeField)getHeader().getField("Content-Type");
    if (getParent() != null) {}
    for (ContentTypeField localContentTypeField1 = (ContentTypeField)getParent().getHeader().getField("Content-Type");; localContentTypeField1 = null) {
      return ContentTypeField.getMimeType(localContentTypeField2, localContentTypeField1);
    }
  }
  
  public Entity getParent()
  {
    return this.parent;
  }
  
  public boolean isMimeType(String paramString)
  {
    return getMimeType().equalsIgnoreCase(paramString);
  }
  
  public boolean isMultipart()
  {
    ContentTypeField localContentTypeField = (ContentTypeField)getHeader().getField("Content-Type");
    return (localContentTypeField != null) && (localContentTypeField.getBoundary() != null) && (getMimeType().startsWith("multipart/"));
  }
  
  <F extends Field> F obtainField(String paramString)
  {
    Header localHeader = getHeader();
    if (localHeader == null) {
      return null;
    }
    return localHeader.getField(paramString);
  }
  
  Header obtainHeader()
  {
    if (this.header == null) {
      this.header = new Header();
    }
    return this.header;
  }
  
  public Body removeBody()
  {
    if (this.body == null) {
      return null;
    }
    Body localBody = this.body;
    this.body = null;
    localBody.setParent(null);
    return localBody;
  }
  
  public void setBody(Body paramBody)
  {
    if (this.body != null) {
      throw new IllegalStateException("body already set");
    }
    this.body = paramBody;
    paramBody.setParent(this);
  }
  
  public void setBody(Body paramBody, String paramString)
  {
    setBody(paramBody, paramString, null);
  }
  
  public void setBody(Body paramBody, String paramString, Map<String, String> paramMap)
  {
    setBody(paramBody);
    obtainHeader().setField(Fields.contentType(paramString, paramMap));
  }
  
  public void setContentDisposition(String paramString)
  {
    obtainHeader().setField(Fields.contentDisposition(paramString, null, -1L, null, null, null));
  }
  
  public void setContentDisposition(String paramString1, String paramString2)
  {
    obtainHeader().setField(Fields.contentDisposition(paramString1, paramString2, -1L, null, null, null));
  }
  
  public void setContentDisposition(String paramString1, String paramString2, long paramLong)
  {
    obtainHeader().setField(Fields.contentDisposition(paramString1, paramString2, paramLong, null, null, null));
  }
  
  public void setContentDisposition(String paramString1, String paramString2, long paramLong, Date paramDate1, Date paramDate2, Date paramDate3)
  {
    obtainHeader().setField(Fields.contentDisposition(paramString1, paramString2, paramLong, paramDate1, paramDate2, paramDate3));
  }
  
  public void setContentTransferEncoding(String paramString)
  {
    obtainHeader().setField(Fields.contentTransferEncoding(paramString));
  }
  
  public void setFilename(String paramString)
  {
    Header localHeader = obtainHeader();
    Object localObject = (ContentDispositionField)localHeader.getField("Content-Disposition");
    if (localObject == null)
    {
      if (paramString != null) {
        localHeader.setField(Fields.contentDisposition("attachment", paramString, -1L, null, null, null));
      }
      return;
    }
    String str = ((ContentDispositionField)localObject).getDispositionType();
    localObject = new HashMap(((ContentDispositionField)localObject).getParameters());
    if (paramString == null) {
      ((Map)localObject).remove("filename");
    }
    for (;;)
    {
      localHeader.setField(Fields.contentDisposition(str, (Map)localObject));
      return;
      ((Map)localObject).put("filename", paramString);
    }
  }
  
  public void setHeader(Header paramHeader)
  {
    this.header = paramHeader;
  }
  
  public void setMessage(Message paramMessage)
  {
    setBody(paramMessage, "message/rfc822", null);
  }
  
  public void setMultipart(Multipart paramMultipart)
  {
    setBody(paramMultipart, "multipart/" + paramMultipart.getSubType(), Collections.singletonMap("boundary", MimeUtil.createUniqueBoundary()));
  }
  
  public void setMultipart(Multipart paramMultipart, Map<String, String> paramMap)
  {
    String str = "multipart/" + paramMultipart.getSubType();
    Object localObject = paramMap;
    if (!paramMap.containsKey("boundary"))
    {
      localObject = new HashMap(paramMap);
      ((Map)localObject).put("boundary", MimeUtil.createUniqueBoundary());
    }
    setBody(paramMultipart, str, (Map)localObject);
  }
  
  public void setParent(Entity paramEntity)
  {
    this.parent = paramEntity;
  }
  
  public void setText(TextBody paramTextBody)
  {
    setText(paramTextBody, "plain");
  }
  
  public void setText(TextBody paramTextBody, String paramString)
  {
    String str1 = "text/" + paramString;
    Object localObject = null;
    String str2 = paramTextBody.getMimeCharset();
    paramString = (String)localObject;
    if (str2 != null)
    {
      paramString = (String)localObject;
      if (!str2.equalsIgnoreCase("us-ascii")) {
        paramString = Collections.singletonMap("charset", str2);
      }
    }
    setBody(paramTextBody, str1, paramString);
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\org\apache\james\mime4j\message\Entity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */