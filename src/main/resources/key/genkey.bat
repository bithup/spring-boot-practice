keytool -genkey -alias demo -storetype PKCS12 -keyalg RSA -keysize 2048 -keystore ./keystore.p12  -dname "CN=localhost, OU=localhost, O=localhost, L=zhengzhou, ST=henan, C=CN"
# 密钥库口令 123456