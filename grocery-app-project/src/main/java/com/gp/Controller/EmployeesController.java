package com.gp.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gp.Service.EmployeesService;

@RestController
@RequestMapping("/employees")
public class EmployeesController {

	@Autowired
	private UserService userService;

	@Autowired
	private EmployeesService employeeService;

	@PostMapping("/sign-in")
	public ResponseEntity<String> signIn(@RequestParam String employeeId, @RequestParam String password) {
		String signInMessage = employeeService.signIn(employeeId, password);

		if (signInMessage.startsWith("Sign-in successful")) {
			return ResponseEntity.ok(signInMessage);
		} else {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(signInMessage);
		}
	}

	@PostMapping("/send-request-to-increase-product-quantity")
	public ResponseEntity<String> sendRequestToIncreaseProductQuantity(@RequestParam long productId,
			@RequestParam int quantity) {
		employeeService.sendRequestToIncreaseProductQuantity(productId, quantity);
		return ResponseEntity.ok("Request to increase product quantity sent successfully.");
	}

	@PostMapping("/update-order-status")
	public ResponseEntity<String> updateOrderStatus(@RequestParam long orderId, @RequestParam String newStatus) {
		employeeService.updateOrderStatus(orderId, newStatus);
		return ResponseEntity.ok("Order status updated successfully.");
	}

	@PostMapping("/unlock-account")
	public ResponseEntity<String> unlockUserAccount(@RequestParam long userId) {
		userService.unlockUserAccount(userId);
		return ResponseEntity.ok("User account unlocked successfully.");
	}

	@PostMapping("/edit-password")
	public ResponseEntity<String> editEmployeePassword(@RequestParam long employeeId,
			@RequestParam String newPassword) {
		employeeService.editEmployeePassword(employeeId, newPassword);
		return ResponseEntity.ok("Employee password edited successfully.");
	}

}
