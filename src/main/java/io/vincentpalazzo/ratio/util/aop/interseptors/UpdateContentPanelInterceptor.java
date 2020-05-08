package io.vincentpalazzo.ratio.util.aop.interseptors;

import io.vincentpalazzo.ratio.App;
import io.vincentpalazzo.ratio.view.IMainPanel;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UpdateContentPanelInterceptor implements MethodInterceptor {

    private static final Logger LOGGER = LoggerFactory.getLogger(UpdateContentPanelInterceptor.class);

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {

        try {
            // code run before execution
            LOGGER.error("Was called the method: " + invocation.getMethod().getName());
            IMainPanel mainPanel = (IMainPanel) App.getInstance().getInstanceObject(IMainPanel.class);;
            mainPanel.refreshUI();
        } catch (Throwable throwable){
            throwable.printStackTrace();
        }finally {
            // code run after execution
            return invocation.proceed();

        }
    }
}
