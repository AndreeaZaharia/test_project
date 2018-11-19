package service.impl;

import entity.Role;
import entity.User;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.springframework.stereotype.Service;

import repository.IRoleRepository;
import repository.IUserRepository;

import service.IUserService;

import java.util.Arrays;
import java.util.HashSet;


/**
 * DOCUMENT ME!
 *
 * @version  $Revision$, $Date$
 */
@Service("userService")
public class UserServiceImpl implements IUserService
{
	/**  */

	private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

	/**  */
	@Autowired
	private IRoleRepository roleRepository;

	/**  */
	@Autowired
	private IUserRepository userRepository;

	/**
	 * DOCUMENT ME!
	 *
	 * @param   email
	 * @return
	 */
	public User findUserByEmail(String email)
	{
		return userRepository.findByEmail(email);
	}

	/**
	 * DOCUMENT ME!
	 *
	 * @param  user
	 */
	public void saveUser(User user)
	{
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		user.setActive(1);
		Role userRole = roleRepository.findByRole("ADMIN");
		user.setRoles(new HashSet<>(Arrays.asList(userRole)));
		userRepository.save(user);
	}
}
