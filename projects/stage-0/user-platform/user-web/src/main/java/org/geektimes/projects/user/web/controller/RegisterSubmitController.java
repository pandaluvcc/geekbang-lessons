package org.geektimes.projects.user.web.controller;

import org.geektimes.projects.user.domain.User;
import org.geektimes.projects.user.service.UserService;
import org.geektimes.projects.user.service.impl.UserServiceImpl;
import org.geektimes.web.mvc.controller.PageController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import java.util.List;

/**
 * 注册提交
 * @author Charles
 */
@Path("/registerSubmit")
public class RegisterSubmitController implements PageController {

    private UserService userService = null;

    public RegisterSubmitController() {
        this.userService = new UserServiceImpl();
    }

    /**
     * @param request  HTTP 请求
     * @param response HTTP 相应
     * @return 视图地址路径
     * @throws Throwable 异常发生时
     */
    @Override
    @GET
    @POST
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        String userName = request.getParameter("userName");
        String passWord = request.getParameter("passWord");
        if (isEmpty(userName) || isEmpty(passWord)){
            return "/register.jsp";
        }
        User user = new User();
        user.setName(userName);
        user.setPassword(passWord);
        if (userService.register(user)) {
            userService.queryAll().stream().forEach(u -> {
                System.out.println(u.toString());
            });
            return "/success.jsp";
        }
        return "/register.jsp";
    }

    private boolean isEmpty(String column) {
        if (null == column || "".equals(column)) {
            return true;
        }
        return false;
    }
}
