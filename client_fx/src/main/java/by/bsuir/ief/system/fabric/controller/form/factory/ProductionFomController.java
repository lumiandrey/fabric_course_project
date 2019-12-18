package by.bsuir.ief.system.fabric.controller.form.factory;

import by.bsuir.ief.system.fabric.FXLoaderController;
import by.bsuir.ief.system.fabric.controller.DialogManager;
import by.bsuir.ief.system.fabric.controller.NewWindowWorking;
import by.bsuir.ief.system.fabric.controller.form.BaseFormController;
import by.bsuir.ief.system.fabric.controller.table.ChoiceTableController;
import by.bsuir.ief.system.fabric.controller.table.TableController;
import by.bsuir.ief.system.fabric.model.entity.fabric.*;
import by.bsuir.ief.system.fabric.model.storage.Repository;
import by.bsuir.ief.system.fabric.model.storage.command.AbstractCommandShowError;
import by.bsuir.ief.system.fabric.model.storage.command.fabric.component_part.ReadComponentWithProducerCommand;
import by.bsuir.ief.system.fabric.model.storage.command.fabric.out_going_cost.OutGoingConstListCommand;
import by.bsuir.ief.system.fabric.model.storage.command.fabric.out_going_dynamic.OutGoingDynamicListCommand;
import by.bsuir.ief.system.fabric.model.storage.command.fabric.production.CalculationOutGoingProductionCommand;
import by.bsuir.ief.system.fabric.util.ListUtil;
import by.bsuir.ief.system.fabric.util.ValidUtil;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class ProductionFomController extends BaseFormController<ProductionEntity> {


    @FXML
    private Label idLabel;
    @FXML
    private TextField nameField;

    @FXML
    private TextField describeField;

    @FXML
    private Label resultCostOutGoing;

    @FXML
    private void handleClear() {
        clearField();
    }

    protected void clearField() {

        idLabel.setText("0");

        nameField.setText("");

        describeField.setText("");

        resultCostOutGoing.setText("");
    }

    @Override
    protected String isValidMessage() {
        String errorMessage = "";

        errorMessage += ValidUtil.checkFieldStringNotEmpty(
                "Название поставщика не заполнено!\n",
                nameField
        );

        return errorMessage;
    }

    @Override
    protected void initEntity(ProductionEntity entity) {
        entity.setName(nameField.getText());
        entity.setDescribe(describeField.getText());
    }

    @Override
    protected void initField(ProductionEntity entity) {
        idLabel.setText(Integer.toString(entity.getId()));

        nameField.setText(entity.getName());
        describeField.setText(entity.getDescribe());
    }

    @Override
    protected ProductionEntity create() {
        return new ProductionEntity();
    }

    @Override
    public Pane getLayout() {
        return rootPane;
    }

    @FXML
    private void onAddComponentTable() {

        if (entity == null){
            entity = create();
        }

        if(entity != null) {

            Repository.getResult(new ReadComponentWithProducerCommand())
                    .subscribe(new AbstractCommandShowError<ComponentPartWithProducerEntity[]>() {
                        @Override
                        public void onNext(ComponentPartWithProducerEntity[] studentEntities) {

                            try {
                                ChoiceTableController<ComponentPartWithProducerEntity> choiceTableController = (ChoiceTableController) FXLoaderController.loadTableController("ChoiceTableView");
                                TableController<ComponentPartWithProducerEntity> studentEntityTableController = FXLoaderController.loadBusinessTableController("ComponentPartTableView");

                                choiceTableController.setController(studentEntityTableController);
                                Stage dialogStage = new Stage();

                                choiceTableController.setMultiSelect(true);

                                Scene scene = new Scene(choiceTableController.getLayout());
                                dialogStage.setScene(scene);
                                choiceTableController.setPrimaryStage(dialogStage);
                                choiceTableController.setList(ListUtil.parseArray(studentEntities));

                                dialogStage.showAndWait();

                                if (choiceTableController.isOk()) {

                                    List<ComponentPartWithProducerEntity> studentEntities1 = choiceTableController.getSelected();

                                    entity.setComponentPartEntities(studentEntities1);

                                }
                            } catch (Exception ex) {

                                DialogManager.showErrorDialog("Ошибка подгрузки данных", ex.getMessage());
                            }
                        }
                    });

        } else {
            DialogManager.showErrorDialog("Error", "Произошла ошибка студентов можно добавлять только для групп находящихся в базе!!!");
        }
    }

    @FXML
    private void onShowComponentTable(){
        if(entity != null && entity.getId() > 0) {
            try {

                Stage stage = new Stage();

                NewWindowWorking newWindowWorking = (NewWindowWorking) FXLoaderController.loadController("NewWondowWorking");

                stage.setScene(new Scene(newWindowWorking.getLayout()));

                TableController<ComponentPartWithProducerEntity> tableController = FXLoaderController.loadBusinessTableController("ComponentPartTableView");

                newWindowWorking.setTableController(tableController);
                newWindowWorking.setPrimaryStage(stage);

                Repository.getsComponentPart(tableController, entity.getId());

                stage.show();

            } catch (IOException e) {
                DialogManager.showErrorDialog("Error", "Произошла ошибка " + e.getMessage());
            }
        } else {
            DialogManager.showErrorDialog("Error", "Произошла ошибка просмотреть можно только после создания продукции!!!");
        }
    }

    @FXML
    private void onAddConstTable() {

        if (entity == null){
            entity = create();
        }

        if(entity != null) {

            Repository.getResult(new OutGoingConstListCommand())
                    .subscribe(new AbstractCommandShowError<OutGoingConstEntity[]>() {
                        @Override
                        public void onNext(OutGoingConstEntity[] studentEntities) {

                            try {
                                ChoiceTableController<OutGoingConstEntity> choiceTableController = (ChoiceTableController) FXLoaderController.loadTableController("ChoiceTableView");
                                TableController<OutGoingConstEntity> studentEntityTableController = FXLoaderController.loadBusinessTableController("OutGoingConstTableView");

                                choiceTableController.setController(studentEntityTableController);
                                Stage dialogStage = new Stage();

                                choiceTableController.setMultiSelect(true);

                                Scene scene = new Scene(choiceTableController.getLayout());
                                dialogStage.setScene(scene);
                                choiceTableController.setPrimaryStage(dialogStage);
                                choiceTableController.setList(ListUtil.parseArray(studentEntities));

                                dialogStage.showAndWait();

                                if (choiceTableController.isOk()) {

                                    List<OutGoingConstEntity> studentEntities1 = choiceTableController.getSelected();

                                    entity.setOutGoingConstEntities(studentEntities1);

                                }
                            } catch (Exception ex) {

                                DialogManager.showErrorDialog("Ошибка подгрузки данных", ex.getMessage());
                            }
                        }
                    });

        } else {
            DialogManager.showErrorDialog("Error", "Произошла ошибка студентов можно добавлять только для групп находящихся в базе!!!");
        }
    }

    @FXML
    private void onShowConstTable(){
        if(entity != null && entity.getId() > 0) {
            try {

                Stage stage = new Stage();

                NewWindowWorking newWindowWorking = (NewWindowWorking) FXLoaderController.loadController("NewWondowWorking");

                stage.setScene(new Scene(newWindowWorking.getLayout()));

                TableController<OutGoingConstEntity> tableController = FXLoaderController.loadBusinessTableController("OutGoingConstTableView");

                newWindowWorking.setTableController(tableController);
                newWindowWorking.setPrimaryStage(stage);

                Repository.getsOutGoingCost(tableController, entity.getId());

                stage.show();

            } catch (IOException e) {
                DialogManager.showErrorDialog("Error", "Произошла ошибка " + e.getMessage());
            }
        } else {
            DialogManager.showErrorDialog("Error", "Произошла ошибка просмотреть можно только после создания продукции!!!");
        }
    }

    @FXML
    private void onAddDynamicTable() {

        if (entity == null){
            entity = create();
        }

        if(entity != null) {

            Repository.getResult(new OutGoingDynamicListCommand())
                    .subscribe(new AbstractCommandShowError<OutGoingDynamicEntity[]>() {
                        @Override
                        public void onNext(OutGoingDynamicEntity[] studentEntities) {

                            try {
                                ChoiceTableController<OutGoingDynamicEntity> choiceTableController = (ChoiceTableController) FXLoaderController.loadTableController("ChoiceTableView");
                                TableController<OutGoingDynamicEntity> studentEntityTableController = FXLoaderController.loadBusinessTableController("OutGoingDynamicTableView");

                                choiceTableController.setController(studentEntityTableController);
                                Stage dialogStage = new Stage();

                                choiceTableController.setMultiSelect(true);

                                Scene scene = new Scene(choiceTableController.getLayout());
                                dialogStage.setScene(scene);
                                choiceTableController.setPrimaryStage(dialogStage);
                                choiceTableController.setList(ListUtil.parseArray(studentEntities));

                                dialogStage.showAndWait();

                                if (choiceTableController.isOk()) {

                                    List<OutGoingDynamicEntity> studentEntities1 = choiceTableController.getSelected();

                                    entity.setOutGoingDynamicEntities(studentEntities1);

                                }
                            } catch (Exception ex) {

                                DialogManager.showErrorDialog("Ошибка подгрузки данных", ex.getMessage());
                            }
                        }
                    });

        } else {
            DialogManager.showErrorDialog("Error", "Произошла ошибка студентов можно добавлять только для групп находящихся в базе!!!");
        }
    }

    @FXML
    private void onShowDynamicTable(){
        if(entity != null && entity.getId() > 0) {
            try {

                Stage stage = new Stage();

                NewWindowWorking newWindowWorking = (NewWindowWorking) FXLoaderController.loadController("NewWondowWorking");

                stage.setScene(new Scene(newWindowWorking.getLayout()));

                TableController<OutGoingDynamicEntity> tableController = FXLoaderController.loadBusinessTableController("OutGoingDynamicTableView");

                newWindowWorking.setTableController(tableController);
                newWindowWorking.setPrimaryStage(stage);

                Repository.getsOutGoingDynamic(tableController, entity.getId());

                stage.show();

            } catch (IOException e) {
                DialogManager.showErrorDialog("Error", "Произошла ошибка " + e.getMessage());
            }
        } else {
            DialogManager.showErrorDialog("Error", "Произошла ошибка просмотреть можно только после создания продукции!!!");
        }
    }

    @FXML
    private void onActiomCalculate(){
        if(entity != null && entity.getId() > 0) {
            Repository.getResult(new CalculationOutGoingProductionCommand(entity.getId()))
                    .subscribe(new AbstractCommandShowError<CalculationOutGoingProductionEntity>() {
                        @Override
                        public void onNext(CalculationOutGoingProductionEntity studentEntities) {

                            resultCostOutGoing.setText(String.format("Полные издержки по этому продукту составляют: %f", studentEntities.getTotalOutGoing()));
                            export(studentEntities);
                        }
                    });
        } else {
            DialogManager.showErrorDialog("Error", "Произошла ошибка просмотреть можно только после создания продукции!!!");
        }
    }

    @FXML
    private void export(CalculationOutGoingProductionEntity entity){

        FileChooser fileChooser = new FileChooser();

        // Set extension filter
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
                "pdf files (*.pdf)", "*.pdf");
        fileChooser.getExtensionFilters().add(extFilter);

        // Show save file dialog
        File file = fileChooser.showSaveDialog(primaryStage);

        if (file != null) {
            // Make sure it has the correct extension
            if (!file.getPath().endsWith(".pdf")) {
                file = new File(file.getPath() + ".pdf");
            }

            File finalFile = file;
            new Thread(()->{ try  {
                Document document = new Document();
                // step 2
                PdfWriter.getInstance(document, new FileOutputStream(finalFile));
                // step 3
                document.open();

                BaseFont font = BaseFont.createFont("c:/windows/fonts/arialbd.ttf", "cp1251", BaseFont.EMBEDDED);
                BaseFont  Twofont = BaseFont.createFont("c:/windows/fonts/arialbd.ttf", "cp1251", BaseFont.NOT_EMBEDDED);

                //-------------added PDF FEATURES EZXPORT Production Component part -----------------//
                List<? extends ComponentPartEntity> componentPartEntities = entity.getProductionEntity().getComponentPartEntities();
                if (!componentPartEntities.isEmpty()) {

                    // параграф с текстом
                    Paragraph purpose = new Paragraph("Данные о компонентах продукта", new Font(font, 14));
                    purpose.setSpacingAfter(16);

                    PdfPTable table = new PdfPTable(2);

                    PdfPCell c1 = new PdfPCell(new Phrase("наименование компонента", new Font(font, 14)));
                    c1.setHorizontalAlignment(Element.ALIGN_CENTER);
                    table.addCell(c1);

                    c1 = new PdfPCell(new Phrase("Стоимость", new Font(font, 14)));
                    c1.setHorizontalAlignment(Element.ALIGN_CENTER);

                    table.addCell(c1);

                    table.setHeaderRows(1);

                    double totalCostCopmonent = 0;

                    for (ComponentPartEntity routeEntity : componentPartEntities) {
                        table.addCell(new Phrase(String.valueOf(routeEntity.getName()), new Font(Twofont, 12)));
                        table.addCell(new Phrase(String.valueOf(routeEntity.getCost()), new Font(font, 12)));

                        totalCostCopmonent += routeEntity.getCost();

                        //table.setFooterRows();
                    }

                    c1 = new PdfPCell(new Phrase("Итого получилось", new Font(Twofont, 14)));
                    c1.setHorizontalAlignment(Element.ALIGN_CENTER);
                    table.addCell(c1);

                    c1 = new PdfPCell(new Phrase(String.valueOf(totalCostCopmonent), new Font(font, 14)));
                    c1.setHorizontalAlignment(Element.ALIGN_CENTER);

                    table.addCell(c1);

                    //table.setFooterRows(table.getRows().size());
                    // step 4
                    purpose.add(table);
                    //document.add(table);
                    document.add(purpose);
                }

                document.add(new Header("", ""));

                List<OutGoingDynamicEntity> outGoingDynamicEntities = entity.getProductionEntity().getOutGoingDynamicEntities();
                if (!componentPartEntities.isEmpty()) {

                    // параграф с текстом
                    Paragraph purpose = new Paragraph("Данные о переменных издержках", new Font(font, 14));
                    purpose.setSpacingAfter(16);

                    PdfPTable table = new PdfPTable(2);

                    PdfPCell c1 = new PdfPCell(new Phrase("Переменные издержки", new Font(font, 14)));
                    c1.setHorizontalAlignment(Element.ALIGN_CENTER);
                    table.addCell(c1);

                    c1 = new PdfPCell(new Phrase("Стоимость", new Font(font, 14)));
                    c1.setHorizontalAlignment(Element.ALIGN_CENTER);

                    table.addCell(c1);

                    table.setHeaderRows(1);

                    double totalCostCopmonent = 0;

                    for (OutGoingDynamicEntity routeEntity : outGoingDynamicEntities) {
                        table.addCell(new Phrase(String.valueOf(routeEntity.getName()), new Font(Twofont, 12)));
                        table.addCell(new Phrase(String.valueOf(routeEntity.getCost()), new Font(font, 12)));

                        totalCostCopmonent += routeEntity.getCost();

                        //table.setFooterRows();
                    }

                    c1 = new PdfPCell(new Phrase("Итого получилось", new Font(Twofont, 14)));
                    c1.setHorizontalAlignment(Element.ALIGN_CENTER);
                    table.addCell(c1);

                    c1 = new PdfPCell(new Phrase(String.valueOf(totalCostCopmonent), new Font(font, 14)));
                    c1.setHorizontalAlignment(Element.ALIGN_CENTER);

                    table.addCell(c1);

                    //table.setFooterRows(table.getRows().size());
                    // step 4
                    purpose.add(table);
                    document.add(purpose);
                }

                document.add(new Header("", ""));

                List<OutGoingConstEntity> outGoingConstEntities = entity.getProductionEntity().getOutGoingConstEntities();
                if (!componentPartEntities.isEmpty()) {

                    Paragraph purpose = new Paragraph("Данные о постоянных издержках", new Font(font, 14));
                    purpose.setSpacingAfter(16);
                    PdfPTable table = new PdfPTable(2);

                    PdfPCell c1 = new PdfPCell(new Phrase("Постоянные издержки", new Font(font, 14)));
                    c1.setHorizontalAlignment(Element.ALIGN_CENTER);
                    table.addCell(c1);

                    c1 = new PdfPCell(new Phrase("Стоимость", new Font(font, 14)));
                    c1.setHorizontalAlignment(Element.ALIGN_CENTER);

                    table.addCell(c1);

                    table.setHeaderRows(1);

                    double totalCostCopmonent = 0;

                    for (OutGoingConstEntity routeEntity : outGoingConstEntities) {
                        table.addCell(new Phrase(String.valueOf(routeEntity.getName()), new Font(Twofont, 12)));
                        table.addCell(new Phrase(String.valueOf(routeEntity.getCost()), new Font(font, 12)));

                        totalCostCopmonent += routeEntity.getCost();

                        //table.setFooterRows();
                    }

                    c1 = new PdfPCell(new Phrase("Итого получилось", new Font(Twofont, 14)));
                    c1.setHorizontalAlignment(Element.ALIGN_CENTER);
                    table.addCell(c1);

                    c1 = new PdfPCell(new Phrase(String.valueOf(totalCostCopmonent), new Font(font, 14)));
                    c1.setHorizontalAlignment(Element.ALIGN_CENTER);

                    table.addCell(c1);

                    //table.setFooterRows(table.getRows().size());
                    // step 4
                    purpose.add(table);
                    document.add(purpose);
                }

                //document.add();

                Paragraph purpose = new Paragraph(String.format("Общая стоимость продукции с учётом издержек: %s", String.valueOf(entity.getTotalOutGoing())), new Font(font, 14));
                purpose.setSpacingAfter(10);
                PdfPTable table = new PdfPTable(2);

                PdfPCell c1 = new PdfPCell(new Phrase("Общая стоимость продукции с учётом издержек", new Font(font, 14)));
                c1.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(c1);

                c1 = new PdfPCell(new Phrase(String.valueOf(entity.getTotalOutGoing()), new Font(font, 14)));
                c1.setHorizontalAlignment(Element.ALIGN_CENTER);

                table.addCell(c1);

                table.setHeaderRows(1);
                // step 5
                purpose.add(table);

                //purpose.add(new Header("Общая стоимость продукции с учётом издержек", String.valueOf(entity.getTotalOutGoing())));
                document.add(purpose);

                document.close();
            } catch (IOException | DocumentException e) {
                Platform.runLater(()->{
                    DialogManager.showErrorDialog("Ошибка при записи в файл", "Ошибка при записи в файл " + e.getMessage());});
            }
            }).start();

        }
    }

   /* PdfPTable createPdfTable(CalculationOutGoingProductionEntity entity) throws IOException, DocumentException {

        PdfDocument pdfDocument = new PdfDocument();

        BaseFont font = BaseFont.createFont("c:/windows/fonts/arialbd.ttf", "cp1251", BaseFont.EMBEDDED);
        BaseFont  Twofont = BaseFont.createFont("c:/windows/fonts/arialbd.ttf", "cp1251", BaseFont.NOT_EMBEDDED);
        PdfPTable table = new PdfPTable(3);

        PdfPCell c1 = new PdfPCell(new Phrase("Логин", new Font(font, 14)));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Статус пользователя", new Font(font, 14)));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);

        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Уровень доступа", new Font(font, 14)));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);
        table.setHeaderRows(1);

        for(UserEntity routeEntity: entityObservableList){
            table.addCell(new Phrase(String.valueOf(routeEntity.getLogin()), new Font(Twofont, 12)));
            table.addCell(new Phrase(routeEntity.getStatus()  != 0 ? "не заблокирован" : "заблокирован", new Font(font, 12)));
            table.addCell(new Phrase(String.valueOf(routeEntity.getRoleApplication().getName()), new Font(Twofont, 12)));
        }

        return pdfDocument;
    }*/
}
