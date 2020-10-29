package goods.statistics.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import goods.statistics.server.Entity.GoodsReport;
import goods.statistics.server.mapper.GoodsReportMapper;
import goods.statistics.server.service.GoodsReportService;
import goods.statistics.server.util.DateUtil;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class GoodsReportServiceImpl implements GoodsReportService {

    @Resource
    private GoodsReportMapper goodsReportMapper;
    @Override
    public int getTodayReportIsLive(int date) {
        QueryWrapper<GoodsReport> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("now_date",date);
        return goodsReportMapper.selectCount(queryWrapper);
    }

    @Override
    public void updateGoodsReportToday(int count,int today) {
        goodsReportMapper.updateGoodsReportToday(count,today);
    }

    @Override
    public void insertGoodsReport(GoodsReport goodsReport) {
        goodsReportMapper.insert(goodsReport);
    }
}
