package com.example.demofreemaker.controller;

import com.openhtmltopdf.extend.FontResolver;
import com.openhtmltopdf.pdfboxout.PdfRendererBuilder;
import freemarker.cache.FileTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;
import freemarker.template.Version;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

@Slf4j
public class CreatePdfController {

    public static void main(String[] args) {
        CreatePdfController CreatePdfController = new CreatePdfController();
        CreatePdfController.create();
    }

    private void create() {

        try {
            Configuration cfg = new Configuration();
            cfg.setIncompatibleImprovements(new Version(2, 3, 20));
            File fileFtl = new File("src/main/resources/");
            log.info("Path is: " + fileFtl.getAbsolutePath());
            FileTemplateLoader templateLoader = new FileTemplateLoader(fileFtl);

            cfg.setTemplateLoader(templateLoader);
            cfg.setDefaultEncoding("UTF-8");
            cfg.setLocale(Locale.US);
            cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
            Template template = cfg.getTemplate("drbCustomerAuto.ftl");
            Map<String, Object> input = new HashMap<>();
            input.put("fontFamily", "Roboto-Regular.ttf");
            defaultInput(input);

            outputDrb(template, input);
        } catch (Exception exception) {
            log.error("Exception occured while processing freeMarker template: {} ", exception.getMessage(), exception);
        }
    }

    private void outputDrb(Template template, Map<String, Object> input) {
        try {
            String outputHtmlPath = "export/templates/";
            File outHtmlPathFolder = new File(outputHtmlPath);
            if (!outHtmlPathFolder.exists()) {
                outHtmlPathFolder.mkdir();
            }
            // save html file
            String fileHtmlName = "fileExport.html";
            String outputHtmlName = outputHtmlPath + fileHtmlName;
            Writer fileWriter = new OutputStreamWriter(new FileOutputStream(outputHtmlName), StandardCharsets.UTF_8);
            template.process(input, fileWriter);
            fileWriter.close();
            // save pdf file
            String filePdfName = outputHtmlPath + "fileExport.pdf";
            OutputStream os = new FileOutputStream(filePdfName);

            File inputFile = new File(outputHtmlName);
            String inputHtmlUri = inputFile.toURI().toURL().toString();
            PdfRendererBuilder builder = new PdfRendererBuilder();
            builder.useFastMode();
            log.info("inputHtmlUri: " + inputHtmlUri);
            builder.withUri(inputHtmlUri);
            builder.toStream(os);
            builder.run();
            os.close();

// templates/fileExport.html
        } catch (Exception ex) {
            log.error("Exception occured while processing freeMarker template: {} ", ex.getMessage(), ex);
        }
    }

    private void defaultInput(Map<String, Object> input) {
        input.put("tfr", "");
        input.put("createdDate", "");
        input.put("createLoanSugMinute", "39");
        input.put("mnRateRevTermUnit", "");
        input.put("cifNo", "");
        input.put("customerIbName", "");
        input.put("mnRateRevTerm", "");
        input.put("contractNo", "");
        input.put("dueDate", "");
        input.put("floatInfo", ""); // id
        input.put("paymentDomestic", "");
        input.put("approvalLoanDate", "");
        input.put("dearlerRate", "");
        input.put("paymentSalary", "");
        input.put("numberAmount", "");
        input.put("varianceCode", "");
        input.put("accountNo", "");
        input.put("disbursementPurpose", "");
        input.put("disbursementDate", "");
        input.put("issueDate", "");
        input.put("numberAmountCus", "");
        input.put("disbursementHour", "");
        input.put("issuePlace", "");
        input.put("createLoanSugDate", "23-12-2023");
        input.put("customerIdNo", "");
        input.put("lastDueDate", "");
        input.put("interestRate", "");
        input.put("isFlat", "");
        input.put("margin", "");
        input.put("flatInfo", "");
        input.put("wordAmount", "");
        input.put("createLoanSugHour", "15");
        input.put("totalAmoutApprove", "");
        input.put("paymentLC", "");
        input.put("isFloat", "");
        input.put("wordAmountCus", "");
        input.put("taxCode", "");
        input.put("customerName", "");
        input.put("disbursementMinute", "");
        input.put("convertDrbDoc", "");
        input.put("convertDrbDocId", "");
        input.put("sugsNo", "");
        input.put("loanTerm", "");
        input.put("productCode", "");
        input.put("productCode", "");
        input.put("mnRateReviewDate", "");
        input.put("csh", "");
        input.put("creditFee", "");
        input.put("effectiveDate", "");
        input.put("customerNamePay", "");
        input.put("floatInfoBody", "");
        input.put("showApproveLoanSuggestion", "");
        input.put("finalSchedules", "");
        input.put("noSchedules", "");
        input.put("noSchedulesBody", "");
        input.put("hasSchedulesTableBody", "");
        input.put("hasSchedules", "");
        input.put("hasSchedulesTable", "");
        input.put("finalSchedulesRoot", "");
        input.put("documentRefund", "");
        input.put("fundamentalPaymentTable", "");
        input.put("fundamentalPaymentTableSal", "");
        input.put("fundamentalPaymentTableLc", "");
        input.put("cusCertCode", "");
        input.put("limitName", "");
        input.put("GTCK", "");
        input.put("GTDHK", "");
        input.put("GTKDHK", "");
        input.put("GLTCK", "");
        input.put("rootFixLoanTermCode", "");
        input.put("intFreq", "");
        input.put("prFreq", "");
        input.put("maturityDate", "");
        input.put("prMaturityDate", "");
        input.put("gtkdhkTable", "");
        input.put("gtkdhkTableBody", "");
        input.put("username", "");
    }


}
