package org.geektimes.projects.user.web.controller;

import org.geektimes.projects.user.domain.User;
import org.geektimes.projects.user.repository.H2DatabaseUserRepository;
import org.geektimes.web.mvc.controller.PageController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import java.util.Collection;

/**
 * @author Charles
 */
@Path("/login")
public class LoginController implements PageController {
    /**
     * @param request  HTTP 请求
     * @param response HTTP 相应
     * @return 视图地址路径
     * @throws Throwable 异常发生时
     */
    @GET
    @POST
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        String userName = request.getParameter("userName");
        String passWord = request.getParameter("passWord");
//        if ("xmgnb".equals(userName) && "112233".equals(passWord)) {
//            return "/success.jsp";
//        }

        H2DatabaseUserRepository repository = new H2DatabaseUserRepository();
        Collection<User> users = repository.getAll();
        System.out.println("##### count: " + users.size());

        // TODO 由于时间不足，先提交作业了...
        return "/register.jsp";
    }
}
