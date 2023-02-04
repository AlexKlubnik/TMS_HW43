package by.klubnikov.orderpage.controller;

import by.klubnikov.orderpage.entity.Order;
import by.klubnikov.orderpage.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("orders")
public class OrderController {

    private final OrderService service;

    @GetMapping
    public String findAll(Model model) {
        List<Order> orders = service.findAll();
        model.addAttribute("ordersList", orders);
        return "orders";
    }

    @GetMapping("order_form")
    public String showForm(Model model, Order order) {
        model.addAttribute("orderForForm", order);
        return "order_form";
    }

    @PostMapping("order_form")
    public String save(Model model, @Valid Order order, Errors errors) {
        if (errors.hasErrors()) {
            return "order_form";
        }
        service.save(order);
        return "redirect:/orders";
    }

    @GetMapping("{id}")
    public String findById(Model model, @PathVariable(name = "id") Long id) {
        Order order = service.findById(id).orElseThrow();
        LocalDateTime requestTime = LocalDateTime.now();
        LocalDateTime creationTime = order.getCreationTime();
        order.setStatus(service.getOrderStatus(requestTime, creationTime));
        model.addAttribute("single_order", order);
        return "order";
    }

    @GetMapping("delete/{id}")
    public String delete(@PathVariable Long id) {
        service.delete(id);
        return "redirect:/orders";
    }

}
