module org.example.stockportfoliotracker {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.scripting;
    requires org.apache.poi.ooxml;


    opens org.example.stockportfoliotracker to javafx.fxml;
    exports org.example.stockportfoliotracker;
    exports org.example.stockportfoliotracker.Model;
    opens org.example.stockportfoliotracker.Model to javafx.fxml;
    exports org.example.stockportfoliotracker.Utility;
    opens org.example.stockportfoliotracker.Utility to javafx.fxml;
}