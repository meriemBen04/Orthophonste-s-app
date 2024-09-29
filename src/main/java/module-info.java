module com.example.tp_poo {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;
    requires jdk.compiler;
    requires jdk.jfr;

    opens com.example.tp_poo to javafx.fxml;
    exports com.example.tp_poo;
    exports Controlleur;
    opens Controlleur to javafx.fxml;

}