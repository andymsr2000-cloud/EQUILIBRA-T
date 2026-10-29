package pe.gob.pnp.criminalistica.equilibrat.util;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import org.apache.commons.codec.binary.Hex;

/**
 * Hashing de contraseñas con PBKDF2-HMAC-SHA256 y salt aleatorio.
 */
public final class CriptografiaUtil {

    private static final int ITERACIONES = 65_536;
    private static final int LONGITUD_CLAVE = 256;
    private static final int LONGITUD_SALT = 16;
    private static final String ALGORITMO = "PBKDF2WithHmacSHA256";

    private CriptografiaUtil() {
    }

    public static String hashearPassword(String passwordPlano) {
        byte[] salt = new byte[LONGITUD_SALT];
        new SecureRandom().nextBytes(salt);
        byte[] hash = derivar(passwordPlano.toCharArray(), salt);
        return Hex.encodeHexString(salt) + ":" + Hex.encodeHexString(hash);
    }

    public static boolean verificarPassword(String passwordPlano, String almacenado) {
        if (passwordPlano == null || almacenado == null || !almacenado.contains(":")) {
            return false;
        }
        String[] partes = almacenado.split(":");
        try {
            byte[] salt = Hex.decodeHex(partes[0]);
            byte[] esperado = Hex.decodeHex(partes[1]);
            byte[] actual = derivar(passwordPlano.toCharArray(), salt);
            return Arrays.equals(esperado, actual);
        } catch (Exception ex) {
            return false;
        }
    }

    private static byte[] derivar(char[] password, byte[] salt) {
        PBEKeySpec spec = new PBEKeySpec(password, salt, ITERACIONES, LONGITUD_CLAVE);
        try {
            SecretKeyFactory factory = SecretKeyFactory.getInstance(ALGORITMO);
            return factory.generateSecret(spec).getEncoded();
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new IllegalStateException("No se pudo derivar el hash PBKDF2", e);
        } finally {
            spec.clearPassword();
        }
    }
}
