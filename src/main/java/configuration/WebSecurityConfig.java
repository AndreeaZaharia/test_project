package configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.context.annotation.Configuration;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.channel.ChannelProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.sql.DataSource;


/**
 * DOCUMENT ME!
 *
 * @version  $Revision$, $Date$
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter
{
	//~ Instance fields --------------------------
	/**  */
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	/**  */
	@Autowired
	@Qualifier("dataSource")
	private DataSource dataSource;

	/**  */
	@Autowired
	private CORSFilter myCorsFilter;

	/**  */
	@Value("${spring.queries.roles-query}")
	private String rolesQuery;

	/**  */
	@Value("${spring.queries.users-query}")
	private String usersQuery;
	//~ Methods ----------------------------------
	/**
	 * @see  org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter#configure(org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder)
	 */
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception
	{
		auth.jdbcAuthentication().usersByUsernameQuery(usersQuery).authoritiesByUsernameQuery(rolesQuery).dataSource(
			dataSource).passwordEncoder(bCryptPasswordEncoder);
	}
	
	/**
	 * @see  org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter#configure(org.springframework.security.config.annotation.web.builders.HttpSecurity)
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception
	{
		http.addFilterBefore(myCorsFilter, ChannelProcessingFilter.class);
		http.authorizeRequests().antMatchers("/").permitAll().antMatchers("/login").permitAll().antMatchers(
			"/registration").permitAll().antMatchers("/admin/**").hasAuthority("ADMIN").anyRequest().authenticated()
			.and().csrf().disable().formLogin().loginPage("/login").failureUrl("/login?error=true").defaultSuccessUrl(
			"/admin/home").usernameParameter("email").passwordParameter("password").and().logout().logoutRequestMatcher(
			new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/").and().exceptionHandling().accessDeniedPage(
			"/access-denied");
		;
	}
	
	/**
	 * @see  org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter#configure(org.springframework.security.config.annotation.web.builders.WebSecurity)
	 */
	@Override
	public void configure(WebSecurity web) throws Exception
	{
		web.ignoring().antMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/images/**");
	}
}
