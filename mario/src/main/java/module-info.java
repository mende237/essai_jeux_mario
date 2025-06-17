module com.game.mario {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;

    opens com.game.mario to javafx.fxml;

    exports com.game.mario;
}
