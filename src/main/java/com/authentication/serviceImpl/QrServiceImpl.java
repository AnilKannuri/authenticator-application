package com.authentication.serviceImpl;

import com.authentication.service.QrService;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;

@Service
public class QrServiceImpl implements QrService {
    @Override
    public String generateQrCode(String username, String secret) throws WriterException, IOException {
        String uri = String.format(
                "otpauth://totp/MyApp:%s?secret=%s&issuer=MyApp",
                username,
                secret
        );

        BitMatrix matrix = new MultiFormatWriter().encode(uri, BarcodeFormat.QR_CODE, 200, 2000);
        ByteArrayOutputStream  stream = new ByteArrayOutputStream();
        MatrixToImageWriter.writeToStream(matrix, "PNG", stream);
        return Base64.getEncoder().encodeToString(stream.toByteArray());
    }
}
