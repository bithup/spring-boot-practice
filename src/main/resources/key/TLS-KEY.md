- TLS是SSL的前身，SSL已经停止更新
- 生成TLS证书，可以使用openssl工具，也可以使用Java自带工具keytool
- openssl生成的证书格式和Java不同
- Java有自己的管理证书的格式
### keytool生成证书参数
- genkey 表示要创建一个新的密钥  
- dname  表示密钥的Distinguished Names，表明了密钥的发行者身份  
  * CN=commonName  
  * OU=organizationUnit  
  * O=organizationName  
  * L=localityName  
  * S=stateName  
  * C=country  
- keyalg    使用加密的算法，这里是RSA  
- alias     密钥的别名  
- keypass   私有密钥的密码，这里设置为changeit  
- keystore  密钥保存在D:盘目录下的mykeystore文件中  
- storepass 存取密码，这里设置为changeit，这个密码提供系统从mykeystore文件中将信息取出  
- validity  该密钥的有效期为 36500表示100年 (默认为90天)

### 例子
keytool -genkey -alias demo -storetype PKCS12 -keyalg RSA -keysize 2048 -keystore ./keystore.p12  -dname "CN=localhost, OU=localhost, O=localhost, L=hangzhou, ST=zhejiang, C=CN"

配置好证书后启动，console中显示一个error:connector that does not support ALPN  
原因是Java8不支持ALPN，