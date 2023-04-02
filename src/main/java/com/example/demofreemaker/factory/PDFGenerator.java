package com.example.demofreemaker.factory;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import com.itextpdf.text.Document;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfWriter;

import freemarker.template.Configuration;
import freemarker.template.Template;
public class PDFGenerator {

    public void generatePDF(Map<String, Object> data, String templatePath, String outputPath) throws Exception {
        // Tạo cấu hình cho FreeMarker
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_23);
        cfg.setClassForTemplateLoading(PDFGenerator.class, "/");

        // Lấy template từ file .ftl
        Template template = cfg.getTemplate(templatePath);

        // Tạo tài liệu PDF mới
        Document document = new Document(PageSize.A4);

        // Tạo đối tượng PdfWriter để ghi PDF vào OutputStream
        OutputStream outputStream = new FileOutputStream(new File(outputPath));
        PdfWriter.getInstance(document, outputStream);

        // Bắt đầu tài liệu
        document.open();

        // Xử lý template và thêm nội dung vào tài liệu
//        template.process(data, document);

        // Đóng tài liệu
        document.close();
    }
}
