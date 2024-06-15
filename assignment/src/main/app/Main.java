package main.app;

import main.framework.controller.AppComponentController;
import main.framework.domain.AppController;
import main.framework.factory.AppContext;

public class Main {

    public static void main(String[] args) {
        AppContext appContext = AppContext.getInstance();
        AppComponentController appComponentController = new AppComponentController(appContext);
        try {
            AppController app = createAppComponent(appComponentController);
            run(app);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("서비스 정상화 전까지 앱을 사용하실 수 없습니다.");
        }

    }

    private static AppController createAppComponent(AppComponentController controller) {
       try {
           AppController app = controller.launchApp();
           return app;
       } catch (Exception e) {
           throw new IllegalStateException("앱 론칭 과정에서 에러가 발생했습니다.");
       }
    }

    private static void run(AppController app) {
        app.play();
        app.end();
    }
}