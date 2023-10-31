package com.gp.ServiceImpl;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gp.Service.EmployeesService;
import com.gp.beans.Employees;

import jakarta.persistence.criteria.Order;

@Service
public class EmployeeServiceImpl implements EmployeesService {
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
			employeeDao.save(employee);
		} else {
			System.out.println("Employee with given id is not present");
		}
	}

	@Override
	public String signIn(String employeeId, String password) {
		Optional<Employees> employeeOptional = employeeDao.findById(employeeId);

		if (employeeOptional.isPresent()) {
			Employees employee = employeeOptional.get();

			if (employee.isFirstLogin()) {
				if (isValidNewPassword(password)) {

					employee.setPassword(password);
					employee.setFirstLogin(false);
					employeeDao.save(employee);
					return "Password changed successfully.";
				} else {
					return "Please provide a valid new password.";
				}
			} else {
				if (employee.getPassword().equals(password)) {
					return "Sign-in successful.";
				} else {
					return "Invalid password.";
				}
			}
		} else {
			return "Employee not found.";
		}
	}

	private boolean isValidNewPassword(String password) {

		boolean hasUppercase = false;
		boolean hasLowercase = false;
		boolean hasDigit = false;

		for (char c : password.toCharArray()) {
			if (Character.isUpperCase(c)) {
				hasUppercase = true;
			} else if (Character.isLowerCase(c)) {
				hasLowercase = true;
			} else if (Character.isDigit(c)) {
				hasDigit = true;
			}
		}

		return password != null && password.length() >= 8;
	}
}
