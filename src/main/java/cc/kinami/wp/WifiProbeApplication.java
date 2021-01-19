package cc.kinami.wp;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@MapperScan(value = "cc.kinami.wp.dao")
public class WifiProbeApplication {

    public static void main(String[] args) {
        SpringApplication.run(WifiProbeApplication.class, args);
    }

}
