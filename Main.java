import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;

public class Main {
   public static void main(String[] args) {
      int bitLength = 4096;
      SecureRandom rand = new SecureRandom();

      BigInteger p = new BigInteger(bitLength / 2, 100, rand);
      BigInteger q = new BigInteger(bitLength / 2, 100, rand);
      BigInteger n = p.multiply(q);
      BigInteger phiN = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));
      BigInteger e = new BigInteger("3");
      while (phiN.gcd(e).intValue() > 1) {
          e = e.add(new BigInteger("2"));
      }
      
      BigInteger d = e.modInverse(phiN);
      String message = "Hej hopp din tok";
      BigInteger msg = new BigInteger(message.getBytes());
      // m^e mod n = c
      // Encrypt
      String cipher = msg.modPow(e, n).toString();
      // Decrypt
      String plain = new String((new BigInteger(cipher)).modPow(d, n).toByteArray());
      System.out.println(cipher);
      System.out.println(plain);

      // Sign
      String toSign = "ABC123";
      BigInteger toSignBI = new BigInteger(toSign.getBytes());

      // Signering
      String signC = toSignBI.modPow(d, n).toString();
      String clearSign = new String((new BigInteger(signC)).modPow(e, n).toByteArray());
      System.out.println(clearSign);
   }
}