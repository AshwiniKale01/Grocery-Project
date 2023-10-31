package com.gp.Service;

public interface EmployeesService {
	
	void sendRequestToIncreaseProductQuantity(long productId, int quantity);
	
	
	void updateOrderStatus(long orderId, String newStatus);
	
	void unlockUserAccount(long userId);
	
	void editEmployeePassword(long employeeId, String newPassword);
	


	
	

}
