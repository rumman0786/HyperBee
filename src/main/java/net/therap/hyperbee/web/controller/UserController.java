package net.therap.hyperbee.web.controller;

import net.therap.hyperbee.dao.UserDaoImpl;
//import net.therap.hyperbee.dao.UserDao;
//import net.therap.hyperbee.domain.Dish;
import net.therap.hyperbee.domain.User;
//import net.therap.hyperbee.web.command.DishCommand;
import net.therap.hyperbee.web.command.UserCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author rumman
 * @since 11/7/16
 */
@Controller
public class UserController {

    @Autowired
    private UserDaoImpl userDao;

    @Autowired
    @Qualifier("userCommandValidator")
    private Validator validator;

    @InitBinder
    private void initBinder(WebDataBinder binder) {
        binder.setValidator(validator);
    }

    @RequestMapping(value = "/user-list", method = RequestMethod.GET)
    public String showUserList(HttpServletRequest request) {
        List<User> userList = userDao.findAll();
        request.setAttribute("userList", userList);
        request.setAttribute("page", "user");
        return "user/list_user";
    }

    @RequestMapping(value = "/add-user", method = RequestMethod.GET)
    public ModelAndView showAddUserPage() {
        ModelAndView modelAndView = new ModelAndView("user/add-user");
        modelAndView.addObject("page", "user");
        modelAndView.addObject("formUser", new UserCommand());

        return modelAndView;
    }

    @RequestMapping(value = "/add-user", method = RequestMethod.POST)
    public ModelAndView handleAddUser(@ModelAttribute("formUser") @Validated UserCommand userCommand,
                                      BindingResult bindingResult,
                                      Model model) {

        if (bindingResult.hasErrors()) {
            return new ModelAndView("user/add-user");
        }

        User user = new User();
        user.setUsername(userCommand.getUsername());
        user.setPassword(User.makePassword(userCommand.getPassword()));
        user.setFirstName(userCommand.getFirstName());
        user.setLastName(userCommand.getLastName());
        user.setEmail(userCommand.getEmail());
        user.setSuperuser(userCommand.getIsSuperuser());

        String redirectUrl = "/user-list";
        boolean status = userDao.insertUser(user);
        if (status) {
            redirectUrl += "?success=success";
        } else {
            redirectUrl += "?failure=failure";
        }

        return new ModelAndView("redirect:" + redirectUrl);
    }

    @RequestMapping(value = "/edit-user", method = RequestMethod.GET)
    public ModelAndView showEditUser(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));
        ModelAndView model = new ModelAndView("user/edit-user");
        User editUser = userDao.findById(id);

        UserCommand userCommand = new UserCommand();
        userCommand.setId(editUser.getId());
        userCommand.setUsername(editUser.getUsername());
        userCommand.setFirstName(editUser.getFirstName());
        userCommand.setLastName(editUser.getLastName());
        userCommand.setIsSuperuser(editUser.getIsSuperuser());
        userCommand.setEmail(editUser.getEmail());
        userCommand.setPassword(editUser.getPassword());
        userCommand.setConfirmPassword(editUser.getPassword());

        model.addObject("editUser", userCommand);
        model.addObject("page", "user");

        return model;
    }

    @RequestMapping(value = "/edit-user", method = RequestMethod.POST)
    public ModelAndView handleEditUser(@ModelAttribute("editUser") @Validated UserCommand userCommand,
                                       BindingResult bindingResult,
                                       Model model) {

        if (bindingResult.hasErrors()) {
            return new ModelAndView("user/edit-user");
        }

        User user = userDao.findById(userCommand.getId());
        user.setFirstName(userCommand.getFirstName());
        user.setLastName(userCommand.getLastName());
        user.setEmail(userCommand.getEmail());
        user.setSuperuser(userCommand.getIsSuperuser());

        String redirectUrl = "/user-list";
        boolean status = userDao.updateUser(user);
        if (status) {
            redirectUrl += "?success=success";
        } else {
            redirectUrl += "?failure=failure";
        }

        return new ModelAndView("redirect:" + redirectUrl);
    }

    @RequestMapping(value = "/delete-user", method = RequestMethod.GET)
    public String deleteUser(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));
        User user = userDao.findById(id);
        boolean status = userDao.deleteUser(user);
        String redirectUrl = "/user-list";
        return "redirect:" + redirectUrl;
    }

}
