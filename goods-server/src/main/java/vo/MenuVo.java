package vo;

import com.web.entity.Menu;
import lombok.Data;

import java.util.List;

@Data
public class MenuVo {
    private  Integer Id;
    private  String MenuName;
    private  String MenuUrl;
    private  Integer Pid;
    private  Integer MenuOrder;
    private  String MenuPic;
    private List<Menu> subMenu;
}
