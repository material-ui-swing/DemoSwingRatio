package io.vincentpalazzo.ratio.util.aop.interseptors;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author https://github.com/vincenzopalazzo
 */
public class HibernateInterceptor implements MethodInterceptor {

    private static final Logger LOGGER = LoggerFactory.getLogger(HibernateInterceptor.class);

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        LOGGER.debug("Was called the method: " + invocation.getMethod().getName());
        //TODO is empty
        try {
            LOGGER.debug("The transaction with hibernate is open");
            return invocation.proceed();
        }finally {
            LOGGER.debug("The transaction with hibernate was closed");

        }
    }

}
