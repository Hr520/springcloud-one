package goods.statistics.server;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
@MapperScan("goods.statistics.server.mapper")
public class GoodsStatisticsServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(GoodsStatisticsServerApplication.class, args);

    }
}
