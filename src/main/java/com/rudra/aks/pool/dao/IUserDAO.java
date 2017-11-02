package com.rudra.aks.pool.dao;

import com.rudra.aks.pool.model.UserDTO;

public interface IUserDAO {

	UserDTO	getUser(int userid);

	void getPoolProperties();

	int getNoOfUsers();
}
