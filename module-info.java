module ma.fstt.esalaf {
    requires javafx.controls;
    requires javafx.fxml;
            
        requires org.controlsfx.controls;
                        requires org.kordamp.bootstrapfx.core;
            
    opens ma.fstt.esalaf to javafx.fxml;
    exports ma.fstt.esalaf;
}