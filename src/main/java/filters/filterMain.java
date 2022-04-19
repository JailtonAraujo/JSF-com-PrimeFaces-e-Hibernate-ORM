package filters;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;

import jpautil.JPAUtil;


@WebFilter("/*")
public class filterMain extends HttpFilter implements Filter {
       
    public filterMain() {}
    
	public void destroy() {
		JPAUtil.getEntityManager().close();
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		chain.doFilter(request, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {
		JPAUtil.getEntityManager();
	}

}
