package goods.statistics.server.service;

import goods.statistics.server.Entity.GoodsReport;

public interface GoodsReportService {
    int getTodayReportIsLive(int parseInt);

    void updateGoodsReportToday(int parseInt,int today);

    void insertGoodsReport(GoodsReport goodsReport);
}
