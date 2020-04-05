package io.vincentpalazzo.ratio.util.aop.interseptors;

import io.vincentpalazzo.ratio.App;
import io.vincentpalazzo.ratio.view.IFrameApp;
import io.vincentpalazzo.ratio.view.MainPanel;
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
            LOGGER.info("Was called the method: " + invocation.getMethod().getName());
            MainPanel frameApp = (MainPanel) App.getInstance().getInstanceObject(MainPanel.class);
            frameApp.refreshUI();
        } finally {
            // code run after execution
            return invocation.proceed();

        }
    }
}
