package io.vincentpalazzo.ratio.view;

import com.google.inject.Inject;
import io.vincentpalazzo.ratio.control.MediatorActions;
import io.vincentpalazzo.ratio.util.Constant;
import io.vincentpalazzo.ratio.util.IAppResourceManager;
import io.vincentpalazzo.ratio.view.eception.ViewException;
import org.jdesktop.swingx.JXTaskPane;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * @author https://github.com/vincenzopalazzo
 */
public class DialogDeveloperInfo extends JDialog implements IAppViewComponent {

    private static final int DIMENSION_X = 500;
    private static final int DIMENSION_Y = 500;
    private static final int DIMENSION_TASK_PANE_X = 20;
    private static final int DIMENSION_TASK_PANE_Y = 150;

    private ImageIcon iconDeveloper;
    private JLabel labelIconDeveloper;
    private JLabel labelName;
    private JLabel descriptone;
    private JLabel siteLink;
    private JLabel profileGithubLink;
    private JXTaskPane taskPaneDescription;
    private GroupLayout layout;
    private IAppResourceManager resourceManager;
    private MediatorActions mediator;

    @Inject
    public DialogDeveloperInfo(JFrame owner, MediatorActions mediator, IAppResourceManager resourceManager) {
        super(owner, true);
        this.mediator = mediator;
        this.resourceManager = resourceManager;
    }

    @Override
    public void initView() throws ViewException {
        dialogInit();
        initComponent();

        positionComponetIntoLayout();

        initActions();

        setLocationRelativeTo(getOwner());
        pack();
        setVisible(true);
    }

    @Override
    public void initComponent() throws ViewException {
        layout = new GroupLayout(this.getContentPane());
        this.getContentPane().setLayout(layout);

        labelName = new JLabel();
        labelName.setText(resourceManager.getResourceString(Constant.NAME_DEV_VALUE));

        iconDeveloper = resourceManager.getResourceImage(Constant.ICON_DEV_PATH);
        labelIconDeveloper = new JLabel();
        labelIconDeveloper.setIcon(iconDeveloper);

        siteLink = new JLabel(resourceManager.getResourceString(Constant.SITE_DEV_INFO));
        //siteLink.addMouseListener(mediator.getAction(Constant.ACTION_OPEN_LINK));
        profileGithubLink = new JLabel(resourceManager.getResourceString(Constant.GITHUB_DEV_INFO));

        descriptone = new JLabel("TODO my description");

        taskPaneDescription = new JXTaskPane();
        taskPaneDescription.setTitle(resourceManager.getResourceString(Constant.TITLE_TASK_PANE_DESCRIPTION));
        taskPaneDescription.add(descriptone);
        taskPaneDescription.setAnimated(true);
        taskPaneDescription.setSize(new Dimension(DIMENSION_TASK_PANE_Y, DIMENSION_TASK_PANE_X));
        setTitle(resourceManager.getResourceString(Constant.TITLE_DIALOG_DEV_INFO));
    }

    protected void positionComponetIntoLayout() {
        if (layout == null) {
            throw new ViewException("Layout null");
        }

        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);


        //Init position component with group layaut
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                        .addComponent(labelIconDeveloper)
                        .addComponent(labelName)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(50)
                                .addComponent(siteLink, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(50)
                                .addComponent(profileGithubLink, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        )
                        .addComponent(taskPaneDescription)
        );

        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addComponent(labelIconDeveloper, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(20)
                        .addComponent(labelName, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(20)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(siteLink)
                                .addComponent(profileGithubLink)
                        )
                        .addGap(20)
                        .addComponent(taskPaneDescription)
        );
    }

    @Override
    public void initActions() throws ViewException {
          JButton button = new JButton();
          button.setAction(new AbstractAction() {
              @Override
              public void actionPerformed(ActionEvent e) {
                  System.out.print("Hello");
              }
          });
    }
}
