package edu.nota;
import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import javafx.util.converter.DoubleStringConverter;

public class BaudaryNota extends Application {
	
	private TextField txtTcc = new TextField();
	private TextField txtNota = new TextField();
	private TextField txtMotivo = new TextField();
	
	private ControlNota control = new ControlNota();
	
	private TableView<EntidadeNota> tabelaNota = new TableView<>();

	@Override
	public void start(Stage stage) throws Exception {
		
		FlowPane fp = new FlowPane();
		GridPane gp = new GridPane();
		BorderPane bp = new BorderPane();
		Scene snc = new Scene(bp, 600, 300);
		
		ColumnConstraints col1 = new ColumnConstraints();
		col1.setPercentWidth(70);
		gp.getColumnConstraints().addAll(col1);
		
		Label lblTcc = new Label("Nome do TCC");
		Label lblNota = new Label("Nota");
		Label lblMotivo = new Label("Motivo");
		
		Button btnRegistrar = new Button("Registar");
		Button btnPesquisar = new Button("Pesquisar");
		Button btnEditar = new Button("Editar");
		Button btnExcluir = new Button("Excluir");
		Button btnLimpar = new Button("Limpar");
		
		fp.getChildren().addAll(btnRegistrar, btnPesquisar, btnEditar, btnExcluir, btnLimpar);
		
		gp.add(lblTcc, 0, 0);
		gp.add(lblNota, 0, 2);
		gp.add(lblMotivo, 0, 4);
		gp.add(txtTcc, 0, 1);
		gp.add(txtNota, 0, 3);
		gp.add(txtMotivo, 0, 5);
		gp.add(fp, 0, 6);
		
		bp.setTop(gp);
		bp.setBottom(tabelaNota);
		
		vincular();
		alimentarTable();
		
		btnRegistrar.setStyle("-fx-base: coral");
		btnPesquisar.setStyle("-fx-base: coral");
		btnEditar.setStyle("-fx-base: coral");
		btnExcluir.setStyle("-fx-base: coral");
		btnLimpar.setStyle("-fx-base: coral");
		
		btnRegistrar.setOnAction(e -> {
			control.adicionar();
			control.limpar();
			control.pesquisar();
			btnEditar.setStyle("-fx-color: grey;");
			btnEditar.setStyle("-fx-base: coral");
			});
		
		btnPesquisar.setOnAction(e -> control.pesquisar());
		btnEditar.setOnAction(e -> {
			btnEditar.setStyle("-fx-color: grey;");
			control.editar();
			});
		
		btnExcluir.setOnAction(e -> {
			control.apagar();
			control.limpar();
			control.pesquisar();
			});
		
		btnLimpar.setOnAction(e -> {
			control.limpar();
			control.pesquisar();
			});
		
		stage.setScene(snc);
		stage.setTitle("Nota TCC");
		stage.show();
		
	}
	
	public void alimentarTable() {
		TableColumn<EntidadeNota, String> col1 = new TableColumn<>("Nome TCC");
		col1.setCellValueFactory(new PropertyValueFactory<EntidadeNota, String>("nomeTcc"));
		
		TableColumn<EntidadeNota, String> col2 = new TableColumn<>("Nota TCC");
		col2.setCellValueFactory(new PropertyValueFactory<EntidadeNota, String>("notaTcc"));
		
		TableColumn<EntidadeNota, String> col3 = new TableColumn<>("Motivo Nota");
		col3.setCellValueFactory(new PropertyValueFactory<EntidadeNota, String>("motivoTcc"));
		
		tabelaNota.getColumns().addAll(col1, col2, col3);
		tabelaNota.setItems(control.getLista());
	}
	
	public void vincular () {
		Bindings.bindBidirectional(txtTcc.textProperty(), control.tccStringProperty());
		
		javafx.util.StringConverter<? extends Number> converterNumber = 
				new DoubleStringConverter();
		
		Bindings.bindBidirectional(txtNota.textProperty(), control.notaProperty(), (StringConverter<Number>)converterNumber);
		
		Bindings.bindBidirectional(txtMotivo.textProperty(), control.motivoProperty());
	}
	
	public static void main(String[] args) {
		Application.launch(BaudaryNota.class, args);
	}
}
