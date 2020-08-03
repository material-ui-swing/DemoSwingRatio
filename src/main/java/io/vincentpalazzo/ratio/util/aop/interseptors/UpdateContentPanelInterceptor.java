/**
 * MIT License
 *
 * Copyright (c) 2020 Vincenzo Palazzo vincenzopalazzodev@gmail.com
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
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
