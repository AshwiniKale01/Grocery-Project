package com.gp.ServiceImpl;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gp.beans.Employees;

import jakarta.persistence.criteria.Order;


@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private ProductDao productDao;

    @Override
    public void sendRequestToIncreaseProductQuantity(long productId, int quantity) {
        Optional<Product> productOptional = productDao.findById(productId);

        if (productOptional.isPresent()) {
            Product product = productOptional.get();
            product.setQuantity(product.getQuantity() + quantity);
            productDao.save(product);
        } else {
            System.out.println("Product is not present");
        }
    }
    
    
    
    @Autowired
    private OrderDao orderDao;

    @Override
    public void updateOrderStatus(long orderId, String newStatus) {
        Optional<Order> orderOptional = orderDao.findById(orderId);

        if (orderOptional.isPresent()) {
            Order order = orderOptional.get();

            // Check if the new status is one of the valid statuses
            if (isValidOrderStatus(newStatus)) {
                order.setStatus(newStatus);
                orderDao.save(order);
            } else {
               System.out.println("No updates yet");
            }
        } else {
            System.out.println("order with given order id is not present");
        }
    }

    private boolean isValidOrderStatus(String status) {
       
        List<String> validStatuses = Arrays.asList("Shipped", "Out for Delivery", "Delivered", "Cancelled");
        return validStatuses.contains(status);
    }
    
    
    
    @Autowired
    private UserDao userDao;

    @Override
    public void unlockUserAccount(long userId) {
        Optional<User> userOptional = userDao.findById(userId);

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            
            user.setAccountLocked(false);
            userDao.save(user);
        } else {
            System.out.println("user with given id is not availble");
        }
    }
    
    
    @Autowired
    private Employees employeeDao;

    @Override
    public void editEmployeePassword(long employeeId, String newPassword) {
        Optional<Employees> employeeOptional = employeeDao.findById(employeeId);

        if (employeeOptional.isPresent()) {
            Employees employee = employeeOptional.get();
            // Update the employee's password
            employee.setPassword(newPassword);
            employeeDao.;
        } else {
            System.out.println("Employee with given id is not present");        }
    }
}

