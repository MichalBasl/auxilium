package cz.aimtec.auxilium.stripes.filter;

import cz.aimtec.auxilium.object.Member;
import java.io.IOException;
import java.util.Base64;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class NtlmAuthorizationFilter implements Filter {

	protected final Logger logger = LogManager.getLogger(getClass());

	@SuppressWarnings("unused")
	private FilterConfig filterConfig = null;

	public void init(FilterConfig filterConfig) throws ServletException {
		this.filterConfig = filterConfig;
	}

	public void destroy() {
		this.filterConfig = null;
	}

	@SuppressWarnings("static-access")
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {

		try {

			HttpServletResponse response = (HttpServletResponse) resp;
			HttpServletRequest request = (HttpServletRequest) req;
			String auth = request.getHeader("Authorization");
			String domain = "";
			String username = "";

			if (auth == null) {
				response.setStatus(response.SC_UNAUTHORIZED);
				response.setHeader("WWW-Authenticate", "NTLM");
				return;
			}

			if (auth.startsWith("NTLM ")) {
				byte[] msg = Base64.getDecoder().decode(auth.substring(5));
				int off = 0, length, offset;

				if (msg[8] == 1) {
					byte z = 0;
					byte[] msg1 = { (byte) 'N', (byte) 'T', (byte) 'L', (byte) 'M', (byte) 'S', (byte) 'S', (byte) 'P',
							z, (byte) 2, z, z, z, z, z, z, z, (byte) 40, z, z, z, (byte) 1, (byte) 130, z, z, z,
							(byte) 2, (byte) 2, (byte) 2, z, z, z, z, z, z, z, z, z, z, z, z };

					response.setStatus(response.SC_UNAUTHORIZED);
					response.setHeader("WWW-Authenticate", "NTLM " + Base64.getEncoder().encodeToString(msg1).trim());
					return;
				} else if (msg[8] == 3) {
					off = 30;
					length = msg[off + 1] * 256 + msg[off];
					offset = msg[off + 3] * 256 + msg[off + 2];
					domain = convertString(new String(msg, offset, length));
					length = msg[off + 9] * 256 + msg[off + 8];
					offset = msg[off + 11] * 256 + msg[off + 10];
					username = convertString(new String(msg, offset, length));
				}
			}

			Member member = new Member();
			member.setDomain(domain);
			member.setUsername(username);
			request.setAttribute("member", member);
			chain.doFilter(req, resp);
			logger.debug("set attribute <member> " + member.getDetails());

		} catch (Exception e) {
			System.out.println(e);
		}
	}

	private String convertString(String str) {
		StringBuffer out = new StringBuffer();
		int c = str.length();
		for (int i = 0; i < c; i++) {
			if (i % 2 == 0) {
				char ch = str.charAt(i);
				out = new StringBuffer(out).append(ch);
			}
		}
		return new String(out);
	}

}
