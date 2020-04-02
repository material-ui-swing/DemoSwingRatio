package io.vincentpalazzo.ratio.control;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import io.vincentpalazzo.ratio.control.actions.ActionExitApp;
import io.vincentpalazzo.ratio.control.actions.ActionViewDeveloper;
import io.vincentpalazzo.ratio.control.exceptions.AppControlException;
import io.vincentpalazzo.ratio.util.Constant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.util.HashMap;
import java.util.Map;

/**
 * @author https://github.com/vincenzopalazzo
 */
@Singleton
public class MediatorActions {

    private static final Logger LOGGER = LoggerFactory.getLogger(MediatorActions.class);

    private Map<String, Action> actionMap = new HashMap<>();

    public Action getAction(String key){
        if(actionMap == null){
            LOGGER.debug("Mediator actions null");
            throw new AppControlException("Mediator actions app null");
        }
        if(actionMap.containsKey(key)){
            return actionMap.get(key);
        }
        LOGGER.debug("Action whit key " + key + " not found");
        throw new AppControlException("Action whit key " + key + " not found");
    }

    @Inject
    private void setActionExit(ActionExitApp actionExit){
        LOGGER.debug("I'm adding the action: " + actionExit.getClass().getSimpleName() + "with key: " + Constant.EXIT_ACTION_KEY);
        actionMap.put(Constant.EXIT_ACTION_KEY, actionExit);
    }
    @Inject
    private void setActionExit(ActionViewDeveloper actionViewDeveloper){
        LOGGER.debug("I'm adding the action: " + actionViewDeveloper.getClass().getSimpleName() + "with key: " + Constant.EXIT_ACTION_KEY);
        actionMap.put(Constant.VIEW_DEV_ACTION_KEY, actionViewDeveloper);
    }
}
