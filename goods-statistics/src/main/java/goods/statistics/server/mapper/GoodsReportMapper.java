package goods.statistics.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import goods.statistics.server.Entity.GoodsReport;
import org.apache.ibatis.annotations.Param;

public interface GoodsReportMapper extends BaseMapper<GoodsReport> {
    void updateGoodsReportToday(@Param("count") int count, @Param("today") int today);
}
