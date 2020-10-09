package goods.statistics.server.listener;

import goods.statistics.server.Entity.GoodsReport;
import goods.statistics.server.service.GoodsReportService;
import goods.statistics.server.util.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.time.DateUtils;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@Slf4j
public class GoodsUpdateListener {

    @Autowired
    private GoodsReportService goodsReportService;

    @KafkaListener(topics = {"kafkaTest"})
    public void listen(ConsumerRecord<?, ?> record) {
        GoodsReport goodsReport=new GoodsReport();
        Integer today=Integer.parseInt(DateUtil.getDays());
        //查看今日报表是否已经生产
        int count =goodsReportService.getTodayReportIsLive(today);
        if (count>0){
            goodsReportService.updateGoodsReportToday(count,today);
        }else {
            goodsReport.setNewGoods(1);
            goodsReport.setNowDate(today);
            goodsReportService.insertGoodsReport(goodsReport);
        }
        Optional<?> kafkaMessage = Optional.ofNullable(record.value());
        if (kafkaMessage.isPresent()) {
            Object message = kafkaMessage.get();
            System.out.println("----------------- record =" + record);
            System.out.println("------------------ message =" + message);
        }
    }


}
