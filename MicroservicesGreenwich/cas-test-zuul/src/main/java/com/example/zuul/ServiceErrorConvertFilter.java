package com.example.zuul;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

@Component
//@ConditionalOnClass(name = "com.netflix.zuul.ZuulFilter")
public class ServiceErrorConvertFilter extends ZuulFilter {

	Logger logger = LogManager.getLogger();

	@Override
	public Object run() {
		RequestContext requestContext = RequestContext.getCurrentContext();
		String jwtHeader = "bearer eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJwaG9uZSI6bnVsbCwidXNlcl9uYW1lIjoiemhhb2tqIiwic2NvcGUiOlsiYWxsIl0sImlzQWRtaW4iOmZhbHNlLCJleHAiOjE1Njc5OTEyODUsInVzZXJOYW1lIjoiemhhb2tqIiwidXNlcklkIjoiIiwiYXV0aG9yaXRpZXMiOlsiUk9MRV9VU0VSIl0sImp0aSI6IjRjNDdhYTY0LThmMDctNDg2My04MmIzLTYxYmQ1MzJlZjNmMyIsInVzZXJDb2RlIjoiemhhb2tqIiwiZW1haWwiOiJudWxsIiwiY2xpZW50X2lkIjoiMTAwMDAwMDEwMiJ9.hkmIvgg4ivpyyMiLODx5hGtfDYu8SQTscVmll6ce9nAoOWCCE0AXmxSC0aPOIMBlCUBMqmYK6xLi31SBJVoC1ym0pxDKZ1nsJw3efQKsac3bfAd7gmWhiQw1LbzHKUksmTwCa6oVYS9877eTwpfWM9QUBz-IxJYo-26VTr4Cg9rWm6LVyRpTd6xU_wJGBbAei-af-K1axcRZ1uGmYPGw7P6wG1Ts03Gph65_7Z5zEBXiijxlp789qyN6QsrZBPOgnC0ncCaacMxMLsn1pk3vqQP5NNbOg6UxXavmsRg4579qnhl5Ug-jTWXo4Ttj4dCGzBSrMfrecc_heJOo_RZH4g";
		requestContext.addZuulRequestHeader("Authorization", jwtHeader);
		return null;
	}

	@Override
	public boolean shouldFilter() {
		return true;
	}

	@Override
	public int filterOrder() {
		return FilterConstants.PRE_DECORATION_FILTER_ORDER - 4;
	}

	@Override
	public String filterType() {
		return FilterConstants.PRE_TYPE;
	}

}
