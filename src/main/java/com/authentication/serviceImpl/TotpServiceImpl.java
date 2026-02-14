package com.authentication.serviceImpl;

import com.authentication.service.TotpService;
import dev.samstevens.totp.code.DefaultCodeGenerator;
import dev.samstevens.totp.code.DefaultCodeVerifier;
import dev.samstevens.totp.secret.DefaultSecretGenerator;
import dev.samstevens.totp.time.SystemTimeProvider;
import org.springframework.stereotype.Service;

@Service
public class TotpServiceImpl implements TotpService {

    private final DefaultSecretGenerator secretGenerator;
    private final DefaultCodeVerifier codeVerifier;

    public TotpServiceImpl() {
        this.secretGenerator = new DefaultSecretGenerator();
        DefaultCodeGenerator codeGenerator = new DefaultCodeGenerator();
        this.codeVerifier = new DefaultCodeVerifier(codeGenerator, new SystemTimeProvider());
        this.codeVerifier.setTimePeriod(30);
        this.codeVerifier.setAllowedTimePeriodDiscrepancy(1);
    }

    @Override
    public String generateSecret() {
        return secretGenerator.generate();
    }

    @Override
    public boolean verifyCode(String secret, String code) {
        return codeVerifier.isValidCode(secret, code);
    }

}
