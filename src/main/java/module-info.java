module org.example.stockportfoliotracker {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.stockportfoliotracker to javafx.fxml;
    exports org.example.stockportfoliotracker;
    exports org.example.stockportfoliotracker.Model;
    opens org.example.stockportfoliotracker.Model to javafx.fxml;
}