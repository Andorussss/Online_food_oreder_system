package kz.foodorder.delivery.controllers;

import jakarta.servlet.http.HttpSession;
import kz.foodorder.delivery.entities.*;
import kz.foodorder.delivery.repositories.RoleRepository;
import kz.foodorder.delivery.services.DishService;
import kz.foodorder.delivery.services.OrderService;
import kz.foodorder.delivery.services.RoleService;
import kz.foodorder.delivery.services.UserService;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;

import java.util.List;
import java.util.Set;

@Controller
public class DeliveryController {
    private final OrderService orderService;
    private final UserService userService;
    private final DishService dishService;

    private final RoleService roleService;

    @Autowired
    public DeliveryController(OrderService orderService,UserService userService,DishService dishService,RoleService roleService){
        this.orderService=orderService;
        this.userService=userService;
        this.dishService=dishService;
        this.roleService=roleService;
    }
    @GetMapping("/")
    public String index(Model model,HttpSession session){
        List<Dish> dishList=dishService.getAllItem();
        model.addAttribute("dishes",dishList);
        if(session.getAttribute("IsLogged")==null)session.setAttribute("IsLogged",false);
        Boolean isLogged=(Boolean) session.getAttribute("IsLogged");
        String role=session.getAttribute("role")!=null?(String) session.getAttribute("role"):"null";
        model.addAttribute("isLogged",isLogged);
        model.addAttribute("role",role);
        return "index";
    }
    @PostMapping(value = "/add")
    public String addItem(@RequestParam(name = "name") String name,
                          @RequestParam(name = "url") String url,
                          @RequestParam(name = "price") double price,
                          @RequestParam(name = "description") String description)
    {
        List<Role> roles=roleService.getAllItem();
        User user=new User((Long) null, "user", "password", "email", orderService.getAllItem(),roles);
        userService.addItem(user);
        dishService.addItem(new Dish(null,name,description,price,url));
        return "redirect:/";
    }
    @GetMapping("/details/{id}")
    public String about(Model model,@PathVariable Long id){
        model.addAttribute("dish",dishService.getItem(id));
        return "details";
    }
    @PostMapping(value = "/update/{id}")
    public String updateDish(Model model,@RequestParam(name = "name") String name,
                          @RequestParam(name = "url") String url,
                          @RequestParam(name = "price") double price,
                          @RequestParam(name = "description") String description,@PathVariable Long id)
    {
        Dish dish=dishService.getItem(id);
        dish.setName(name);
        dish.setPrice(price);
        dish.setDescription(description);
        dish.setImageUrl(url);
        dishService.updateItem(dish);
        return "redirect:/";
    }
    @PostMapping(value = "/delete/{id}")
    public String deleteDish(@PathVariable Long id)
    {
        dishService.deleteItem(dishService.getItem(id));
        return "redirect:/";
    }
    @GetMapping(value = "/order")
    public String order(Model model,HttpSession session)
    {
        User user=(User) session.getAttribute("user");
        List<Order> ordersList=orderService.getByUser_UserId(user.getUserId());
        if(session.getAttribute("IsLogged")==null)session.setAttribute("IsLogged",false);
        Boolean isLogged=(Boolean) session.getAttribute("IsLogged");
        String role=session.getAttribute("role")!=null?(String) session.getAttribute("role"):"null";
        model.addAttribute("isLogged",isLogged);
        model.addAttribute("role",role);
        model.addAttribute("orders",ordersList);
        return "order_history";
    }
    @PostMapping(value = "/order/{id}")
    public String addOrder(HttpSession session,@PathVariable Long id){

        try {
            Dish dish=dishService.getItem(id);
            User user=(User) session.getAttribute("user");
            Order order=new Order(null,user,new Date(),dish.getPrice(),dish);
            orderService.addItem(order);
            return "redirect:/order";
        }
        catch (Exception e){
            return "redirect:/error";
        }

    }
    @GetMapping("/login")
    public String login(Model model,HttpSession session){

        Boolean error=session.getAttribute("errorL")!=null?(Boolean) session.getAttribute("errorL"):false;
        model.addAttribute("error",error);
        return "login";
    }
    @PostMapping("/login")
    public String confirmLogin(@RequestParam(name="email") String email, @RequestParam (name = "password") String password, HttpSession session)
    {
        User user = userService.getByEmail(email);
        if(user.getPassword().equals("admin")&&email.equals("admin@gmail.com"))
        {
            session.setAttribute("role","admin");
            session.setAttribute("IsLogged",true);
            session.setAttribute("errorL",false);
            return "redirect:/";
        }
        else if(user!=null&&user.getPassword().equals(password)){
            session.setAttribute("user", user);
            session.setAttribute("role","user");
            session.setAttribute("IsLogged",true);
            session.setAttribute("errorL",false);
            return "redirect:/";
        }
        session.setAttribute("errorL",true);
        System.out.println((Boolean) session.getAttribute("error"));
        return "redirect:/login";
    }
    @GetMapping("/registration")
    public String registartion(Model model,HttpSession session){
        Boolean error=session.getAttribute("errorR")!=null?(Boolean) session.getAttribute("errorR"):false;
        model.addAttribute("error",error);
        return "registration";
    }
    @PostMapping("/registration")
    public String confirmRegistration(Model model,@RequestParam(name="username") String username,
                                      @RequestParam (name = "password") String password,
                                      @RequestParam (name = "ConfirmPassword") String confirmPassword,
                                      @RequestParam (name = "email") String email,HttpSession session) {
        if(userService.getByEmail(email)!=null&&email.equals("admin@gmail.com")&&
                password.equals("admin")&&password.equals(confirmPassword))
        {
            List<Order> orders = new ArrayList<>();
            List<Role> roles = new ArrayList<>();
            roles.add(roleService.getItem(1L));
            session.setAttribute("role","admin");
            session.setAttribute("errorR",false);
            userService.addItem(new User(null, username, password, email, orders, roles));
            return "redirect:/login";
        }
        else if (userService.getByEmail(email) == null&&password.equals(confirmPassword)) {
            List<Order> orders = new ArrayList<>();
            List<Role> roles = new ArrayList<>();
            roles.add(roleService.getItem(2L));
            session.setAttribute("role","user");
            session.setAttribute("errorR",false);
            userService.addItem(new User(null, username, password, email, orders, roles));
            return "redirect:/login";
        }
        session.setAttribute("errorR",true);
        return "redirect:/registration";
    }
}
