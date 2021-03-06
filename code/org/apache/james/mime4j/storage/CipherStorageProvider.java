package org.apache.james.mime4j.storage;

import java.io.IOException;
import java.io.InputStream;
import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class CipherStorageProvider
  extends AbstractStorageProvider
{
  private final String algorithm;
  private final StorageProvider backend;
  private final KeyGenerator keygen;
  
  public CipherStorageProvider(StorageProvider paramStorageProvider)
  {
    this(paramStorageProvider, "Blowfish");
  }
  
  public CipherStorageProvider(StorageProvider paramStorageProvider, String paramString)
  {
    if (paramStorageProvider == null) {
      throw new IllegalArgumentException();
    }
    try
    {
      this.backend = paramStorageProvider;
      this.algorithm = paramString;
      this.keygen = KeyGenerator.getInstance(paramString);
      return;
    }
    catch (NoSuchAlgorithmException paramStorageProvider)
    {
      throw new IllegalArgumentException(paramStorageProvider);
    }
  }
  
  private SecretKeySpec getSecretKeySpec()
  {
    return new SecretKeySpec(this.keygen.generateKey().getEncoded(), this.algorithm);
  }
  
  public StorageOutputStream createStorageOutputStream()
    throws IOException
  {
    SecretKeySpec localSecretKeySpec = getSecretKeySpec();
    return new CipherStorageOutputStream(this.backend.createStorageOutputStream(), this.algorithm, localSecretKeySpec);
  }
  
  private static final class CipherStorage
    implements Storage
  {
    private final String algorithm;
    private Storage encrypted;
    private final SecretKeySpec skeySpec;
    
    public CipherStorage(Storage paramStorage, String paramString, SecretKeySpec paramSecretKeySpec)
    {
      this.encrypted = paramStorage;
      this.algorithm = paramString;
      this.skeySpec = paramSecretKeySpec;
    }
    
    public void delete()
    {
      if (this.encrypted != null)
      {
        this.encrypted.delete();
        this.encrypted = null;
      }
    }
    
    public InputStream getInputStream()
      throws IOException
    {
      if (this.encrypted == null) {
        throw new IllegalStateException("storage has been deleted");
      }
      try
      {
        Object localObject = Cipher.getInstance(this.algorithm);
        ((Cipher)localObject).init(2, this.skeySpec);
        localObject = new CipherInputStream(this.encrypted.getInputStream(), (Cipher)localObject);
        return (InputStream)localObject;
      }
      catch (GeneralSecurityException localGeneralSecurityException)
      {
        throw ((IOException)new IOException().initCause(localGeneralSecurityException));
      }
    }
  }
  
  private static final class CipherStorageOutputStream
    extends StorageOutputStream
  {
    private final String algorithm;
    private final CipherOutputStream cipherOut;
    private final SecretKeySpec skeySpec;
    private final StorageOutputStream storageOut;
    
    public CipherStorageOutputStream(StorageOutputStream paramStorageOutputStream, String paramString, SecretKeySpec paramSecretKeySpec)
      throws IOException
    {
      try
      {
        this.storageOut = paramStorageOutputStream;
        this.algorithm = paramString;
        this.skeySpec = paramSecretKeySpec;
        paramString = Cipher.getInstance(paramString);
        paramString.init(1, paramSecretKeySpec);
        this.cipherOut = new CipherOutputStream(paramStorageOutputStream, paramString);
        return;
      }
      catch (GeneralSecurityException paramStorageOutputStream)
      {
        throw ((IOException)new IOException().initCause(paramStorageOutputStream));
      }
    }
    
    public void close()
      throws IOException
    {
      super.close();
      this.cipherOut.close();
    }
    
    protected Storage toStorage0()
      throws IOException
    {
      return new CipherStorageProvider.CipherStorage(this.storageOut.toStorage(), this.algorithm, this.skeySpec);
    }
    
    protected void write0(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
      throws IOException
    {
      this.cipherOut.write(paramArrayOfByte, paramInt1, paramInt2);
    }
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\org\apache\james\mime4j\storage\CipherStorageProvider.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */