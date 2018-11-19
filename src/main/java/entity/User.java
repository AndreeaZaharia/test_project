package entity;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;


/**
 * DOCUMENT ME!
 *
 * @version  $Revision$, $Date$
 */
@Entity
@Table(name = "user")
public class User
{
	//~ Instance fields --------------------------
	/**  */
	@Column(name = "active")
	private int active;

	/**  */
	@Column(name = "email")
	@Email(message = "*Please provide a valid Email")
	@NotEmpty(message = "*Please provide an email")
	private String email;

	/**  */
	@Column(name = "user_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id
	private int id;

	/**  */
	@Column(name = "last_name")
	@NotEmpty(message = "*Please provide your last name")
	private String lastName;

	/**  */
	@Column(name = "name")
	@NotEmpty(message = "*Please provide your name")
	private String name;

	/**  */
	@Column(name = "password")
	@Length(
		min = 5,
		message = "*Your password must have at least 5 characters"
	)
	@NotEmpty(message = "*Please provide your password")
	private String password;

	/**  */
	@JoinTable(
		name = "user_role",
		joinColumns = @JoinColumn(name = "user_id"),
		inverseJoinColumns = @JoinColumn(name = "role_id")
	)
	@ManyToMany(cascade = CascadeType.ALL)
	private Set<Role> roles;
	//~ Methods ----------------------------------

	/**
	 * DOCUMENT ME!
	 *
	 * @return
	 */
	public int getActive()
	{
		return active;
	}
	

	/**
	 * DOCUMENT ME!
	 *
	 * @param  active
	 */
	public void setActive(int active)
	{
		this.active = active;
	}
	

	/**
	 * DOCUMENT ME!
	 *
	 * @return
	 */
	public String getEmail()
	{
		return email;
	}
	

	/**
	 * DOCUMENT ME!
	 *
	 * @param  email
	 */
	public void setEmail(String email)
	{
		this.email = email;
	}
	

	/**
	 * DOCUMENT ME!
	 *
	 * @return
	 */
	public int getId()
	{
		return id;
	}
	

	/**
	 * DOCUMENT ME!
	 *
	 * @param  id
	 */
	public void setId(int id)
	{
		this.id = id;
	}
	

	/**
	 * DOCUMENT ME!
	 *
	 * @return
	 */
	public String getLastName()
	{
		return lastName;
	}
	

	/**
	 * DOCUMENT ME!
	 *
	 * @param  lastName
	 */
	public void setLastName(String lastName)
	{
		this.lastName = lastName;
	}
	

	/**
	 * DOCUMENT ME!
	 *
	 * @return
	 */
	public String getName()
	{
		return name;
	}
	

	/**
	 * DOCUMENT ME!
	 *
	 * @param  name
	 */
	public void setName(String name)
	{
		this.name = name;
	}
	

	/**
	 * DOCUMENT ME!
	 *
	 * @return
	 */
	public String getPassword()
	{
		return password;
	}
	

	/**
	 * DOCUMENT ME!
	 *
	 * @param  password
	 */
	public void setPassword(String password)
	{
		this.password = password;
	}
	

	/**
	 * DOCUMENT ME!
	 *
	 * @return
	 */
	public Set<Role> getRoles()
	{
		return roles;
	}
	

	/**
	 * DOCUMENT ME!
	 *
	 * @param  roles
	 */
	public void setRoles(Set<Role> roles)
	{
		this.roles = roles;
	}
}
