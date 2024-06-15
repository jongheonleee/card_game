package main.framework.controller;

import main.framework.domain.AppController;
import main.framework.factory.AppContext;

public class AppComponentController {

    private final AppContext context;
    public AppComponentController(AppContext context) {
        this.context = context;
    }

    public AppController launchApp() throws Exception {
        try {
            AppController controller = context.getAppController();
            return controller;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }


}
