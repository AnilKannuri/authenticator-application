package com.authentication.service;

import com.google.zxing.WriterException;

import java.io.IOException;

public interface QrService {
    String generateQrCode(String username, String secret) throws WriterException, IOException;
}
