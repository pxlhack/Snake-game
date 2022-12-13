module ru.pxlhack.snake_game {
    requires javafx.controls;
    requires javafx.fxml;

    opens ru.pxlhack.snake_game to javafx.fxml;
    exports ru.pxlhack.snake_game;
}