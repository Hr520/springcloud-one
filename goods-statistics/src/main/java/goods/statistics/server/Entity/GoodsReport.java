package goods.statistics.server.Entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("goods_report")
public class GoodsReport {
    @TableId
    private Integer Id;
    private Integer NewGoods;
    private Integer NowDate;
}
