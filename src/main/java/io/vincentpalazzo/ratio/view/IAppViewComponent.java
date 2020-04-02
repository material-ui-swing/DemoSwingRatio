package io.vincentpalazzo.ratio.view;

import io.vincentpalazzo.ratio.view.eception.ViewException;

/**
 * @author https://github.com/vincenzopalazzo
 */
public interface IAppViewComponent {

    void initView() throws ViewException;
    void initComponent() throws ViewException;

    void initActions() throws ViewException;
}
