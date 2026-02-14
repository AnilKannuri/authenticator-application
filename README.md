# 🔐 Authenticator Service (TOTP-Based) – Spring Boot

A Spring Boot application that implements Time-Based One-Time Password (TOTP) authentication similar to Google Authenticator and Microsoft Authenticator.

This project demonstrates how to:
- Generate TOTP secret
- Generate QR code
- Verify 6-digit OTP
- Store users in PostgreSQL
- Implement RFC 6238 compliant authentication

---

## 🚀 Tech Stack

- Java 21
- Spring Boot 3.x
- Spring Data JPA
- PostgreSQL
- ZXing (QR Code generation)
- dev.samstevens.totp (TOTP library)
- Maven

---

## 📦 Features

✔ User Registration  
✔ Base32 Secret Key Generation  
✔ QR Code Generation  
✔ 6-digit OTP Verification  
✔ 30-second TOTP window  
✔ Configurable time tolerance