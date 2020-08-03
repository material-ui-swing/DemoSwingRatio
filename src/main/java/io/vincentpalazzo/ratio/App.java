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
package io.vincentpalazzo.ratio;

import com.google.inject.*;
import com.google.inject.matcher.Matchers;
import io.vincentpalazzo.ratio.model.ModelMediator;
import io.vincentpalazzo.ratio.util.AppResourceManager;
import io.vincentpalazzo.ratio.util.IAppResourceManager;
import io.vincentpalazzo.ratio.util.aop.annotations.UpdateContentPanelAOP;
import io.vincentpalazzo.ratio.util.aop.interseptors.UpdateContentPanelInterceptor;
import io.vincentpalazzo.ratio.view.*;

import javax.swing.*;

/**
 * @author https://github.com/vincenzopalazzo
 */
public class App {

    public static final App SINGLETON = new App();

    public static App getInstance(){
        return SINGLETON;
    }

    private Injector injector;
    private App(){}

    private void initGuice(){
        injector = Guice.createInjector(Stage.PRODUCTION, new AppBinder());
    }

    public Object getInstanceObject(Class implementationClass){
        return injector.getInstance(implementationClass);
    }

    private void initApp() {
        initGuice();

        IFrameApp frameApp = (IFrameApp) getInstanceObject(IFrameApp.class);

        frameApp.initView();
    }

    private class AppBinder extends AbstractModule{

        @Override
        protected void configure() {
            //Binding Frame

            bind(IFrameApp.class).to(Frame.class).in(Singleton.class);

            bind(IAppResourceManager.class).to(AppResourceManager.class).in(Singleton.class);

            bind(IMainPanel.class).to(MainPanel.class).in(Singleton.class);

            bind(IPanelSetting.class).to(PanelSetting.class).in(Singleton.class);

            bind(IPresentationPanel.class).to(PanelPresentation.class).in(Singleton.class);

            bind(IAppResourceManager.class).to(AppResourceManager.class).in(Singleton.class);

            bind(ModelMediator.class).in(Singleton.class);
            //TODO refactoring
            bind(DialogDeveloperInfo.class).in(Singleton.class);

            //AOP
            bindInterceptor(Matchers.any(), Matchers.annotatedWith(UpdateContentPanelAOP.class), new UpdateContentPanelInterceptor());
        }
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                SINGLETON.initApp();
            }
        });
    }

}
