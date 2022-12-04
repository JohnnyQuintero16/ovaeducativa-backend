package com.educativa.ova.Service.Implementation;

import com.educativa.ova.DAO.EstudianteDAO;
import com.educativa.ova.Model.Estudiante;
import com.educativa.ova.Service.EstudianteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;
@Service
@RequiredArgsConstructor
public class EstudianteServiceImp implements EstudianteService {

    private final EstudianteDAO estudianteDAO;

    @Override
    public Estudiante createEstudiante(Estudiante estudiante) {
        try{
            estudiante.setClave(encriptar(estudiante.getClave(),"9sa87yh#f!gqunfp98hy!!awo098#*ahis"));
        }catch (Exception e){
            System.err.println(e.getMessage());
            return null;
        }
        return estudianteDAO.save(estudiante);
    }

    @Override
    public Estudiante loginEstudiante(String email, String clave) throws Exception {
        Estudiante loginEstudiante = estudianteDAO.findByEmail(email).orElse(null);
        if(loginEstudiante == null){
            return null;
        }
        String claveDescifrada = desencriptar(loginEstudiante.getClave(),"9sa87yh#f!gqunfp98hy!!awo098#*ahis");
        System.out.println(claveDescifrada);
        if(clave.equals(claveDescifrada)){
            return loginEstudiante;
        }
        return null;
    }

    @Override
    public List<Estudiante> getEstudiantes() {
        return estudianteDAO.findAll();
    }

    @Override
    public Estudiante findByEstudiante(String codigo) {
        return estudianteDAO.findById(codigo).orElse(null);
    }

    private SecretKeySpec crearClave(String clave) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        byte[] claveEncriptacion = clave.getBytes("UTF-8");

        MessageDigest sha = MessageDigest.getInstance("SHA-1");

        claveEncriptacion = sha.digest(claveEncriptacion);
        claveEncriptacion = Arrays.copyOf(claveEncriptacion, 16);

        SecretKeySpec secretKey = new SecretKeySpec(claveEncriptacion, "AES");

        return secretKey;
    }

    public String encriptar(String datos, String claveSecreta) throws UnsupportedEncodingException, NoSuchAlgorithmException, InvalidKeyException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {
        SecretKeySpec secretKey = this.crearClave(claveSecreta);

        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);

        byte[] datosEncriptar = datos.getBytes("UTF-8");
        byte[] bytesEncriptados = cipher.doFinal(datosEncriptar);
        String encriptado = Base64.getEncoder().encodeToString(bytesEncriptados);

        return encriptado;
    }

    public String desencriptar(String datosEncriptados, String claveSecreta) throws UnsupportedEncodingException, NoSuchAlgorithmException, InvalidKeyException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        SecretKeySpec secretKey = this.crearClave(claveSecreta);

        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
        cipher.init(Cipher.DECRYPT_MODE, secretKey);

        byte[] bytesEncriptados = Base64.getDecoder().decode(datosEncriptados);
        byte[] datosDesencriptados = cipher.doFinal(bytesEncriptados);
        String datos = new String(datosDesencriptados);

        return datos;
    }


}
