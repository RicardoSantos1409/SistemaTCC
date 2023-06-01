package edu.evento;
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
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import javafx.util.converter.DoubleStringConverter;

public class BaudaryEvento extends Application{

	private TextField txtEvento = new TextField();
	private TextField txtDesc = new TextField();
	private TextField txtData = new TextField();
	
	private ControlEvento control = new ControlEvento();
	
	private TableView<EntidadeEvento> tableTarefa = new TableView<>();
	
	@Override
	public void start(Stage stage) throws Exception {
		
		GridPane gp = new GridPane();
		BorderPane bp = new BorderPane();
		FlowPane fp = new FlowPane();
		Scene scn = new Scene(bp, 500, 300);
		
		Text lblEvento = new Text("Nome Evento");
		Text lblDesc = new Text("Descrição");
		Text lblData = new Text("Data");
		
		lblEvento.setStyle("-fx-fill: red");
		lblDesc.setStyle("-fx-fill: red");
		lblData.setStyle("-fx-fill: red");
		
		ColumnConstraints col1 = new ColumnConstraints();
		col1.setPercentWidth(70);
		ColumnConstraints col2 = new ColumnConstraints();
		col2.setPercentWidth(60);
		gp.getColumnConstraints().addAll(col1, col2);
		
		Button btnSalva = new Button("Salva");
		Button btnPesquisa = new Button("Pesquisa");
		Button btnExcluir = new Button("Excluir");
		Button btnLimpar = new Button ("Limpar");
		Button btnEditar = new Button ("Editar");
		
		btnSalva.setStyle("-fx-background-color: coral; -fx-border-color: grey; -fx-border-radius: 5;");
		btnPesquisa.setStyle("-fx-base: coral; -fx-border-color: grey; -fx-border-radius: 5");
		btnExcluir.setStyle("-fx-base: coral; -fx-border-color: grey; -fx-border-radius: 5");
		btnLimpar.setStyle("-fx-base: coral; -fx-border-color: grey; -fx-border-radius: 5");
		btnEditar.setStyle("-fx-base: coral; -fx-border-color: grey; -fx-border-radius: 5");
		
		
		fp.setHgap(0);
		fp.getChildren().addAll(btnSalva, btnPesquisa, btnExcluir, btnEditar, btnLimpar);
		
		gp.add(lblEvento, 0, 0);
		gp.add(lblDesc, 0, 2);
		gp.add(lblData, 0, 4);
		gp.add(txtEvento, 0, 1);
		gp.add(txtDesc, 0, 3);
		gp.add(txtData, 0, 5);
		gp.add(fp, 0, 6);
		
		bp.setTop(gp);
		bp.setBottom(tableTarefa);

		vincular();
		alimentarTable();
		
		btnSalva.setOnAction(e -> {
			btnSalva.setStyle("-fx-background-color: yellow; -fx-border-color: grey; -fx-border-radius: 5");
			control.salvar();
			control.pesquisar();
			btnSalva.setStyle("-fx-base: coral; -fx-border-color: grey; -fx-border-radius: 5");
			btnEditar.setStyle("-fx-background-color: coral; -fx-border-color: grey; -fx-border-radius: 5");
			});
		
		btnPesquisa.setOnAction(e -> {
			btnPesquisa.setStyle("-fx-background-color: yellow; -fx-border-color: grey; -fx-border-radius: 5");
			control.pesquisar();
			btnPesquisa.setStyle("-fx-base: coral; -fx-border-color: grey; -fx-border-radius: 5");
			});
		
		btnLimpar.setOnAction(e -> {
			btnLimpar.setStyle("-fx-background-color: yellow; -fx-border-color: grey; -fx-border-radius: 5");
			control.limpar();
			control.pesquisar();
			btnLimpar.setStyle("-fx-base: coral; -fx-border-color: grey; -fx-border-radius: 5");
			});
		
		btnEditar.setOnAction(e -> {
			btnEditar.setStyle("-fx-background-color: yellow; -fx-border-color: grey; -fx-border-radius: 5");
			control.editar();
			});
		
		btnExcluir.setOnAction(e -> {
			btnExcluir.setStyle("-fx-background-color: yellow; -fx-border-color: grey; -fx-border-radius: 5");
			control.apagar();
			control.limpar();
			control.pesquisar();
			btnExcluir.setStyle("-fx-base: coral; -fx-border-color: grey; -fx-border-radius: 5");
			});
		
		stage.setScene(scn);
		stage.setTitle("Cadastro Evento");
		stage.show();
		
	}
	
	public void alimentarTable() {
		TableColumn<EntidadeEvento, String> col1 = new TableColumn<>("Evento");
		col1.setCellValueFactory(new PropertyValueFactory<EntidadeEvento, String>("eventoNome"));
		
		TableColumn<EntidadeEvento, String> col2 = new TableColumn<>("Descrição");
		col2.setCellValueFactory(new PropertyValueFactory<EntidadeEvento, String>("eventoDesc"));
		
		TableColumn<EntidadeEvento, String> col3 = new TableColumn<>("Data");
		col3.setCellValueFactory(new PropertyValueFactory<EntidadeEvento, String>("eventoDuracao"));
		
		tableTarefa.getColumns().addAll(col1, col2, col3);
		tableTarefa.setItems(control.getLista());
	}
	
	public void vincular() {
		Bindings.bindBidirectional(txtEvento.textProperty(), control.nomeProperty());
		Bindings.bindBidirectional(txtDesc.textProperty(), control.descProperty());
		Bindings.bindBidirectional(txtData.textProperty(), control.duracaoProperty());
	}
	
	public static void main(String[] args) {
		Application.launch(BaudaryEvento.class, args);
	}
}
