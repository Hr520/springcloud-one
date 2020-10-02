package vo;

import com.web.entity.Role;
import com.web.entity.RoleMenu;
import lombok.Data;

import java.util.List;

@Data
public class UserVo {
    private Integer Id;
    private String SystemName;
    private String PasswordLogin;
    private  String Code;
    private Integer Status;
    private  String AddTime;
    private String Remark;
    private String NickName;
    private String ImagUrl;

    private List<Role> roles;

    private List<RoleMenu> roleMenus;
}
