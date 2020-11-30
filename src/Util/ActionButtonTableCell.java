package Util;

import java.util.function.Function;

import com.jfoenix.controls.JFXButton;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconName;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.event.ActionEvent;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;

public class ActionButtonTableCell<S> extends TableCell<S, JFXButton> {
	private final JFXButton actionButton;
	
	public ActionButtonTableCell(String type, String label, Function< S, S> function) {
		FontAwesomeIcon icon = new FontAwesomeIcon();
		icon.setSize("1em");
		icon.getStyleClass().add("white");
		this.actionButton = new JFXButton();
		if(type.equals("edit")) {
			icon.setIcon(FontAwesomeIconName.PENCIL);
			this.actionButton.getStyleClass().addAll("btn-primary");
		}else if(type.equals("delete")) {
			icon.setIcon(FontAwesomeIconName.TRASH);
			this.actionButton.getStyleClass().addAll("btn-danger");	
		}
		else if(type.equals("view")) {
			icon.setIcon(FontAwesomeIconName.SEARCH);
			this.actionButton.getStyleClass().addAll("btn-info");
		}
		else if(type.equals("add")) {
			icon.setIcon(FontAwesomeIconName.PLUS);
			this.actionButton.getStyleClass().addAll("btn-success");
		}
        this.actionButton.setGraphic(icon);
        this.actionButton.setOnAction((ActionEvent e) -> {function.apply(getCurrentItem());});
        this.actionButton.setMaxWidth(Double.MAX_VALUE);
    }
	
	public S getCurrentItem() {
        return (S) getTableView().getItems().get(getIndex());
    }
	
	public static <S> Callback<TableColumn<S, JFXButton>, TableCell<S, JFXButton>> forTableColumn(String type, String label, Function< S, S> function) {
        return param -> new ActionButtonTableCell<>(type, label, function);
    }
	
	@Override
    public void updateItem(JFXButton item, boolean empty) {
        super.updateItem(item, empty);
        if (empty) {
            setGraphic(null);
        } else {                
            setGraphic(actionButton);
        }
    }
}