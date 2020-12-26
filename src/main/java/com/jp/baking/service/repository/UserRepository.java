package com.jp.baking.service.repository;

import com.jp.baking.service.model.User;

public interface UserRepository extends CustomRepository<User, Long> {

	public User findByUsername(String username);
	public User findByUsernameAndPassword(String username, String password);
}
