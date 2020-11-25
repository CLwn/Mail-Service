package StrategyPattern;

public interface StrategyCipher {

    String encrypt(String body);
    String decrypt(String encryptData);
}
