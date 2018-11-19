package configuration;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

import org.springframework.stereotype.Component;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * DOCUMENT ME!
 *
 * @version  $Revision$, $Date$
 */
@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class CORSFilter implements Filter
{
	//~ Constructors -----------------------------
	/** Creates a new CORSFilter object. */
	public CORSFilter( )
	{
		super( );
	}
	//~ Methods ----------------------------------
	/**
	 * @see  javax.servlet.Filter#doFilter(javax.servlet.ServletRequest, javax.servlet.ServletResponse,
	 *       javax.servlet.FilterChain)
	 */
	@Override
	public final void doFilter(final ServletRequest req, final ServletResponse res, final FilterChain chain)
		throws IOException, ServletException
	{
		final HttpServletResponse response = (HttpServletResponse) res;
		response.setHeader("Access-Control-Allow-Origin", "http://localhost:4200");

		// without this header jquery.ajax calls returns 401 even after successful login and SSESSIONID being
		// succesfully stored.
		response.setHeader("Access-Control-Allow-Credentials", "true");

		response.setHeader("Access-Control-Allow-Methods", "POST, PUT, GET, OPTIONS, DELETE");
		response.setHeader("Access-Control-Max-Age", "3600");
		response.setHeader("Access-Control-Allow-Headers",
			"X-Requested-With, Authorization, Origin, Content-Type, Version");
		response.setHeader("Access-Control-Expose-Headers", "X-Requested-With, Authorization, Origin, Content-Type");

		final HttpServletRequest request = (HttpServletRequest) req;
		if (!request.getMethod().equals("OPTIONS"))
		{
			chain.doFilter(req, res);
		}
		else
		{
			// do not continue with filter chain for options requests
		}
	}
	
	/** @see  javax.servlet.Filter#destroy() */
	@Override
	public void destroy() { }
	
	/** @see  javax.servlet.Filter#init(javax.servlet.FilterConfig) */
	@Override
	public void init(FilterConfig filterConfig) throws ServletException { }
}
