/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jsj.api.service;

import com.jsj.api.entity.dao.TicketDAO;
import com.jsj.api.entity.dao.UsuarioDAO;
import java.io.ByteArrayOutputStream;
import org.springframework.stereotype.Service;
import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.HorizontalAlignment;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.UnitValue;
import com.itextpdf.io.font.constants.StandardFontFamilies;

import java.io.ByteArrayOutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author Juan José Molano Franco
 */
@Service
public class AdminService {

    private final TicketDAO ticketDao;
    private final UsuarioDAO usuarioDao;

    public AdminService(TicketDAO ticketDao, UsuarioDAO usuarioDao) {
        this.ticketDao = ticketDao;
        this.usuarioDao = usuarioDao;
    }

    public long countTicketsTotales() {
        return ticketDao.countTicketsTotales();
    }

    public long countTicketsAbiertos() {
        return ticketDao.countTicketsAbiertos();
    }

    public long countTicketsCerrados() {
        return ticketDao.countTicketsCerrados();
    }

    public long countUsuariosTotales() {
        return usuarioDao.countUsuariosTotales();
    }

    public long countEmpleadosActivos() {
        return usuarioDao.countEmpleadosActivos();
    }

    public long countAgentesActivos() {
        return usuarioDao.countAgentesActivos();
    }

    public byte[] generateSystemReportPdf() throws Exception {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PdfWriter writer = new PdfWriter(baos);
        PdfDocument pdf = new PdfDocument(writer);
        Document document = new Document(pdf, PageSize.A4);
        document.setMargins(50, 50, 50, 50);

        // Fuentes y colores
        PdfFont titleFont = PdfFontFactory.createFont("Helvetica-Bold");
        PdfFont bold = PdfFontFactory.createFont("Helvetica-Bold");
        PdfFont regular = PdfFontFactory.createFont("Helvetica");

        // Título
        document.add(new Paragraph("Reporte General del Sistema")
                .setFont(titleFont).setFontSize(20).setTextAlignment(TextAlignment.CENTER)
                .setMarginBottom(30).setFontColor(DeviceRgb.BLUE));

        document.add(new Paragraph("Generado el " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")))
                .setFont(regular).setFontSize(10).setTextAlignment(TextAlignment.CENTER)
                .setMarginBottom(40));

        // Datos del reporte
        float[] columnWidths = {3, 2};
        Table table = new Table(UnitValue.createPercentArray(columnWidths));
        table.setWidth(UnitValue.createPercentValue(80));
        table.setHorizontalAlignment(HorizontalAlignment.CENTER);

        // Filas
        addTableRow(table, bold, "Total de Tickets", String.valueOf(countTicketsTotales()));
        addTableRow(table, bold, "Tickets Abiertos", String.valueOf(countTicketsAbiertos()));
        addTableRow(table, bold, "Tickets Cerrados", String.valueOf(countTicketsCerrados()));
        addTableRow(table, bold, "Total de Usuarios", String.valueOf(countUsuariosTotales()));
        addTableRow(table, bold, "Empleados Activos", String.valueOf(countEmpleadosActivos()));
        addTableRow(table, bold, "Agentes Activos", String.valueOf(countAgentesActivos()));

        document.add(table);

        // Pie de página
        document.add(new Paragraph("\nReporte generado automáticamente por el sistema de gestión de tickets.")
                .setFont(regular).setFontSize(9).setTextAlignment(TextAlignment.CENTER)
                .setItalic());

        document.close();
        return baos.toByteArray();
    }

    private void addTableRow(Table table, PdfFont boldFont, String label, String value) {
        table.addCell(new Cell().add(new Paragraph(label).setFont(boldFont).setFontSize(12)).setBackgroundColor(new DeviceRgb(240, 248, 255)));
        table.addCell(new Cell().add(new Paragraph(value).setFont(boldFont).setFontSize(14).setTextAlignment(TextAlignment.CENTER)));
    }

}
