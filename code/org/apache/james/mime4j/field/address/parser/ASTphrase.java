package org.apache.james.mime4j.field.address.parser;

public class ASTphrase
  extends SimpleNode
{
  public ASTphrase(int paramInt)
  {
    super(paramInt);
  }
  
  public ASTphrase(AddressListParser paramAddressListParser, int paramInt)
  {
    super(paramAddressListParser, paramInt);
  }
  
  public Object jjtAccept(AddressListParserVisitor paramAddressListParserVisitor, Object paramObject)
  {
    return paramAddressListParserVisitor.visit(this, paramObject);
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\org\apache\james\mime4j\field\address\parser\ASTphrase.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */